package com.example.arbolavl;

import org.junit.Test;
import static org.junit.Assert.*;

public class InsercionTest {

    @Test
    public void insertarEnArbolVacioCreaNodo() {
        Telemento<Integer> raiz = Insercion.insertar(null, 10);
        assertNotNull(raiz);
        assertEquals(10, (int) raiz.getDato());
        assertNull(raiz.getHijoIzq());
        assertNull(raiz.getHijoDer());
        assertEquals(1, raiz.getAltura());
    }

    @Test
    public void insertarMenorVaALaIzquierda() {
        Telemento<Integer> raiz = Insercion.insertar(null, 10);
        raiz = Insercion.insertar(raiz, 5);
        assertEquals(10, (int) raiz.getDato());
        assertEquals(5,  (int) raiz.getHijoIzq().getDato());
        assertNull(raiz.getHijoDer());
    }

    @Test
    public void insertarMayorVaALaDerecha() {
        Telemento<Integer> raiz = Insercion.insertar(null, 10);
        raiz = Insercion.insertar(raiz, 20);
        assertEquals(10, (int) raiz.getDato());
        assertEquals(20, (int) raiz.getHijoDer().getDato());
        assertNull(raiz.getHijoIzq());
    }

    @Test
    public void insertarDuplicadoNoModificaArbol() {
        Telemento<Integer> raiz = Insercion.insertar(null, 10);
        raiz = Insercion.insertar(raiz, 10);
        assertEquals(10, (int) raiz.getDato());
        assertNull(raiz.getHijoIzq());
        assertNull(raiz.getHijoDer());
        assertEquals(1, raiz.getAltura());
    }

    @Test
    public void insertarAscendenteAplicaRotacionRR() {
        // 1, 2, 3 → balance en 1 es -2 → rotacionRR → raiz=2
        Telemento<Integer> raiz = null;
        raiz = Insercion.insertar(raiz, 1);
        raiz = Insercion.insertar(raiz, 2);
        raiz = Insercion.insertar(raiz, 3);
        assertEquals(2, (int) raiz.getDato());
        assertEquals(1, (int) raiz.getHijoIzq().getDato());
        assertEquals(3, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void insertarDescendenteAplicaRotacionLL() {
        // 3, 2, 1 → balance en 3 es 2 → rotacionLL → raiz=2
        Telemento<Integer> raiz = null;
        raiz = Insercion.insertar(raiz, 3);
        raiz = Insercion.insertar(raiz, 2);
        raiz = Insercion.insertar(raiz, 1);
        assertEquals(2, (int) raiz.getDato());
        assertEquals(1, (int) raiz.getHijoIzq().getDato());
        assertEquals(3, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void insertarAplicaRotacionLR() {
        // 3, 1, 2 → balance en 3 es 2 y 2 > 1 → rotacionLR → raiz=2
        Telemento<Integer> raiz = null;
        raiz = Insercion.insertar(raiz, 3);
        raiz = Insercion.insertar(raiz, 1);
        raiz = Insercion.insertar(raiz, 2);
        assertEquals(2, (int) raiz.getDato());
        assertEquals(1, (int) raiz.getHijoIzq().getDato());
        assertEquals(3, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void insertarAplicaRotacionRL() {
        // 1, 3, 2 → balance en 1 es -2 y 2 < 3 → rotacionRL → raiz=2
        Telemento<Integer> raiz = null;
        raiz = Insercion.insertar(raiz, 1);
        raiz = Insercion.insertar(raiz, 3);
        raiz = Insercion.insertar(raiz, 2);
        assertEquals(2, (int) raiz.getDato());
        assertEquals(1, (int) raiz.getHijoIzq().getDato());
        assertEquals(3, (int) raiz.getHijoDer().getDato());
    }

    @Test
    public void alturaArbolTresElementosPerfecto() {
        // 1, 2, 3 → tras rotacionRR queda árbol completo de altura 2
        Telemento<Integer> raiz = null;
        raiz = Insercion.insertar(raiz, 1);
        raiz = Insercion.insertar(raiz, 2);
        raiz = Insercion.insertar(raiz, 3);
        assertEquals(2, raiz.getAltura());
    }

    @Test
    public void balanceRaizSiempreValido() {
        Telemento<Integer> raiz = null;
        for (int v : new int[]{10, 20, 30, 40, 50, 60, 70}) {
            raiz = Insercion.insertar(raiz, v);
            assertTrue("balance fuera de rango tras insertar " + v,
                    Math.abs(raiz.obtenerBalance()) <= 1);
        }
    }

    @Test
    public void alturaCreceLogaritmicamente() {
        // 15 elementos en orden ascendente: AVL debe mantener h <= 5
        Telemento<Integer> raiz = null;
        for (int i = 1; i <= 15; i++) {
            raiz = Insercion.insertar(raiz, i);
        }
        assertTrue("altura esperada <= 5, fue " + raiz.getAltura(),
                raiz.getAltura() <= 5);
    }
}
