import Grupo_6.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        UI_Hub ui = new UI_Hub(); // crea una instancia de la interfaz de usuario

        JFrame frame = new JFrame("Mi aplicación"); // crea una nueva ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(ui.getHUB()); // agrega el panel HUB a la ventana
        frame.pack(); // ajusta el tamaño de la ventana según su contenido
        frame.setVisible(true); // muestra la ventana
    }
}