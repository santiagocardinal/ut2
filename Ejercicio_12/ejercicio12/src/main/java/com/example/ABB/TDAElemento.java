package com.example.ABB;

import java.util.function.Consumer;

public interface TDAElemento<T> {
    void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo);
    void setHijoDerecho(TDAElemento<T> hijoDerecho);
    TDAElemento<T> getHijoIzquierdo();
    TDAElemento<T> getHijoDerecho();
    void setDato(T dato);
    T getDato();
    TDAElemento<T> buscar(Comparable<T> criterioBusqueda);
    TDAElemento<T> eliminar(Comparable<T> criterioBusqueda);
    boolean insertar(Comparable<T> nuevoDato);
    void inOrder(Consumer<TDAElemento<T>> consumidor);
    void preOrder(Consumer<TDAElemento<T>> consumidor);
    void postOrder(Consumer<TDAElemento<T>> consumidor);
    boolean esHoja();
    int cantidadHojas();
    int cantidadNodosInternos();
    int cantidadNodos();
    int altura();
    int obtenerNivel(Comparable<T> criterioBusqueda);
}
