package com.example;

import java.util.ArrayList;
import java.util.List;
import com.example.arbolavl.Insercion;
import com.example.arbolavl.Telemento;

public class RegistroNaves {

    // Nave implementa Comparable para que Telemento<Nave> pueda ordenarla por código
    public static class Nave implements Comparable<Nave> {
        private final int codigo;
        private final String clase;
        private final int combustible;

        public Nave(int codigo, String clase, int combustible) {
            this.codigo = codigo;
            this.clase = clase;
            this.combustible = combustible;
        }

        public int getCodigo()      { return codigo; }
        public String getClase()    { return clase; }
        public int getCombustible() { return combustible; }

        // Criterio de orden del árbol AVL: se compara por código de nave
        @Override
        public int compareTo(Nave otra) {
            return Integer.compare(this.codigo, otra.codigo);
        }

        @Override
        public String toString() {
            return "(" + codigo + ", \"" + clase + "\", " + combustible + ")";
        }
    }

    // Raíz del árbol AVL genérico; la comparación usa Nave.compareTo (por código)
    private Telemento<Nave> raiz;

    public RegistroNaves() {
        this.raiz = null;
    }

    // Inserta una nave en el árbol AVL
    public void insertar(Nave nave) {
        this.raiz = Insercion.insertar(this.raiz, nave);
    }

    // Recorre el árbol en inorden y retorna la lista de naves explorador
    private List<Nave> recolectarExplorador(Telemento<Nave> nodo) {
        if (nodo == null) return new ArrayList<>();
        List<Nave> resultado = new ArrayList<>();
        resultado.addAll(recolectarExplorador(nodo.getHijoIzq()));
        if ("Explorador".equals(nodo.getDato().getClase())) {
            resultado.add(nodo.getDato());
        }
        resultado.addAll(recolectarExplorador(nodo.getHijoDer()));
        return resultado;
    }

    // Devuelve una lista con los códigos de todas las naves explorador
    public List<Integer> obtenerCodigosExploradoras() {
        List<Nave> exploradoras = recolectarExplorador(this.raiz);
        List<Integer> codigos = new ArrayList<>();
        for (Nave n : exploradoras) {
            codigos.add(n.getCodigo());
        }
        return codigos;
    }

    // Devuelve el promedio de combustible de las naves explorador
    public double promedioCombustibleExplorador() {
        List<Nave> exploradores = recolectarExplorador(this.raiz);
        if (exploradores.isEmpty()) {
            return 0.0;
        }
        long suma = 0;
        for (Nave n : exploradores) {
            suma += n.getCombustible();
        }
        return (double) suma / exploradores.size();
    }

    // Imprime el árbol en preorden mostrando altura y factor de balance de cada nodo
    public void imprimirEnPreorden() {
        imprimirRec(this.raiz, 0);
    }

    private void imprimirRec(Telemento<Nave> n, int nivel) {
        if (n == null) return;
        // getAltura() y obtenerBalance() vienen de Telemento, ya no se calculan aquí
        System.out.println(n.getDato() );
        imprimirRec(n.getHijoIzq(), nivel + 1);
        imprimirRec(n.getHijoDer(), nivel + 1);
    }

}
