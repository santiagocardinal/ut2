package com.example.ABB;

import java.util.function.Consumer;

public interface TDAArbolBinario<T> {
    T buscar(Comparable<T> predicate);
    TDAElemento<T> obtenerRaiz();
    boolean eliminar(Comparable<T> criterioBusqueda);
    boolean insertar(Comparable<T> dato);
    void inOrder(Consumer<T> consumidor);
    void preOrder(Consumer<T> consumidor);
    void postOrder(Consumer<T> consumidor);
    boolean esVacio();
    int cantidadNodos();
    int cantidadHojas();
    int cantidadNodosInternos();
}

