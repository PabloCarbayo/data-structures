package main;

import graphsDSESIUCLM.*;
/************************************************************************************************
 * 
 * Nombre de Clase: DecoratedElementRelaciones
 * Nombre de autores: Pablo Carbayo, Daniel Aguado y Juan de Dios Carrera
 * Fecha de creacion: 10/12/2022
 * Version de la Clase: 1.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir donde vamos a guardar los vértices del grafo,
 * es decir, el objeto Personaje y sus atributos de interés para el grafo y su recorrido.
 * 
 ************************************************************************************************/
public class DecoratedElementPersonaje<T> implements Element {
    private String ID;
    private T element;
    private boolean visited;
    private DecoratedElementPersonaje<T> parent;
    private int distance;

    public DecoratedElementPersonaje(String key, T element) {
        ID = key;
        this.element = element;
        visited = false;
        parent = null;
        distance = 0;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public DecoratedElementPersonaje<T> getParent() {
        return parent;
    }

    public void setParent(DecoratedElementPersonaje<T> parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public String toString() {
        return "DecoratedElementPersonaje{" + "ID='" + ID + '\'' + ", visited=" + visited + ", parent=" + parent + ", distance=" + distance + '}';
    }
}
