package main;

import java.io.*;
import java.util.*;
import graphsDSESIUCLM.*;

/*********************************************************************
*
* Nombre de la clase: B2_07_Grafos
 * Nombre de autores: Pablo Carbayo, Daniel Aguado y Juan de Dios Carrera
 * Fecha de creacion: 10/12/2022
 * Version de la Clase: 1.0
* Descripción de la clase: Esta clase es la clase principal del programa, en ella se creará el grafo y
* la busqueda por anchura(BFS)
* También nos permitirá interactuar completamente con el grafo, utilizando los datos guardados.
* 
*
**********************************************************************
*/
public class B2_07_Grafos {

    final static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> g= new TreeMapGraph<>();
        g=lecturaPersonajes(g, "lotr-pers.csv");
        g=lecturaRelaciones(g, "networks-id-3books.csv");
        menu(g);
    }
    /***********************************************************************
	 * 
	 * Nombre del metodo: menu
	 * Descripcion del metodo: Nos mostrara un menú para elegir las distintas
	 * opciones que puede realizar el programa.
	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
	 * como argumento.
	 * 
	 * **********************************************************************/

    public static void menu(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo) throws InputMismatchException {
        boolean salir=false;
        int opcion;
        while(!salir){

            try {
                mensajeBienvenida();
                opcion=teclado.nextInt();
                while(opcion<1 || opcion>7) {
                    System.out.println("Error. Elige una opción del 1 al 7.");
                    opcion=teclado.nextInt();
                }
                switch (opcion) {
                    case 1: {
                        numPersonajes(grafo);
                        break;
                    }
                    case 2:{
                        numRelaciones(grafo);
                        break;
                    }
                    case 3:{
                        maxRelaciones(grafo);
                        break;
                    }
                    case 4:{
                        parejaMaxRelaciones(grafo);
                    }
                    case 5:{

                        break;
                    }
                    case 6:{
                        enviarMensaje(grafo);
                    }
                    case 7:{
                        salir=true;
                        break;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                teclado.next();
                teclado.nextLine();
            }
        }
    }
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: mensajeBienvenida
   	 * 
   	 * Descripcion del método: Muestra mensajes por pantalla indicando las opciones del menú
   	 *
   	 * Llamada de argumentos: Ninguna 
   	 * **********************************************************************/

    public static void mensajeBienvenida() {
        System.out.println("****************************************");
        System.out.println("Bienvenido al mundo del señor de los anillos\n ¿Qué desea hacer?");
        System.out.println("1.Mostrar número de personajes\n2.Mostrar el número total de relaciones entre personajes");
        System.out.println("3.Mostrar el personaje con más relaciones\n4.Mostrar la pareja con mayor interacción");
        System.out.println("5.Crear un comando\n6.Mandar un mensaje\n7.Salir");
        System.out.println("****************************************");
        System.out.println("Introduce la opción elegida:");
    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: leerFicheroPersonajes
   	 * 
   	 * Descripcion del método: En este método se leera el fichero de los datos
   	 * de los personajes, también se separarán los datos a traves del .split.
   	 * Con estos datos crearemos a los personajes y los introduciremos en el grafo.
   	 * Los personajes serán los vértices del grafo.
   	 *
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * Y la ruta donde esté el fichero de los datos sobre los personajes
   	 * 
   	 * Excepciones: FileNotFoundException, para que salte un error en el caso de 
   	 * que no se encuentre el fichero. 
   	 * 
   	 * **********************************************************************/

    public static Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> lecturaPersonajes(Graph<DecoratedElementPersonaje<Personaje>,
    																												  DecoratedElementRelaciones<Relacion>> grafo, String ruta) throws FileNotFoundException {

        ArrayList<Personaje>listaPersonajes=new ArrayList<Personaje>();
        try {
            File archivo= new File(ruta);
            Scanner lectura = new Scanner(archivo);
            lectura.nextLine(); //Saltamos la primera línea que describe las columnas
            while (lectura.hasNextLine()) {
                String linea = lectura.nextLine();
                String[] tokens = linea.split(";");
                Personaje personaje=new Personaje(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]));
                listaPersonajes.add(personaje); //Añadimos el personaje creado a la lista de personajes
            }
            for(int i=0;i<listaPersonajes.size();i++) {
                DecoratedElementPersonaje<Personaje> elemento=new DecoratedElementPersonaje<Personaje>(listaPersonajes.get(i).getId(),listaPersonajes.get(i));
                grafo.insertVertex(elemento); //Recorremos la lista de personajes, creamos un vértice con el personaje actual de la lista y lo insertamos en el grafo
            }
            lectura.close();
        }catch (FileNotFoundException e) {
            System.out.println("Archivo no encotrado");
        }
        return grafo;
    }
    /***********************************************************************
   	 * Nombre del metodo: leerFicheroRelaciones
   	 * Descripcion del metodo: En este método se leera el fichero de los datos
   	 * de las relaciones entre personajes, también se separarán los datos a traves del .split.
   	 * Con estos datos crearemos las relaciones entre los personajes y las introduciremos en el grafo. 
   	 * Las relaciones serán las aristas del grafo, que unirán los vértices que obtendremos del fichero.
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * Y la ruta donde esté el fichero de los datos sobre las relaciones entre los personaje
   	 * Excepciones: FileNotFoundException, para que salte un error en el caso de 
   	 * que no se encuentre el fichero.
   	 * **********************************************************************/
    public static Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> lecturaRelaciones(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo, String ruta) throws FileNotFoundException{
        ArrayList<Relacion> listaRelaciones= new ArrayList<Relacion>();
        int id=1;
        try {
            File archivo= new File(ruta);
            Scanner lectura = new Scanner(archivo);
            lectura.nextLine(); //Saltamos la primera línea que describe las columnas
            while(lectura.hasNext()) {
                String linea = lectura.nextLine();
                String[] tokens = linea.split(";");
                Relacion relacion=new Relacion(tokens[0],tokens[1],Integer.parseInt(tokens[2])); 
                listaRelaciones.add(relacion); //Creamos el objeto relación y lo añadimos a la lista de relaciones
            }
            for(int i=0;i<listaRelaciones.size();i++) {
                DecoratedElementRelaciones<Relacion> elemento= new DecoratedElementRelaciones<Relacion>(Integer.toString(id),listaRelaciones.get(i).getValue(),listaRelaciones.get(i));
                grafo.insertEdge(grafo.getVertex(listaRelaciones.get(i).getSource()), grafo.getVertex(listaRelaciones.get(i).getTarget()), elemento);
                id++; //Recorremos la lista de relaciones, creamos la relacion y la añadimos al grafo. Aumentamos id para distinguir las relaciones. 
            }
            lectura.close();
        }catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }

        return grafo;
    }
    /***********************************************************************
   	 * 
   	 * Nombre del método: numPersonajes
   	 * Descripción del metodo: Este método lo utilizaremos para obtener el número
   	 * de personajes (vértices) que podemos encontrar en le grafo. Esto se hace
   	 * através de un iterador de los vértices.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/

    public static void numPersonajes(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo) {
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it = grafo.getVertices();
        int cont = 0;

        while (it.hasNext()) {
            ++cont;
            it.next();
        }//Creamos un iterator de personajes y mientras siga habiendo personajes aumentamos en 1 el contador
        System.out.println("El número total de personajes es: " + cont);
    }
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: numRelaciones
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el número
   	 * de relaciones (aristas) que podemos encontrar en le grafo. Esto se hace
   	 * a través de un iterador de las aristas.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    public static void numRelaciones(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo) {
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it = grafo.getEdges();
        int cont = 0;
        while (it.hasNext()) {
            ++cont;
            it.next();
        }//Creamos un iterator de relaciones y mientras haya relaciones en el grafo aumentamos en 1 el contador
        System.out.println("El número total de relaciones entre personajes es: " + cont);
    }
    /***********************************************************************
   	 * 
   	 * Nombre del método: parejaMaxRelaciones
   	 * Descripcion del método: Este método lo utilizaremos para obtener la relación
   	 * que más peso tiene, es decir, la arista que más value tiene.
   	 * Esto lo vamos a conseguir con dos iteradores igual que en el método anterior.
   	 * Pero ahora con el iterador conseguimos la arista que más value tiene, y después
   	 * obtenemos los dos vértices que une esta arista.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/

    public static void parejaMaxRelaciones(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> gr) {
    	Vertex<DecoratedElementPersonaje<Personaje>> aux;
        Vertex<DecoratedElementPersonaje<Personaje>> p1 = null;
        Vertex<DecoratedElementPersonaje<Personaje>> p2 = null;
        Edge<DecoratedElementRelaciones<Relacion>> maxRel = null;
        Edge<DecoratedElementRelaciones<Relacion>> aux1;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it = gr.getVertices();
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it1;
        int max = 0;
        while (it.hasNext()) {
            int i = 0;
            aux = it.next();
            it1 = gr.getEdges();
            while (it1.hasNext()) {
                aux1 = it1.next();
                i = aux1.getElement().getDistance();
                if (i > max) {
                    max = i;
                    maxRel = aux1;
                    p1 = gr.getVertex(maxRel.getElement().getElement().getSource());
                    p2 = gr.getVertex(maxRel.getElement().getElement().getTarget());

                }

            }
        }


        System.out.println("La pareja con el mayor número de interacciones es: " + p1.getElement().getElement().getName() + "-" + p2.getElement().getElement().getName() + " y tienen un total de " + max + " interacciones.");
    }
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: maxRelaciones
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el personaje
   	 * (vértice) que más se relaciona con otros personajes, es decir, el vértice que
   	 * más aristas tiene.
   	 * Esto se hace a trav�s de 2 iteradores, uno de personajes y otro de relaciones. 
   	 * Así vas recorriendo todos los vertices y comprobando cuantas aristas tiene cada uno.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    public static void maxRelaciones(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> gr) {
        Vertex<DecoratedElementPersonaje<Personaje>> aux;
        Vertex<DecoratedElementPersonaje<Personaje>> maxRel = null;
        Edge<DecoratedElementRelaciones<Relacion>> aux1;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it = gr.getVertices();
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it1;
        int max = 0;
        while (it.hasNext()) {
            int i = 0;
            aux = it.next();
            it1 = gr.incidentEdges(aux);
            while (it1.hasNext()) {
                aux1 = it1.next();
                i++;
            }
            if (i > max) {
                max = i;
                maxRel = aux;
            }

        }
        System.out.println("El personaje con más relaciones es: " + maxRel.getElement().getElement().getName() + " y tiene " + max + " relaciones.");
    }
    
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: BFS
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el recorrido por
   	 * anchura(BFS). 
   	 * Esta ejecución se lleva a cabo de una cola. Introduces el primer vértice en la cola, después
   	 * a través de un bucle vamos comprobando los vértices y en el caso de que el valor de la relación
   	 * entre el primer vértice metido en la cola y con el que estamos comprobando sea mayor que 8, lo introducimos
   	 * en la cola y cambiamos el vértice a visitado, además ponemos al primer vértice como el padre del vertice que hemos comprobado,
   	 * y así al final en la cola, por último tenemos, todos los vértices.
   	 * y todos los demás vertices en los cuales su arista es mayor que 8.
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * También se le introduce 2 personajes.
   	 * Retorno: Devuelve un vértice, el que buscas en el bfs, que como tiene todos los padres, se puede sacar
   	 * el camino. 
   	 * **********************************************************************/
    
    public static DecoratedElementPersonaje<Personaje> BFS(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> g, Vertex<DecoratedElementPersonaje<Personaje>> s, Vertex<DecoratedElementPersonaje<Personaje>> t) {
        Queue<Vertex<DecoratedElementPersonaje<Personaje>>> q = new LinkedList<Vertex<DecoratedElementPersonaje<Personaje>>>();
        boolean noEnd = true;
        int valor = 0;
        Vertex<DecoratedElementPersonaje<Personaje>> u, v = null;
        u = null;
        Edge<DecoratedElementRelaciones<Relacion>> e;
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it;

        s.getElement().setVisited(true);
        q.offer(s); //Mete en la cola q
        //        boolean salir = true;
        while (!q.isEmpty() && noEnd) {
            u = q.poll();
            //Quita un elemento de la cola q y lo mete en u
            it = g.incidentEdges(u); //Itera con las edges incidentes de u
            while (it.hasNext() && noEnd) {
                e = it.next(); //Iteramos sobre las edges
                valor = e.getElement().getDistance();
                v = g.opposite(u, e); //v = edge a verificar
                if (!(v.getElement()).isVisited()) { //Si no está visitada

                    if (valor >= 8) {
                        (v.getElement()).setParent(u.getElement());
                        (v.getElement()).setVisited(true);
                        q.offer(v); //Mete el elemento v en la cola
                        noEnd = !(v.getElement().equals(t.getElement()));
                    }

                    // noEnd = se pone a false cuando se termina el algoritmo

                    //}
                }
            }
        }
        if (noEnd) v.getElement().setParent(null);
        return v.getElement();
    }
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: enviarMensaje
   	 * Descripcion del metodo: Este método lo utilizaremos para que el usuario nos diga los 2 vértices
   	 * que quiere comprobar en el camino de los BFS. Se comprueba que los 2 vértices sean correctos.
   	 * En el caso de que estos 2 vértices sean correctos vamos al método de arriba de obtener el vértice.
   	 * Si hay un camino que sea correcto de BFS, devuelve el vértice final con su padre. Vas recorriendo sus padres
   	 * hacia arriba y así obtenemos el camino final.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * Excepciones: NullPointerException para que, cuando no exista puntero, salte un mensaje de error
   	 * **********************************************************************/
    public static void enviarMensaje(Graph<DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo)throws NullPointerException {
        try {
            DecoratedElementPersonaje<Personaje> startNode, endNode, nx, node = null;
            boolean bool1 = true, bool2 = true;
            int size;
            String id1=null, id2=null;
            Vertex<DecoratedElementPersonaje<Personaje>> aux, s = null, t = null;
            Stack<DecoratedElementPersonaje<Personaje>> sp = new Stack<DecoratedElementPersonaje<Personaje>>();
            Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it1,it2;



            String nombre1 = elegirPersonajeMensaje(grafo);
            String nombre2 = elegirPersonajeMensaje(grafo);
            it2 = grafo.getVertices();
            while (it2.hasNext()) {
                aux = it2.next();
                if (aux.getElement().getElement().getName().equals(nombre1)) {
                    id1 = aux.getID();
                }
                if (aux.getElement().getElement().getName().equals(nombre2)) {
                    id2 = aux.getID();
                }
            }

            Vertex<DecoratedElementPersonaje<Personaje>> e = grafo.getVertex(id1);
            Vertex<DecoratedElementPersonaje<Personaje>> x = grafo.getVertex(id2);
            startNode = e.getElement();
            endNode = x.getElement();

            it1 = grafo.getVertices();

            while (it1.hasNext() && (bool1 || bool2)) {

                aux = it1.next();
                nx = aux.getElement();
                if (nx.equals(startNode)) {
                    s = aux;
                    bool1 = false;
                }
                if (nx.equals(endNode)) {
                    t = aux;
                    bool2 = false;
                }
            }

            if (!bool1 || bool2) {
                node = BFS(grafo, s, t);
                
                if (node.getParent() == null) {
                    System.out.println("\nNo existe un camino o algún camino seguro.");
                    it1 = grafo.getVertices();
                    while (it1.hasNext()) {
                        aux = it1.next();
                        aux.getElement().setVisited(false);
                        aux.getElement().setParent(null);
                    }


                } else {

                    while (node.getParent() != null) {
                        sp.push(node);
                        node = node.getParent();
                    }
                    it1 = grafo.getVertices();
                    while (it1.hasNext()) {
                        aux = it1.next();
                        aux.getElement().setVisited(false);
                        aux.getElement().setParent(null);
                    }
                    sp.push(node);

                    size = sp.size();
                    System.out.println("El camino para llegar de " + nombre1 + " a " + nombre2 + " es:");
                    for (int i = 0; i < size - 1; i++) {
                        node = sp.pop();
                        System.out.print(node.getElement().getName() + "----->");
                    }
                    node = sp.pop();
                    System.out.println(node.getElement().getName());
                }
            } else System.out.println("\nUn nodo no está en el grafo.");
        }catch (NullPointerException e) {
            System.out.println("\nError.Escribe nombres válidos");
        }

    }
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: elegirPersonajeMensaje
   	 * Descripcion del metodo: Este método es utilizado para pedir el nombre de los personajes 
   	 * que van a interactuar como origen y destino del mensaje. Primero se comprueba que exista
   	 * un vértice con el nombre que introducimos y luego se comprueba si su tipo es persona o no retornando
   	 * el nombre del vértice si es del tipo persona y si no lo es se imprime un mensaje de error.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    public static String elegirPersonajeMensaje(Graph <DecoratedElementPersonaje<Personaje>, DecoratedElementRelaciones<Relacion>> grafo) {
        System.out.println("\nEscribe el nombre del personaje deseado:\n");
        String nombre=teclado.next();
        Vertex<DecoratedElementPersonaje<Personaje>> aux;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it1;
        it1=grafo.getVertices();
        while (it1.hasNext()) {
            aux = it1.next();
            if (aux.getElement().getElement().getName().equals(nombre)) {
                if(!aux.getElement().getElement().getType().equals("per")) {
                    System.out.println("\nNo es del tipo persona por lo que no es válido\n.");
                    break;
                }else {
                    nombre=aux.getElement().getElement().getName();
                }

            }
        }
        return nombre;
    }
}