package com.example.ABB;

public class ABB<T> extends ArbolBinario<T>{

    public ABB() {

    }

    public ABB(TDAElemento<T> raiz) {
        super(raiz);
    }

    @Override
    public T buscar(Comparable<T> predicate) { 
        if (this.raiz==null) {
            return(null);
        } else {
            return((ElementoABB<T>)this.raiz).buscar(predicate).getDato();
        }
    }

    @Override
    public boolean insertar(Comparable<T> dato) { 
        if (raiz == null) {
            raiz = new ElementoABB<T>((T) dato);
            return (true);
        }
        else {
            return(((ElementoABB<T>)this.raiz).insertar(dato));
        }
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        TDAElemento<T> padre = null;
        TDAElemento<T> actual = raiz;
        while (actual != null && criterioBusqueda.compareTo(actual.getDato()) != 0) {
            padre = actual;
            if (criterioBusqueda.compareTo(actual.getDato()) < 0) {
                actual = actual.getHijoIzquierdo();
            } else {
                actual = actual.getHijoDerecho();
            }
        }
        if (actual == null) {
            return false;
        }
        if (actual.getHijoIzquierdo() == null || actual.getHijoDerecho() == null) {

            TDAElemento<T> nuevo;

            if (actual.getHijoIzquierdo() != null) {
                nuevo = actual.getHijoIzquierdo();
            } else {
                nuevo = actual.getHijoDerecho();
            }
            if (padre == null) {
                raiz = nuevo;
            } else if (padre.getHijoIzquierdo() == actual) {
                padre.setHijoIzquierdo(nuevo);
            } else {
                padre.setHijoDerecho(nuevo);
            }
        } else {
            TDAElemento<T> padreAux = actual;
            TDAElemento<T> temp = actual.getHijoIzquierdo();

            while (temp.getHijoDerecho() != null) { //busca el mayor del subárbol izquierdo (inmediato anteiror), va lo mas a la derecha posible dentro del hijo izq
                padreAux = temp;
                temp = temp.getHijoDerecho();
            }

            // copiar dato
            actual.setDato(temp.getDato());

            // eliminar el nodo duplicado
            if (padreAux == actual) {
                padreAux.setHijoIzquierdo(temp.getHijoIzquierdo());
            } else {
                padreAux.setHijoDerecho(temp.getHijoIzquierdo());
            }
        }

        return true;
    }
}
