package main;


/************************************************************************************************
 * 
 * Nombre de Clase: Personaje
 * Nombre de autores: Pablo Carbayo, Daniel Aguado y Juan de Dios Carrera
 * Fecha de creacion: 10/12/2022
 * Version de la Clase: 1.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir definir todos los atributos 
 * que van a tener los personajes, estos atributos los obtendremos del fichero. 
 * 
 ************************************************************************************************/

public class Personaje {
    private String id;
    private String name;
    private String type;
    private String subtype;
    private String gender;
    private int freqSum;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSubtype() {
        return subtype;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getFreqSum() {
        return freqSum;
    }
    public void setFreqSum(int freqSum) {
        this.freqSum = freqSum;
    }
    public Personaje(String id, String type, String subtype, String name, String gender, int freqSum) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.gender = gender;
        this.freqSum = freqSum;
    }
    public String toString() {
        return "Personaje [id=" + id + ", name=" + name + ", type=" + type + ", subtype=" + subtype + ", gender="
                + gender + ", freqSum=" + freqSum + "]";
    }


}
