package Grupo_6;

public class Hub {
    private Contenedor[][] conten;

    public Hub(){
        this.conten = new Contenedor[10][12];
    }

    //GET y SET
    public Contenedor[][] getConten() {
        return conten;
    }

    public void setConten(Contenedor[][] conten) {
        if (conten != null) {
            this.conten = conten;
        }
    }

    //TO STRING
    public String toString() {
        String cadena = new String();
        for (int i = 0; i < conten.length; i++) {        //FILAS
            for (int j = 0; j < conten[i].length; j++) { //COLUMNAS DE LA FILA i
                if (conten[i][j] == null) {
                    cadena+="[N]";
                }
                else{
                    cadena+="["+conten[i][j].getId()+"]";
                }
            }
            cadena+="\n";
        }
        return cadena;
    }

    //MÉTODOS
    public void apilar(Contenedor c){
        if(c!=null){
            int p = c.getPrioridad();

            if(p==1 || p==2){ //Prioridad 1 y 2
                int col = p-1;
                for (int i = conten.length-1; i >= 0; i--) {
                    if(conten[i][col]==null){
                        conten[i][col]=c;
                    }
                }
            }
            else{ //Prioridad 3
                for (int i = conten.length-1; i < p-1; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(conten[i][j]==null){
                            conten[i][j]=c;
                        }
                    }
                }
            }
        }
    }

    public Contenedor desapilar(int col){
        if(col<0 || col>=conten[0].length){
            return null;
        }

        for (int i = 0; i < conten.length; i++) {
            if(conten[i][col]!=null){
                Contenedor aux = conten[i][col]; //Copia
                conten[i][col]=null;             //Lo borro
                return aux;
            }
        }
        return null;
    }
}