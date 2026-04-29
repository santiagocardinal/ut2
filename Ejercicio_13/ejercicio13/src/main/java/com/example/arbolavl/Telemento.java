package com.example.arbolavl;

public class Telemento<T extends Comparable<T>> {

    // Dato almacenado en el nodo
    private T dato;
    // Hijo izquierdo y derecho del nodo
    private Telemento<T> hijoIzq;
    private Telemento<T> hijoDer;
    // Altura del subárbol enraizado en este nodo
    private int altura;

    // Crea un nodo hoja con el dato dado; altura 1 porque no tiene hijos
    public Telemento(T dato) {
        this.dato = dato;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.altura = 1;
    }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public Telemento<T> getHijoIzq() { return hijoIzq; }
    public void setHijoIzq(Telemento<T> hijoIzq) { this.hijoIzq = hijoIzq; }

    public Telemento<T> getHijoDer() { return hijoDer; }
    public void setHijoDer(Telemento<T> hijoDer) { this.hijoDer = hijoDer; }

    public int getAltura() { return altura; }

    // Devuelve la altura de un nodo, o 0 si es null
    public static int alturaDe(Telemento<?> n) {
        return (n == null) ? 0 : n.altura;
    }

    // Recalcula la altura del nodo en base a la de sus hijos
    public void actualizarAltura() {
        // La altura es 1 (el nodo mismo) más la mayor altura entre sus dos subárboles
        this.altura = 1 + Math.max(alturaDe(this.hijoIzq), alturaDe(this.hijoDer));
    }

    // Devuelve el factor de balance: altura(izq) - altura(der)
    public int obtenerBalance() {
        // Positivo → más cargado a la izquierda; negativo → más cargado a la derecha
        return alturaDe(this.hijoIzq) - alturaDe(this.hijoDer);
    }
}
