package Grupo_6;

public class Contenedor {
    private final int id;
    private int peso, prioridad;
    private String pais_procedencia, contenido, empresa_emisora, empresa_receptora;
    private boolean inspeccionado;

    //Constructores
    public Contenedor(int id){
        if(id<0) id=-id;
        this.id=id;
        this.prioridad=3;
    }

    public Contenedor(int id, int peso, int prioridad, String pais_procedencia, String contenido, String empresa_emisora, String empresa_receptora){
        this(id);
        this.setPeso(peso);
        this.setPrioridad(prioridad);
        this.pais_procedencia=pais_procedencia;
        this.contenido=contenido;
        this.empresa_emisora=empresa_emisora;
        this.empresa_receptora=empresa_receptora;
    }

    //Get
    public int getPrioridad() {
        return prioridad;
    }
    public int getId() {
        return id;
    }
    public int getPeso() {
        return peso;
    }
    public String getContenido() {
        return contenido;
    }
    public String getEmpresa_emisora() {
        return empresa_emisora;
    }
    public String getEmpresa_receptora() {
        return empresa_receptora;
    }
    public String getPais_procedencia() {
        return pais_procedencia;
    }

    //Set
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public void setEmpresa_emisora(String empresa_emisora) {
        this.empresa_emisora = empresa_emisora;
    }
    public void setEmpresa_receptora(String empresa_receptora) {
        this.empresa_receptora = empresa_receptora;
    }
    public void setPais_procedencia(String pais_procedencia) {
        this.pais_procedencia = pais_procedencia;
    }
    public void setPeso(int peso) {
        if(peso>0){
            this.peso=peso;
        }
    }
    public void setPrioridad(int prioridad) {
        if(prioridad>=-1 && prioridad<=1){
            this.prioridad=prioridad;
        }
    }
    public boolean isInspeccionado() {
        return inspeccionado;
    }

    //toString
    public String toString() {
        String contenedor = new String();
        contenedor= "Contenedor{" +
                "id=" + id +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", pais='" + pais_procedencia + '\'' +
                ", contenido='" + contenido + '\'' +
                ", emisor='" + empresa_emisora + '\'' +
                ", receptor='" + empresa_receptora + '\'';
        if(inspeccionado) contenedor+=", inspeccionado= SI";
        else contenedor+=", inspeccionado= NO";
        return contenedor+'}';
    }
}
