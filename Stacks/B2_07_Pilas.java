/**********************************************************************
* Class Name: B2_07_Pilas.java
* Author/s name: B.07
* Release/Creation date: 10/10/2022
* Class version: 1.0
* Class description: Simulacion de acceso a las paginas web
***********************************************************************/
package b2_07_pilas;
import java.util.*;
import java.io.*;

public class B2_07_Pilas {
	
	//Variables
    static File archivo =new File("navegacion.txt"); //Ruta del archivo con el flujo de navegación
    static String linea, url;
    static String[] tokens; 
    
    static Stack <B2_07_PaginaVisualizada> flujo=new Stack<B2_07_PaginaVisualizada>(); //Pila del tipo PaginaVisitada para simular el flujo
    static B2_07_PaginaVisualizada paginaVisitada=null;								   //Objeto para mostrar o guardar una pagina
    
    static int tiempo, tiempoTotal=0,contador=0;									   //Contadores para mostrar las estadisticas
    static double tiempoMedio=0;
    
    //Metodo main
    public static void main(String[] args) throws FileNotFoundException {
		/**********************************************************************
		* Method name: main
		*
		* Description of the Method: Simula la traza de una navegacion web y el
		* orden de acceso a las paginas.
		*
		* Required Files: Requiere el fichero navegacion.txt, el cual se debe colocar
		* en la carpeta del proyecto.
		*********************************************************************/
        try {
            Scanner sc = new Scanner(archivo); //Scanner para leer el fichero

            while(sc.hasNext()) { //Comprobamos que haya siguiente línea en el fichero
                linea=sc.next();
                if(linea.equals("<=")) { 						//Comprobamos si hay retorno
                    contador=contador+1; 						//Aumentamos el contador de páginas visitadas
                    flujo.pop().toString();  					//Eliminamos la página de la pila
                    System.out.println(flujo.peek().toString());//Mostramos la pagina accedida
                    paginaVisitada=flujo.peek();				//Tomamos como dato la cima de la pila
                    tiempo=paginaVisitada.getTiempoVisita(); 	//Cogemos como dato el tiempo de la página a la que retornamos
                    tiempoTotal=tiempo+tiempoTotal;				//Sumamos el tiempo de visita al tiempo total
                }
                else {
                    tokens=linea.split(","); 								//Separamos la línea en URL y su tiempo de visita
                    url=tokens[0];
                    tiempo=Integer.parseInt(tokens[1]);
                    paginaVisitada=new B2_07_PaginaVisualizada(url,tiempo); //Creamos el objeto PaginaVisitada
                    System.out.println(flujo.push(paginaVisitada)); 		//Empujamos la nueva página a la pila
                    tiempoTotal=tiempoTotal+tiempo;							//sumamos el tiempo de visita al tiempo total
                    contador=contador+1; 									//aumentamos el contador de páginas visitadas
                }
            }
            
            //Muestra de estadisticas de los accesos a las paginas web
            System.out.println("\n**************");
            System.out.println("*Estadisticas*");
            System.out.println("**************");
            System.out.println("El tiempo total de visita es: "+tiempoTotal+ " segundos");
            System.out.println("El total de páginas que visitamos es: "+contador+" páginas");
            tiempoMedio=(float)tiempoTotal/contador; //Calculamos el tiempo medio de visita
            System.out.println("El tiempo medio de visita es: "+ tiempoMedio+ " segundos");
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Fichero no encontrado");	 //Capturamos la excepción si no se encuentra el fichero
        }

    }





}

