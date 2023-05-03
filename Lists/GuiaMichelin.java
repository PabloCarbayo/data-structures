package listas;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.*;
import java.lang.*;

/*********************************************************************
*
* Class Name: GuiaMichelin
* Author/s name: G7
* Release/Creation date:20-10-22
* Class version: 1.0
* Class description: En esta clase tenemos el cÃ³digo principal del programa 
* donde tenemos el 'main'. Tambien estan presentes los métodos necesarios para 
* poder comparar los establecimientos, insertarlos en la lista y obtener los distintos tops. 
*
**********************************************************************
*/

public class GuiaMichelin{
    private static File archivo= new File("2022_23_LabTask_Lists_Entrada.csv");
    private static Scanner sc,teclado=new Scanner(System.in);
    private static List<Elemento> topGustosPersonales,top10CRAwards,bestCafeAwards,archivoAux,listaAux;
    
    /**********************************************************************
     * Method name: main
     *
     * Description of the Method:En este main leemos el archivo y luego invocamos el método menú
     *
     * Required Files: Requiere el fichero de los establecimientos de la comunidad
     *********************************************************************/

    public static void main(String[] args) throws FileNotFoundException {
        try {
            lecturaArchivo(archivo);
            menu();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
    }

    /**********************************************************************
     * Method name: topGustos
     *
     * Description of the Method: Lee un número por pantalla para determinar los parámetros para
     * invocar el método filtrarPorTipo con ayuda de un switch. Al elegir por ejemplo restaurantes,
     * eliminamos de la lista todos los locales que sean distintos a Restaurantes
     *
     * Calling arguments:List<Elemento> archivoAux
     *
     * Return value: topGustosPersonales
     *
     * Required Files: 2022_23_LabTask_Lists_Entrada
     *
     *********************************************************************/
    
    public static List<Elemento> topGustos(List<Elemento> archivoAux){
        System.out.println("Introduce el tipo de local\n1.Restaurantes\n2.Bares\n3.Cafeterías"); 
        int opcionElegida=teclado.nextInt();
        while(opcionElegida<1 || opcionElegida>3) { 
            System.out.println("Debe introducir una opcion entre 1 y 3");
            opcionElegida=teclado.nextInt();
        }
        switch (opcionElegida) {
            case 1: { 
                topGustosPersonales=filtrarPorTipo(archivoAux,"Restaurantes");

                break;

            }

            case 2: {
                topGustosPersonales=filtrarPorTipo(archivoAux,"Bares");

                break;
            }
            case 3: {
                topGustosPersonales=filtrarPorTipo(archivoAux,"Cafeterías");

            }
            break;

        }

        System.out.println("Introduce la provincia \n1.Toledo\n2.Ciudad Real\n3.Albacete\n4.Cuenca\n5.Guadalajara"); //elegimos entre provicias
        opcionElegida=teclado.nextInt();
        while(opcionElegida<1 || opcionElegida>6) { //Comprobamos que la opción sea 1,2,3,4,5 o 6
            System.out.println("Debe introducir una opcion entre 1 y 6");
            opcionElegida=teclado.nextInt();
        }
        switch (opcionElegida) {
            case 1: {
                topGustosPersonales=filtrarPorProvincia(topGustosPersonales,"TOLEDO");
                break;

            }
            case 2: {
                topGustosPersonales=filtrarPorProvincia(topGustosPersonales,"CIUDAD REAL");
                break;

            }
            case 3: {
                topGustosPersonales=filtrarPorProvincia(topGustosPersonales,"ALBACETE");
                break;

            }
            case 4: {
                topGustosPersonales=filtrarPorProvincia(topGustosPersonales,"CUENCA");
                break;

            }
            case 5: {
                topGustosPersonales=filtrarPorProvincia(topGustosPersonales,"GUADALAJARA");
                break;
            }

        }
        return topGustosPersonales;

    }


    /**********************************************************************
     * Method name: top10
     *
     * Description of the Method: Crea una lista con el top 10 de establecimientos según 
     * el filtro aplicado mediante el método filtrarPorProvincia y un Comparator
     * 
     * Calling arguments:List<Elemento> archivoAux
     * 
     * Return value: listaAux
     *
     * Required Files: 2022_23_LabTask_Lists_Entrada
     *
     *********************************************************************/
    
    public static List<Elemento> top10(List<Elemento> archivoAux){
        listaAux=new ArrayList<Elemento>();
        top10CRAwards=filtrarPorProvincia(archivoAux, "CIUDAD REAL");
        top10CRAwards.sort(Comparator.comparing(Elemento::getVisitas).reversed());
        for(int i=0;i<10;i++) {
            listaAux.add(top10CRAwards.get(i));
        }
        return listaAux;
    }
    
    /**********************************************************************
     * Method name: bestCafe
     *
     * Description of the Method: Mediante el método filtrarPorTipo y el comparator añadimos 
     * las cafeterías correspondientes a la listaAux
     * 
     * Calling arguments: List<Elemento> archivoAux
     * 
     * Return value: listaAux
     *
     * Required Files:2022_23_LabTask_Lists_Entrada
     *
     *********************************************************************/
    
    public static List<Elemento> bestCafe(List<Elemento> archivoAux){
        listaAux=new ArrayList<Elemento>();
        bestCafeAwards=filtrarPorTipo(archivoAux, "Cafeterías");
        bestCafeAwards.sort(Comparator.comparing(Elemento::getVisitas).reversed());
        for(int i=0;i<10;i++) {
            listaAux.add(bestCafeAwards.get(i));
        }
        return listaAux;
    }


    /**********************************************************************
     * Method name: menu
     *
     * Description of the Method: Lee un número por pantalla y según la opción elegida muestra una lista u otra
     * invocando el método mostrarLista 
     *
     * Required Files: 2022_23_LabTask_Lists_Entrada
     *
     *********************************************************************/
    
    public static void menu() {
        System.out.println("Bienvenido a los tops de la Guía Michelín. Elige la opción que quiere revisar");
        System.out.println("1. Top gustos personales \n2. Top 10 CR Awards\n3. Best Cafe Awards");
        System.out.println("Introduce por teclado la opción elegida");
        int opcionElegida=teclado.nextInt();
        while(opcionElegida<1 || opcionElegida>3) {
            System.out.println("Debe introducir una opcion entre 1 y 3");
            opcionElegida=teclado.nextInt();
        }
        switch (opcionElegida) {
            case 1: {
                mostrarLista(topGustos(archivoAux));
                break;
            }
            case 2: {
                mostrarLista(top10(archivoAux));
                break;
            }
            case 3: {
                mostrarLista(bestCafe(archivoAux));
                break;

            }

        }
    }


    /**********************************************************************
     * Method name: lecturaArchivo
     *
     * Description of the Method: Lee el archivo y diferencia los tipos de locales
     * con ayuda de tokens
     * 
     * Calling arguments: File archivo
     * 
     *  Return value: archivoAux
     *
     * Required Files: 2022_23_LabTask_Lists_Entrada
     *
     *********************************************************************/
    
    public static List<Elemento> lecturaArchivo(File archivo) throws FileNotFoundException{
        archivoAux=new ArrayList<Elemento>();
        sc=new Scanner(archivo);
        String tokens[];
        while(sc.hasNext()) {
            tokens=sc.nextLine().split(";");
            if(tokens[0].equals("Restaurantes")||tokens[0].equals("Bares")||tokens[0].equals("Cafeterías")) {
                archivoAux.add(new Elemento(tokens[0],tokens[1],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Integer.parseInt(tokens[2])));}
            else {
                sc.nextLine();
            }
        }
        sc.close();
        return archivoAux;
    }
    
    /**********************************************************************
     * Method name: mostrarLista
     *
     * Description of the Method: muestra por pantalla la lista que hemos creado con 
     * los filtros específicos
     * 
     * Calling arguments: List<Elemento> a
     *
     * Required Files: no se trabaja con archivos
     *
     *********************************************************************/
    
    public static void mostrarLista(List<Elemento> a) {
        if(a.isEmpty())
            System.out.println("La lista está vacía");//muestra este mensaje si no hay locales acordes con el filtro
        else {
            for(int i=0;i<a.size();i++)
                System.out.println(a.get(i).toString());
        }
    }




    /**********************************************************************
     * Method name: filtrarPorTipo
     *
     * Description of the Method: Utilizamos el método stream.filter para comparar los distintos establecimientos
     *
     * Calling arguments:List<Elemento> list y tipo de tipo String
     * 
     * Return value: una lista con los establecimienos tras aplicar el filtro tipo
     * 
     * Required Files: no se trabaja con archivos
     *
     *********************************************************************/ 
    
    private static List<Elemento> filtrarPorTipo(List<Elemento> list,String tipo) {
        return list.stream().filter(x -> x.getTipo().equals(tipo)).collect(Collectors. toList());
    }

    /**********************************************************************
     * Method name: filtrarPorProvincia
     *
     * Description of the Method: Utilizamos el método stream.filter para comparar 
     * las distintas provincias
     * 
     * Calling arguments:List<Elemento> a y provincia de tipo String
     * 
     * Return value: una lista con los establecimienos tras aplicar el filtro provincia
     *
     * Required Files: no se trabaja con archivos
     *
     *********************************************************************/
    
    public static List<Elemento> filtrarPorProvincia(List<Elemento> a,String provincia){
        return a.stream().filter(x -> x.getProvincia().equals(provincia)).collect(Collectors. toList());
    }

}


