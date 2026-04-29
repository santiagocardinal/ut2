package com.example.arbolavl;

public class Rotaciones {
    private Rotaciones() {}

    // Rotación simple a la derecha (caso LL)
    // Se usa cuando el subárbol izquierdo del hijo izquierdo causó el desbalance
    public static <T extends Comparable<T>> Telemento<T> rotacionLL(Telemento<T> k2) {
        Telemento<T> k1 = k2.getHijoIzq();
        k2.setHijoIzq(k1.getHijoDer());
        k1.setHijoDer(k2);
        // Se actualizan alturas 
        k2.actualizarAltura();
        k1.actualizarAltura();
        return k1;
    }

    // Rotación simple a la izquierda (caso RR)
    public static <T extends Comparable<T>> Telemento<T> rotacionRR(Telemento<T> k1) {
        Telemento<T> k2 = k1.getHijoDer();
        k1.setHijoDer(k2.getHijoIzq());
        k2.setHijoIzq(k1);
        // Se actualizan alturas
        k1.actualizarAltura();
        k2.actualizarAltura();
        return k2;
    }

    // Rotación doble izquierda-derecha (caso LR)
    public static <T extends Comparable<T>> Telemento<T> rotacionLR(Telemento<T> k3) {
        k3.setHijoIzq(rotacionRR(k3.getHijoIzq()));
        return rotacionLL(k3);
    }

    // Rotación doble derecha-izquierda (caso RL)
    public static <T extends Comparable<T>> Telemento<T> rotacionRL(Telemento<T> k1) {
        k1.setHijoDer(rotacionLL(k1.getHijoDer()));
        return rotacionRR(k1);
    }
}
