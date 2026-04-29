package com.example.arbolavl;

public class Insercion {

    private Insercion() {}

    // Inserta un dato en el árbol AVL, aplica la rotación necesaria y devuelve la nueva raíz
    public static <T extends Comparable<T>> Telemento<T> insertar(Telemento<T> nodo, T dato) {
        // Caso base: posición vacía, se crea un nuevo nodo hoja
        if (nodo == null) {
            return new Telemento<>(dato);
        }

        // Se compara el dato con el del nodo actual para decidir hacia qué lado bajar
        int cmp = dato.compareTo(nodo.getDato());
        if (cmp < 0) {
            // El dato es menor, se inserta en el subárbol izquierdo
            nodo.setHijoIzq(insertar(nodo.getHijoIzq(), dato));
        } else if (cmp > 0) {
            // El dato es mayor, se inserta en el subárbol derecho
            nodo.setHijoDer(insertar(nodo.getHijoDer(), dato));
        } else {
            // El dato ya existe, no se permiten duplicados
            return nodo;
        }

        // Se recalcula la altura del nodo tras la inserción
        nodo.actualizarAltura();
        // Se verifica si el nodo quedó desbalanceado
        int bal = nodo.obtenerBalance();

        //Si bal > 1 subárbol izquierdo más alto; Si bal < -1 subárbol derecho más alto

        // Caso LL: inserción en el hijo izquierdo del hijo izquierdo
        if (bal > 1 && dato.compareTo(nodo.getHijoIzq().getDato()) < 0)
            return Rotaciones.rotacionLL(nodo);

        // Caso RR: inserción en el hijo derecho del hijo derecho
        if (bal < -1 && dato.compareTo(nodo.getHijoDer().getDato()) > 0)
            return Rotaciones.rotacionRR(nodo);

        // Caso LR: inserción en el hijo derecho del hijo izquierdo
        if (bal > 1 && dato.compareTo(nodo.getHijoIzq().getDato()) > 0)
            return Rotaciones.rotacionLR(nodo);

        // Caso RL: inserción en el hijo izquierdo del hijo derecho
        if (bal < -1 && dato.compareTo(nodo.getHijoDer().getDato()) < 0)
            return Rotaciones.rotacionRL(nodo);

        // El nodo esta balanceado
        return nodo;
    }
}
