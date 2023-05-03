package listas;

//import java.util.Comparator;

/*********************************************************************
*
* Class Name: GuiaMichelin
* Author/s name: G7
* Release/Creation date:10-11-22
* Class version: 1.0
* Class description: En esta clase es donde definimos las propiedades del
 * objeto Elemento, con un get y un set para cada propiedad. 
*
**********************************************************************
*/

public class Elemento /*implements Comparator<Elemento>*/ {
  //Variables
    private String tipo,nombre,poblacion,provincia; 
    private int visitas,codigoPostal;
    
  //Constructor
    public Elemento(String tipo, String nombre, String poblacion, String provincia, int visitas, int codigoPostal) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.visitas = visitas;
        this.codigoPostal = codigoPostal;
    }
  //Getters y Setters
    public String toString() {
        return "Elemento [tipo=" + tipo + ", nombre=" + nombre + ", poblacion=" + poblacion + ", provincia=" + provincia
                + ", visitas=" + visitas + ", codigoPostal=" + codigoPostal + "]";
    }
    public String getTipo() {//Getter del tipo de establecimiento
        return tipo;
    }
    public void setTipo(String tipo) {//Setter del tipo de establecimiento
        this.tipo = tipo;
    }
    public String getNombre() {//Getter del nombre
        return nombre;
    }
    public void setNombre(String nombre) {//Setter del nombre
        this.nombre = nombre;
    }
    public String getPoblacion() {//Getter de la población
        return poblacion;
    }
    public void setPoblacion(String poblacion) {//Setter de la población
        this.poblacion = poblacion;
    }
    public String getProvincia() {//Getter de la provincia
        return provincia;
    }
    public void setProvincia(String provincia) {//Setter de la provincia
        this.provincia = provincia;
    }
    public int getVisitas() {//Getter de las visitas
        return visitas;
    }
    public void setVisitas(int visitas) {//Setter de las visitas
        this.visitas = visitas;
    }
    public int getCodigoPostal() {//Getter del código postal
        return codigoPostal;
    }
    public void setCodigoPostal(int codigoPostal) {//Setter del código postal
        this.codigoPostal = codigoPostal;
    }



}

