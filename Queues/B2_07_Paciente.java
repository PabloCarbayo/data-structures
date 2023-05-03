/**********************************************************************
* Class Name: Paciente.java
* Author/s name:  B2-07.
* Release/Creation date: 11/11/2022
* Class version: v1.0
* Class description: Guarda los datos de los pacientes que meteremos en
* las colas
***********************************************************************/
package b2_07_colas;

public class B2_07_Paciente implements Comparable<B2_07_Paciente>{
	
	//Variables
	private String id;
	private String area;	
	private String gravedad;
	
	//Constructores
	public B2_07_Paciente () {
	}
	public B2_07_Paciente (String i, String a, String g) {
		/**********************************************************************
		* Method name: Paciente (constructor).
		* 
		* Description of the Method: Da de alta a un paciente con un ID, area en
		* la cual va a ser hospitalizado y su gravedad.
		*
		* Calling arguments: int i -> Identificador del paciente.
		* 					 String a -> Area en la cual se va a hospitalizar. 
		* 					 String g -> Gravedad del paciente.
		*********************************************************************/
		this.id = i;
		this.area = a;	
		this.gravedad = g;	
	}

	//Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}
	
	public String toString() {
		return "PACIENTE: "+id+" HOSPITALIZADO EN: "+area+". GRAVEDAD: "+gravedad+"\n";
	}
	public int compareTo(B2_07_Paciente p) { 
		//Variable
		int prio;	//Valor de la prioridad
		
		prio = this.getGravedad().compareTo(p.getGravedad()); //Como el orden alfabeticamente de la primera letra
															  //de cada palabra coincide con la prioridad al reves,
															  //usamos un compareTo de strings para resolver la prioridad
		
		return prio*(-1); //Retornamos el contrario, pues el que esta al final del alfabeto es el mas prioritario
	}
}
