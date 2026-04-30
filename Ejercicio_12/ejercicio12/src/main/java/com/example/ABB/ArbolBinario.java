package com.example.ABB;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T> {

    protected TDAElemento<T> raiz;

    public ArbolBinario() {

    }

    public ArbolBinario(TDAElemento<T> raiz) {
        this.raiz = raiz;
    }

    public T buscar(Comparable<T> predicate) {
        if (raiz == null) {
            return null;
        }
        return (this.raiz.buscar(predicate).getDato());
    }

    public TDAElemento<T> obtenerRaiz() {
        return (raiz);
    }

    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (this.raiz == null) {
            return (false);
        }
        if (this.raiz.buscar(criterioBusqueda) == null) {
            return (false);
        }
        this.raiz = this.raiz.eliminar(criterioBusqueda);
        return (true);
    }

    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new ElementoABB<T>((T) dato);
            return true;
        }
        return raiz.insertar(dato);
    }

    public void inOrder(Consumer<T> consumidor) {
        if (this.raiz == null) {
            return;
        }
        if (this.raiz.getHijoIzquierdo() != null) {
            new ArbolBinario<>(this.raiz.getHijoIzquierdo()).inOrder(consumidor);
        }
        consumidor.accept(this.raiz.getDato());
        if (this.raiz.getHijoDerecho() != null) {
            new ArbolBinario<>(this.raiz.getHijoDerecho()).inOrder(consumidor);
        }
    }

    public void preOrder(Consumer<T> consumidor) {
        if (this.raiz == null) {
            return;
        }
        consumidor.accept(this.raiz.getDato());
        if (this.raiz.getHijoIzquierdo() != null) {
            new ArbolBinario<>(this.raiz.getHijoIzquierdo()).preOrder(consumidor);
        }
        if (this.raiz.getHijoDerecho() != null) {
            new ArbolBinario<>(this.raiz.getHijoDerecho()).preOrder(consumidor);
        }
    }

    public void postOrder(Consumer<T> consumidor) {
        if (this.raiz == null) {
            return;
        }
        if (this.raiz.getHijoIzquierdo() != null) {
            new ArbolBinario<>(this.raiz.getHijoIzquierdo()).postOrder(consumidor);
        }
        if (this.raiz.getHijoDerecho() != null) {
            new ArbolBinario<>(this.raiz.getHijoDerecho()).postOrder(consumidor);
        }
        consumidor.accept(this.raiz.getDato());
    }

    public boolean esVacio() {
        if (this.raiz == null) {
            return (true);
        } else {
            return (false);
        }
    }

    public int cantidadNodos() {
        if (this.obtenerRaiz() == null) {
            return 0;
        }
        return (this.obtenerRaiz().cantidadNodos());
    }

    public int cantidadHojas() {
        if (this.obtenerRaiz() == null) {
            return 0;
        }
        return (this.obtenerRaiz().cantidadHojas());
    }

    public int cantidadNodosInternos() {
        if (this.obtenerRaiz() == null) {
            return 0;
        }
        return (this.obtenerRaiz().cantidadNodosInternos());
    }

    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        if (this.obtenerRaiz() == null) {
            return -1;
        }
        return raiz.obtenerNivel(criterioBusqueda);
    }
}
