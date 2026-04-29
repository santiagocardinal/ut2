package com.example.arbolavl;

import org.junit.Test;
import static org.junit.Assert.*;

public class TelemementoTest {

    @Test
    public void constructorCreaHojaConAltura1() {
        Telemento<Integer> nodo = new Telemento<>(42);
        assertEquals(42, (int) nodo.getDato());
        assertNull(nodo.getHijoIzq());
        assertNull(nodo.getHijoDer());
        assertEquals(1, nodo.getAltura());
    }

    @Test
    public void alturaDeNullEsCero() {
        assertEquals(0, Telemento.alturaDe(null));
    }

    @Test
    public void alturaDeNodoRetornaAltura() {
        Telemento<Integer> nodo = new Telemento<>(1);
        assertEquals(1, Telemento.alturaDe(nodo));
    }

    @Test
    public void setDatoActualizaValor() {
        Telemento<Integer> nodo = new Telemento<>(1);
        nodo.setDato(99);
        assertEquals(99, (int) nodo.getDato());
    }

    @Test
    public void setHijosActualizaReferencias() {
        Telemento<Integer> padre = new Telemento<>(10);
        Telemento<Integer> izq   = new Telemento<>(5);
        Telemento<Integer> der   = new Telemento<>(15);
        padre.setHijoIzq(izq);
        padre.setHijoDer(der);
        assertSame(izq, padre.getHijoIzq());
        assertSame(der, padre.getHijoDer());
    }

    @Test
    public void actualizarAlturaConDosHijos() {
        Telemento<Integer> padre = new Telemento<>(10);
        Telemento<Integer> izq   = new Telemento<>(5);
        Telemento<Integer> der   = new Telemento<>(15);
        padre.setHijoIzq(izq);
        padre.setHijoDer(der);
        padre.actualizarAltura();
        assertEquals(2, padre.getAltura());
    }

    @Test
    public void actualizarAlturaSoloHijoIzquierdo() {
        Telemento<Integer> padre = new Telemento<>(10);
        Telemento<Integer> izq   = new Telemento<>(5);
        padre.setHijoIzq(izq);
        padre.actualizarAltura();
        assertEquals(2, padre.getAltura());
    }

    @Test
    public void obtenerBalanceSinHijosEsCero() {
        Telemento<Integer> nodo = new Telemento<>(10);
        assertEquals(0, nodo.obtenerBalance());
    }

    @Test
    public void obtenerBalanceConHijosIguales() {
        Telemento<Integer> padre = new Telemento<>(10);
        padre.setHijoIzq(new Telemento<>(5));
        padre.setHijoDer(new Telemento<>(15));
        assertEquals(0, padre.obtenerBalance());
    }

    @Test
    public void obtenerBalanceCargadoIzquierda() {
        Telemento<Integer> padre = new Telemento<>(10);
        padre.setHijoIzq(new Telemento<>(5));
        assertEquals(1, padre.obtenerBalance());
    }

    @Test
    public void obtenerBalanceCargadoDerecha() {
        Telemento<Integer> padre = new Telemento<>(10);
        padre.setHijoDer(new Telemento<>(15));
        assertEquals(-1, padre.obtenerBalance());
    }
}
