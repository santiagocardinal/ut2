package com.example.arbolavl;

import org.junit.Test;
import static org.junit.Assert.*;

public class RotacionesTest {

    private Telemento<Integer> nodo(int val) {
        return new Telemento<>(val);
    }

    // --- rotacionLL (derecha simple) ---

    @Test
    public void rotacionLL_nuevaRaizCorrecta() {
        // k2(30) <- k1(20) <- x(10)
        Telemento<Integer> x  = nodo(10);
        Telemento<Integer> k1 = nodo(20);
        Telemento<Integer> k2 = nodo(30);
        k1.setHijoIzq(x);  k1.actualizarAltura();
        k2.setHijoIzq(k1); k2.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionLL(k2);

        assertEquals(20, (int) raiz.getDato());
        assertEquals(10, (int) raiz.getHijoIzq().getDato());
        assertEquals(30, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void rotacionLL_actualizaAlturas() {
        Telemento<Integer> k1 = nodo(20);
        Telemento<Integer> k2 = nodo(30);
        k1.setHijoIzq(nodo(10)); k1.actualizarAltura();
        k2.setHijoIzq(k1);       k2.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionLL(k2);

        assertEquals(2, raiz.getAltura());
        assertEquals(1, raiz.getHijoIzq().getAltura());
        assertEquals(1, raiz.getHijoDer().getAltura());
    }

    @Test
    public void rotacionLL_preservaSubarbolDerecho() {
        // k1 tiene hijo derecho; tras la rotacion debe pasar a ser hijo izq de k2
        Telemento<Integer> subDer = nodo(25);
        Telemento<Integer> k1 = nodo(20);
        Telemento<Integer> k2 = nodo(30);
        k1.setHijoIzq(nodo(10));
        k1.setHijoDer(subDer);
        k1.actualizarAltura();
        k2.setHijoIzq(k1); k2.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionLL(k2);

        assertSame(subDer, raiz.getHijoDer().getHijoIzq());
    }

    // --- rotacionRR (izquierda simple) ---

    @Test
    public void rotacionRR_nuevaRaizCorrecta() {
        // k1(10) -> k2(20) -> y(30)
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        Telemento<Integer> y  = nodo(30);
        k2.setHijoDer(y);  k2.actualizarAltura();
        k1.setHijoDer(k2); k1.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionRR(k1);

        assertEquals(20, (int) raiz.getDato());
        assertEquals(10, (int) raiz.getHijoIzq().getDato());
        assertEquals(30, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void rotacionRR_actualizaAlturas() {
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        k2.setHijoDer(nodo(30)); k2.actualizarAltura();
        k1.setHijoDer(k2);       k1.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionRR(k1);

        assertEquals(2, raiz.getAltura());
        assertEquals(1, raiz.getHijoIzq().getAltura());
        assertEquals(1, raiz.getHijoDer().getAltura());
    }

    @Test
    public void rotacionRR_preservaSubarbolIzquierdo() {
        Telemento<Integer> subIzq = nodo(15);
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        k2.setHijoIzq(subIzq);
        k2.setHijoDer(nodo(30));
        k2.actualizarAltura();
        k1.setHijoDer(k2); k1.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionRR(k1);

        assertSame(subIzq, raiz.getHijoIzq().getHijoDer());
    }

    // --- rotacionLR (doble: RR sobre hijo izq, luego LL) ---

    @Test
    public void rotacionLR_nuevaRaizCorrecta() {
        // k3(30) -> hijoIzq=k1(10) -> hijoDer=k2(20)
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        Telemento<Integer> k3 = nodo(30);
        k1.setHijoDer(k2); k1.actualizarAltura();
        k3.setHijoIzq(k1); k3.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionLR(k3);

        assertEquals(20, (int) raiz.getDato());
        assertEquals(10, (int) raiz.getHijoIzq().getDato());
        assertEquals(30, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void rotacionLR_alturaFinalCorrecta() {
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        Telemento<Integer> k3 = nodo(30);
        k1.setHijoDer(k2); k1.actualizarAltura();
        k3.setHijoIzq(k1); k3.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionLR(k3);

        assertEquals(2, raiz.getAltura());
    }

    // --- rotacionRL (doble: LL sobre hijo der, luego RR) ---

    @Test
    public void rotacionRL_nuevaRaizCorrecta() {
        // k1(10) -> hijoDer=k3(30) -> hijoIzq=k2(20)
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        Telemento<Integer> k3 = nodo(30);
        k3.setHijoIzq(k2); k3.actualizarAltura();
        k1.setHijoDer(k3); k1.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionRL(k1);

        assertEquals(20, (int) raiz.getDato());
        assertEquals(10, (int) raiz.getHijoIzq().getDato());
        assertEquals(30, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void rotacionRL_alturaFinalCorrecta() {
        Telemento<Integer> k1 = nodo(10);
        Telemento<Integer> k2 = nodo(20);
        Telemento<Integer> k3 = nodo(30);
        k3.setHijoIzq(k2); k3.actualizarAltura();
        k1.setHijoDer(k3); k1.actualizarAltura();

        Telemento<Integer> raiz = Rotaciones.rotacionRL(k1);

        assertEquals(2, raiz.getAltura());
    }
}
