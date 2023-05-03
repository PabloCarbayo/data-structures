package main;

/************************************************************************************************
 * 
 * Nombre de Clase: Relacion
 * Nombre de autores: Pablo Carbayo, Daniel Aguado y Juan de Dios Carrera
 * Fecha de creacion: 10/12/2022
 * Version de la Clase: 1.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir definir todos los atributos 
 * que van a tener las relaciones, estos atributos los obtendremos del fichero. 
 * 
 ************************************************************************************************/

public class Relacion {
    private String source;
    private String target;
    private int value;

    public Relacion(String source, String destiny, int value) {
        this.source = source;
        this.target = destiny;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int  getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "Relación{" + "source='" + source + '\'' + ", destiny='" + target + '\'' + ", value=" + value + '}';
    }
}
