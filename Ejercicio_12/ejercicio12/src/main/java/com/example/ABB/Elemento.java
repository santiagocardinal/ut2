package com.example.ABB;

import java.util.function.Consumer;

public class Elemento<T> implements TDAElemento<T>{
private TDAElemento<T> hijoIzq;
    private TDAElemento<T> hijoDer;
    private T dato;

    public Elemento(T dato) {
        this.dato = dato;
    }

    /**
     * Asigna el nodo izquierdo del nodo actual. Puede ser nulo.
     */
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzq = hijoIzquierdo;
    }

    /**
     * Asigna el nodo derecho del nodo actual. Puede ser nulo.
     */
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDer = hijoDerecho;
    }

    /**
     * Devuelve el hijo derecho del nodo actual. El valor es nulo si no tiene
     * hijo derecho.
     */
    public TDAElemento<T> getHijoIzquierdo() {
        return (this.hijoIzq);
    }

    /**
     * Devuelve el hijo izquierdo del nodo actual. El valor es nulo si no tiene
     * hijo izquierdo.
     */
    public TDAElemento<T> getHijoDerecho() {
        return (this.hijoDer);
    }

    /**
     * Actualiza el dato del nodo actual.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * devuelve el dato del nodo actual.
     */
    public T getDato() {
        return (dato);
    }

    /**
     * Busca un nodo por un criterio de búsqueda. Si no se encuentra, retorna
     * nulo.
     */
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) { //PROBADO
        int comparacion = criterioBusqueda.compareTo(this.getDato());
        if (comparacion == 0) {
            return (this);
        }
        if (comparacion < 0 && this.getHijoIzquierdo() != null) {
            TDAElemento<T> resultado = this.getHijoIzquierdo().buscar(criterioBusqueda);
            if (resultado != null) {
                return (resultado);
            }
        }
        if ((comparacion > 0 && this.getHijoDerecho() != null)) {
            TDAElemento<T> resultado = this.getHijoDerecho().buscar(criterioBusqueda);
            if (resultado != null) {
                return (resultado);
            }
        }
        return (null);
    }

    /**
     * Elimina un nodo del árbol según el criterio de búsqueda. Si se encuentra,
     * se retorna el nodo borrado. En otro caso retornar null.
     */
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) { //PROBADO
        if (criterioBusqueda.compareTo(this.getDato()) == 0) {
            if (this.getHijoIzquierdo() == null && this.getHijoDerecho() == null) { //nodo hoja
                return null;
            }
            if (this.getHijoIzquierdo() == null) { //si solo tiene hijo derecho
                return this.getHijoDerecho();
            }
            if (this.getHijoDerecho() == null) { //si solo tiene hijo izquierdo
                return this.getHijoIzquierdo();
            }
            TDAElemento<T> reemplazo=this.getHijoIzquierdo();
            TDAElemento<T> padreAux = this;
            TDAElemento<T> aux = reemplazo;
            while (aux.getHijoDerecho() != null) {
                padreAux = aux;
                aux = aux.getHijoDerecho();
            }
            // copiar dato
            this.setDato(aux.getDato());
            // eliminar el nodo duplicado
            if (padreAux == this) {
                padreAux.setHijoIzquierdo(aux.getHijoIzquierdo());
            } else {
                padreAux.setHijoDerecho(aux.getHijoIzquierdo());
            }
            return this;
        }
        if (this.getHijoIzquierdo() != null) {
            this.setHijoIzquierdo(this.getHijoIzquierdo().eliminar(criterioBusqueda));
        }

        if (this.getHijoDerecho() != null) {
            this.setHijoDerecho(this.getHijoDerecho().eliminar(criterioBusqueda));
        }

        return this;
    }

    /**
     * Agrega un nuevo elemento al árbol Si el nuevoDato existe, no se agrega
     */
    public boolean insertar(Comparable<T> nuevoDato) { //PROBADO (ambos)
        if (this.getHijoIzquierdo() == null) {
        this.setHijoIzquierdo(new Elemento<T>((T) nuevoDato));
        return true;
    }
    if (this.getHijoDerecho() == null) {
        this.setHijoDerecho(new Elemento<T>((T) nuevoDato));
        return true;
    }
    if (this.getHijoIzquierdo().cantidadNodos() <= this.getHijoDerecho().cantidadNodos()) {
        return this.getHijoIzquierdo().insertar(nuevoDato);
    }
    return this.getHijoDerecho().insertar(nuevoDato);
    }

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.inOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * }); }
     */
    public void inOrder(Consumer<TDAElemento<T>> consumidor) { //PROBADO
        if (this.hijoIzq != null) {
            this.hijoIzq.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (this.hijoDer != null) {
            this.hijoDer.inOrder(consumidor);
        }
    }

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.preOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * }); }
     */
    public void preOrder(Consumer<TDAElemento<T>> consumidor) { //PROBADO
        consumidor.accept(this);
        if (this.hijoIzq != null) {
            this.hijoIzq.preOrder(consumidor);
        }
        if (this.hijoDer != null) {
            this.hijoDer.preOrder(consumidor);
        }
    }

    /**
     * {@snippet :
     * // ejemplo de uso
     * elemento.postOrder(dato ->{
     *     // procesar dato
     *     // esta función se llama tantas veces como nodos halla en el árbol
     * }); }
     */
    public void postOrder(Consumer<TDAElemento<T>> consumidor) { //PROBADO
        if (this.hijoIzq != null) {
            this.hijoIzq.postOrder(consumidor);
        }
        if (this.hijoDer != null) {
            this.hijoDer.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    /**
     * retornar true si el nodo es hoja
     */
    public boolean esHoja() { //PROBADO
        if ((this.hijoDer == null) && (this.hijoIzq == null)) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * retorna la cantidad de nodos que son hojas
     */
    public int cantidadHojas() { //PROBADO
        if (this.esHoja()) {
            return (1);
        }
        int total = 0;
        if (this.getHijoIzquierdo() != null) {
            total += this.getHijoIzquierdo().cantidadHojas();
        }
        if (this.getHijoDerecho() != null) {
            total += this.getHijoDerecho().cantidadHojas();
        }
        return total;
    }

    /**
     * retorna la cantidad de nodos que no son hojas
     */
    public int cantidadNodosInternos() { //PROBADO
        int total = 0;
        if ((this.getHijoIzquierdo() == null) && (this.getHijoDerecho() == null)) {
            return (0);
        }
        if (this.getHijoIzquierdo() != null) {
            total = total + this.getHijoIzquierdo().cantidadNodosInternos();
        }
        total += 1;
        if (this.getHijoDerecho() != null) {
            total = total + this.getHijoDerecho().cantidadNodosInternos();
        }
        return (total);
    }

    /**
     * retorna la cantidad de nodos que los compone
     */
    public int cantidadNodos() { //PROBADO
        int total = 0;
        if (this.esHoja()) {
            return (1);
        }
        if (this.getHijoIzquierdo() != null) {
            total = total + this.getHijoIzquierdo().cantidadNodos();
        }
        total += 1;
        if (this.getHijoDerecho() != null) {
            total = total + this.getHijoDerecho().cantidadNodos();
        }
        return (total);
    }

    public int altura() { //PROBADO
        if (this.esHoja()) {
            return (0); //si el nodo no tiene hijos la altura es cero
        }
        int total = 0;
        if (this.getHijoDerecho() != null && this.getHijoIzquierdo() != null) {
            total += Math.max(this.hijoIzq.altura() + 1, this.hijoDer.altura() + 1); //retorna la altura del hijo con más altura +1, se calcula con recursividad hasta llegar a una hoja (desde ahi se le va sumando uno a cada flechita)
        } else {
            if (this.getHijoIzquierdo() != null) {
                total += this.getHijoIzquierdo().altura() + 1;
            }
            if (this.getHijoDerecho() != null) {
                total += this.getHijoDerecho().altura() + 1;
            }
        }
        return (total);
    }

    public int obtenerNivel(Comparable<T> criterioBusqueda) { //PROBADO
        if (criterioBusqueda.compareTo(this.getDato()) == 0) {
            return 0;
        }
        if (this.getHijoIzquierdo() != null) {
            int nivelIzq = this.getHijoIzquierdo().obtenerNivel(criterioBusqueda);
            if (nivelIzq != -1) {
                return nivelIzq + 1;
            }
        }
        if (this.getHijoDerecho() != null) {
            int nivelDer = this.getHijoDerecho().obtenerNivel(criterioBusqueda);
            if (nivelDer != -1) {
                return nivelDer + 1;
            }
        }
        return -1;
    }
}
