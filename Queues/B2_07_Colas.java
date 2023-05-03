/**********************************************************************
* Class Name: B2_07_Listas.java
* Author/s name: B2-07.
* Release/Creation date: 11/11/2022
* Class version: 1.0
* Class description: Lógica para repartir los pacientes en la cola
* correspondiente de su área para su tratamiendo.
***********************************************************************/

package b2_07_colas;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class B2_07_Colas {
	
	//Variables
	static Queue<B2_07_Paciente> neurology = new ArrayBlockingQueue<B2_07_Paciente>(6); //Cola estatica de neurologia de maximo 6 pacientes
	static Queue<B2_07_Paciente> traumatology = new LinkedBlockingQueue<B2_07_Paciente>(); //Cola dinamica de trauma para N pacientes
	static Queue<B2_07_Paciente> cardiology = new PriorityQueue<B2_07_Paciente>(); //Cola prioriaria de cardiologia para N pacientes
	
	//Metodo Main
	public static void main(String[] args) {
			readFile();		//Leemos el fichero y lo almacenamos en 3 colas correspondiente a las 3 areas
}
	
	//Metodos
	public static void readFile (){
		/**********************************************************************
		* Method name: readFile
		*
		* Description of the Method: Lee los datos del fichero y los introduce
		* en la cola correspondiente, dependiendo del area.
		*
		* Required Files: Requiere el fichero triaje.txt, el cual se debe colocar
		* en la carpeta del proyecto.
		*
		*********************************************************************/
		
		File fichero = new File("triaje.txt");	//Fichero con los datos de los pacientes.
		Scanner sc;
		boolean exceso = false;
		
		try {
		sc = new Scanner(fichero);
		String[] campos;						//Array para almacenar cada palabra de la linea para luego compararla.

		while (sc.hasNextLine()) {
			campos = sc.nextLine().split(";");	//Separo cada palabra de la linea, metiendola en un array.
			switch(campos[1]) {
				case "traumatology":
					traumatology.offer(new B2_07_Paciente(campos[0], campos[1], campos[2]));
					break;
				case "neurology":
					neurology.add(new B2_07_Paciente(campos[0], campos[1], campos[2]));
					break;
				case "cardiology":
					cardiology.offer(new B2_07_Paciente(campos[0], campos[1], campos[2]));
					break;
			}
		}
		sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IllegalStateException e2) {  //En caso de que haya mas de 6 pacientes para neurologia,
			exceso = true;					  //se derivaran a otros hospitales.
		}
		
		//Impresion de resultados por pantalla
		System.out.println("*************************");
		System.out.println("* Área de traumatología *");
		System.out.println("*************************");
		while(!traumatology.isEmpty())
			System.out.println(traumatology.poll());
		
		System.out.println("**********************");
		System.out.println("* Área de neurología *");
		System.out.println("**********************");;
		while(!neurology.isEmpty())
			System.out.println(neurology.poll());
		
		if (exceso)
			System.out.println("Se ha llegado al maximo de pacientes de neurologia por dia (6). El resto seran derivados a otro hospital.");
		
		System.out.println("***********************");
		System.out.println("* Área de cardiología *");
		System.out.println("***********************");
		while(!cardiology.isEmpty())
			System.out.println(cardiology.poll());


	}
}
