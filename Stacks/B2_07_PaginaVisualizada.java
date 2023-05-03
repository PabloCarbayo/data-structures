package b2_07_pilas;

/**********************************************************************
* Class Name: PaginaVisualizada
* Author/s name: B.07
* Release/Creation date: 10/10/2022
* Class version: 1.0
* Class description: Clase que representa una página leida del fichero para ser
* almacenada en la pila
***********************************************************************/

public class B2_07_PaginaVisualizada {
	
	//Variables
	private String enlace;
	private int tiempoVisita;
	
	//Constructor
	public B2_07_PaginaVisualizada(String enlace, int tiempoVisita) { //Constructor de la clase
		this.enlace=enlace;
		this.tiempoVisita=tiempoVisita;
	}
	
	//Getters y Setters
	public String getEnlace() { //Getter de la url
		return enlace;
	}
	public void setEnlace(String enlace) { //Setter de la url
		this.enlace = enlace;
	}
	public int getTiempoVisita() { //Getter del tiempo de visita
		return tiempoVisita;
	}
	public void setTiempoVisita(int tiempoVisita) { //Setter del tiempo de visita
		this.tiempoVisita = tiempoVisita;
	}
	public String toString() {// ToString del objeto
		return "PaginaVisitada [enlace=" + enlace + ", tiempoVisita=" + tiempoVisita + "]";
	}
}
