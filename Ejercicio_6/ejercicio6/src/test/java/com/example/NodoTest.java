package com.example;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class NodoTest 
{

    
    @Test

    public void testConstructorGuardaDato() {
        Nodo<Integer> nodo = new Nodo<>(42);
        assert(nodo.getDato().equals(42));
    }

    @Test
    public void testConstructorSiguienteEsNullInicial() {
        Nodo<String> nodo = new Nodo<>("hola");
        assertNull(nodo.getSiguiente());
    }


    @Test
    public void testSetDatoCambiaValor() {
        Nodo<String> nodo = new Nodo<>("original");
        nodo.setDato("nuevo");
        assert(nodo.getDato().equals("nuevo"));
    }

    @Test
    public void testSetDatoConNull() {
        Nodo<String> nodo = new Nodo<>("algo");
        nodo.setDato(null);
        assertNull(nodo.getDato());
    }


    @Test
    public void testSetSiguienteEncadenaNodo() {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);
        nodo1.setSiguiente(nodo2);
        assert(nodo1.getSiguiente().equals(nodo2));
    }

    @Test
    public void testSetSiguienteConNullDesencadena() {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);
        nodo1.setSiguiente(nodo2);
        nodo1.setSiguiente(null);
        assertNull(nodo1.getSiguiente());
    }

    @Test
    public void testEncadenamientoMultiplesNodos() {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);
        Nodo<Integer> nodo3 = new Nodo<>(3);
        nodo1.setSiguiente(nodo2);
        nodo2.setSiguiente(nodo3);
        assert(nodo1.getSiguiente().getSiguiente().equals(nodo3));
    }


    @Test
    public void testConTipoDouble() {
        Nodo<Double> nodo = new Nodo<>(3.14);
        assert(nodo.getDato().equals(3.14));
    }

    @Test
    public void testConTipoObjeto() {
        Nodo<int[]> nodo = new Nodo<>(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{1, 2, 3}, nodo.getDato());
    }
}