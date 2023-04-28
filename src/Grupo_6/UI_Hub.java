package Grupo_6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UI_Hub {
    private JLabel id;
    private JTextField id_field;
    private JLabel peso;
    private JTextField peso_field;
    private JLabel DescContenido;
    private JTextField contenido_field;
    private JPanel HUB;
    private JLabel remitente;
    private JTextField remitente_field;
    private JTextField receptor_field;
    private JComboBox comboBox1;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox inspeccionadoEnAduanasCheckBox;
    private JTextArea textArea1;
    private JPanel Operaciones;
    private JButton apilar;
    private JButton desapilarButton;
    private JTextField nºColumnaTextField;
    private JButton mostrarDatosContenedorButton;
    private JButton cuantosButton;
    private JTextField idContenedorTextField;
    private JComboBox comboBox2;

    private Hub hub = new Hub();

    public JPanel getHUB() {
        return HUB;
    }
    public UI_Hub(){

        String[] paises = {"Estados Unidos", "Canadá", "México", "Argentina", "Brasil", "Chile", "Colombia", "Perú", "Ecuador", "Venezuela"};
        List<String> listaPaises = new ArrayList<>(Arrays.asList(paises));
        Collections.shuffle(listaPaises);

        mostrarEstadoHub();

        for (String pais : listaPaises) {
            comboBox1.addItem(pais);
            comboBox2.addItem(pais);
        }
        apilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(id_field.getText());
                int peso = Integer.parseInt(peso_field.getText());
                String contenido = contenido_field.getText();
                String remitente = remitente_field.getText();
                String receptor = receptor_field.getText();
                String pais = comboBox1.getSelectedItem().toString();
                int prioridad;
                if(a1RadioButton.isSelected()){
                    prioridad=1;
                }else if(a2RadioButton.isSelected()){
                    prioridad=2;
                }else if(a3RadioButton.isSelected()){
                    prioridad=3;
                }else {
                    prioridad=-1;
                }
                boolean inspeccion;
                if(inspeccionadoEnAduanasCheckBox.isSelected()){
                    inspeccion=true;
                }else {inspeccion=false;}

                Contenedor contenedor = new Contenedor(id,peso,prioridad,pais,contenido,remitente,receptor);

                hub.apilar(contenedor);
                mostrarEstadoHub();
            }
        });
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = nºColumnaTextField.getText();
                int columna = -1;
                try {
                    columna = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(HUB, "Error: columna no válida.");
                    return;
                }

                Contenedor contenedor = hub.desapilar(columna);

                if (contenedor != null) {
                    textArea1.setText(hub.toString());
                    JOptionPane.showMessageDialog(HUB, "Contenedor desapilado correctamente: " + contenedor.getId());
                } else {
                    JOptionPane.showMessageDialog(HUB, "Error: no se encontró ningún contenedor en la columna especificada.");
                }
                mostrarEstadoHub();
            }
        });
        mostrarDatosContenedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idContenedorTextField.getText();
                if(id.isEmpty()) {
                    JOptionPane.showMessageDialog(HUB, "Debe introducir una ID de contenedor", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idInt = Integer.parseInt(id);
                Contenedor c = null;
                // Buscar el contenedor en el hub
                Contenedor[][] conten = hub.getConten();
                for (int i = 0; i < conten.length; i++) {
                    for (int j = 0; j < conten[i].length; j++) {
                        if (conten[i][j] != null && conten[i][j].getId() == idInt) {
                            c = conten[i][j];
                            break;
                        }
                    }
                    if (c != null) break;
                }
                if (c == null) {
                    JOptionPane.showMessageDialog(HUB, "El contenedor con la ID " + id + " no se encuentra en el HUB", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Mostrar los datos del contenedor
                String datosContenedor = "ID: " + c.getId() + "\n"
                        + "Peso: " + c.getPeso() + "\n"
                        + "Contenido: " + c.getContenido() + "\n"
                        + "Remitente: " + c.getEmpresa_emisora() + "\n"
                        + "Receptor: " + c.getEmpresa_receptora() + "\n"
                        + "Prioridad: " + c.getPrioridad() + "\n"
                        + "Inspeccionado: " + c.isInspeccionado();
                JOptionPane.showMessageDialog(HUB, datosContenedor, "Datos del contenedor", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cuantosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pais = (String) comboBox2.getSelectedItem();
                if (pais == null || pais.isEmpty()) {
                    JOptionPane.showMessageDialog(HUB, "Error: debe seleccionar un país.");
                    return;
                }
                List<Integer> contenedoresEnPais = new ArrayList<>();
                Contenedor[][] conten = hub.getConten();
                for (int i = 0; i < conten.length; i++) {
                    for (int j = 0; j < conten[i].length; j++) {
                        Contenedor c = conten[i][j];
                        if (c != null && c.getPais_procedencia().equals(pais)) {
                            contenedoresEnPais.add(c.getId());
                        }
                    }
                }
                if (contenedoresEnPais.isEmpty()) {
                    JOptionPane.showMessageDialog(HUB, "No hay contenedores en el país especificado.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Integer id : contenedoresEnPais) {
                        sb.append(id).append("\n");
                    }
                    JOptionPane.showMessageDialog(HUB, sb.toString());
                }
            }
        });
    }
    private void mostrarEstadoHub(){
        textArea1.setText(hub.toString());
    }
}

