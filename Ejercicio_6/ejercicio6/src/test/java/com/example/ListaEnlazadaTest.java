package com.example;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ListaEnlazadaTest {


    /** Crea una lista ya poblada con los valores dados en orden. */
    private ListaEnlazada<Integer> listaCon(Integer... valores) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        for (Integer v : valores) lista.agregar(v);
        return lista;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // esVacio / tamano
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void esVacio_listaVacia() {
        assertTrue(new ListaEnlazada<Integer>().esVacio());
    }

    @Test
    public void esVacio_despuesDeAgregar() {
        assertFalse(listaCon(1).esVacio());
    }

    @Test
    public void tamano_listaVacia() {
        assertEquals(0, new ListaEnlazada<Integer>().tamano());
    }

    @Test
    public void tamano_conElementos() {
        assertEquals(3, listaCon(1, 2, 3).tamano());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // agregar / obtener
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void agregar_retornaTrue() {
        assertTrue(new ListaEnlazada<Integer>().agregar(10));
    }

    @Test
    public void agregar_ordenInsercion() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    @Test
    public void obtener_indiceNegativo() {
        assertNull(listaCon(1, 2, 3).obtener(-1));
    }

    @Test
    public void obtener_indiceFueraDeRango() {
        assertNull(listaCon(1, 2, 3).obtener(10));
    }

    @Test
    public void agregar_porIndice_posicionCero() {
        ListaEnlazada<Integer> lista = listaCon(2, 3);
        lista.agregar(0, 1);
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    @Test
    public void agregar_porIndice_posicionIntermedia() {
        ListaEnlazada<Integer> lista = listaCon(1, 3);
        lista.agregar(1, 2);
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // quitar / eliminar por elemento
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void quitar_porElemento_retornaDato() {
        assertEquals(Integer.valueOf(3), listaCon(1, 2, 3).quitar(2));
    }

    

    @Test
    public void quitar_porElemento_cabeza() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.quitar(1);
        assertEquals(Integer.valueOf(1), lista.obtener(0));
    }

    @Test
    public void quitar_porElemento_inexistente() {
        assertNull(listaCon(1, 2, 3).quitar(99));
    }

    @Test
    public void quitar_porElemento_listaVacia() {
        assertNull(new ListaEnlazada<Integer>().quitar(1));
    }

    @Test
    public void eliminar_porElemento_existe() {
        assertTrue(listaCon(1, 2, 3).eliminar(2));
    }

    @Test
    public void eliminar_porElemento_noExiste() {
        assertFalse(listaCon(1, 2, 3).eliminar(99));
    }

    @Test
    public void eliminar_porElemento_listaVacia() {
        assertFalse(new ListaEnlazada<Integer>().eliminar(1));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // quitar / eliminar por índice
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void quitar_porIndice_retornaDato() {
        assertEquals(Integer.valueOf(2), listaCon(1, 2, 3).quitar(1));
    }

    @Test
    public void quitar_porIndice_loElimina() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.quitar(1);
        assertEquals(2, lista.tamano());
        assertEquals(Integer.valueOf(3), lista.obtener(1));
    }

    @Test
    public void quitar_porIndice_cabeza() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.quitar(0);
        assertEquals(Integer.valueOf(2), lista.obtener(0));
    }

    @Test
    public void quitar_porIndice_fueraDeRango() {
        assertNull(listaCon(1, 2, 3).quitar(10));
    }

    @Test
    public void eliminar_porIndice_existe() {
        assertTrue(listaCon(1, 2, 3).eliminar(1));
    }

    @Test
    public void eliminar_porIndice_fueraDeRango() {
        assertFalse(listaCon(1, 2, 3).eliminar(10));
    }

    @Test
    public void eliminar_porIndice_cabeza() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.eliminar(0);
        assertEquals(Integer.valueOf(2), lista.obtener(0));
        assertEquals(2, lista.tamano());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // contiene / indiceDe
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void contiene_existe() {
        assertTrue(listaCon(1, 2, 3).contiene(2));
    }

    @Test
    public void contiene_noExiste() {
        assertFalse(listaCon(1, 2, 3).contiene(99));
    }

    @Test
    public void indiceDe_posicionCorrecta() {
        assertEquals(1, listaCon(1, 2, 3).indiceDe(2));
    }

    @Test
    public void indiceDe_inexistente() {
        assertEquals(-1, listaCon(1, 2, 3).indiceDe(99));
    }

    @Test
    public void indiceDe_null() {
        assertEquals(-1, listaCon(1, 2, 3).indiceDe(null));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // buscar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void buscar_criterioSatisfecho() {
        assertEquals(Integer.valueOf(2), listaCon(1, 2, 3).buscar(x -> x == 2));
    }

    @Test
    public void buscar_ningunoSatisface() {
        assertNull(listaCon(1, 2, 3).buscar(x -> x > 10));
    }

    @Test
    public void buscar_criterioNull() {
        assertNull(listaCon(1, 2, 3).buscar(null));
    }

    @Test
    public void buscar_listaVacia() {
        assertNull(new ListaEnlazada<Integer>().buscar(x -> true));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // vaciar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void vaciar_dejalistaVacia() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.vaciar();
        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamano());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // invertir
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void invertir_ordenInvertido() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.invertir();
        assertEquals(Integer.valueOf(3), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(1), lista.obtener(2));
    }

    @Test
    public void invertir_unElemento() {
        ListaEnlazada<Integer> lista = listaCon(42);
        lista.invertir();
        assertEquals(Integer.valueOf(42), lista.obtener(0));
    }

    @Test
    public void invertir_dobleInversion() {
        ListaEnlazada<Integer> lista = listaCon(1, 2, 3);
        lista.invertir();
        lista.invertir();
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // concatenar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void concatenar_resultado() {
        TDALista<Integer> resultado = listaCon(1, 2).concatenar(listaCon(3, 4));
        assertEquals(4, resultado.tamano());
        assertEquals(Integer.valueOf(1), resultado.obtener(0));
        assertEquals(Integer.valueOf(2), resultado.obtener(1));
        assertEquals(Integer.valueOf(3), resultado.obtener(2));
        assertEquals(Integer.valueOf(4), resultado.obtener(3));
    }

    @Test
    public void concatenar_conListaVacia() {
        TDALista<Integer> resultado = listaCon(1, 2).concatenar(new ListaEnlazada<>());
        assertEquals(2, resultado.tamano());
    }

    @Test
    public void concatenar_noModificaOriginales() {
        ListaEnlazada<Integer> a = listaCon(1, 2);
        ListaEnlazada<Integer> b = listaCon(3, 4);
        a.concatenar(b);
        assertEquals(2, a.tamano());
        assertEquals(2, b.tamano());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // intercalar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void intercalar_resultado() {
        TDALista<Integer> resultado = listaCon(1, 3).intercalar(listaCon(2, 4));
        assertEquals(4, resultado.tamano());
        assertEquals(Integer.valueOf(1), resultado.obtener(0));
        assertEquals(Integer.valueOf(2), resultado.obtener(1));
        assertEquals(Integer.valueOf(3), resultado.obtener(2));
        assertEquals(Integer.valueOf(4), resultado.obtener(3));
    }

    @Test
    public void intercalar_primeraEsMasLarga() {
        TDALista<Integer> resultado = listaCon(1, 2, 3).intercalar(listaCon(10));
        assertEquals(4, resultado.tamano());
        assertEquals(Integer.valueOf(1),  resultado.obtener(0));
        assertEquals(Integer.valueOf(10), resultado.obtener(1));
        assertEquals(Integer.valueOf(2),  resultado.obtener(2));
        assertEquals(Integer.valueOf(3),  resultado.obtener(3));
    }

    @Test
    public void intercalar_segundaEsMasLarga() {
        TDALista<Integer> resultado = listaCon(1).intercalar(listaCon(10, 20, 30));
        assertEquals(4, resultado.tamano());
        assertEquals(Integer.valueOf(1),  resultado.obtener(0));
        assertEquals(Integer.valueOf(10), resultado.obtener(1));
        assertEquals(Integer.valueOf(20), resultado.obtener(2));
        assertEquals(Integer.valueOf(30), resultado.obtener(3));
    }

    @Test
    public void intercalar_conListaVacia() {
        TDALista<Integer> resultado = listaCon(1, 2).intercalar(new ListaEnlazada<>());
        assertEquals(2, resultado.tamano());
        assertEquals(Integer.valueOf(1), resultado.obtener(0));
        assertEquals(Integer.valueOf(2), resultado.obtener(1));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // ordenarTotal
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void ordenarTotal_ascendente() {
        ListaEnlazada<Integer> lista = listaCon(3, 1, 4, 1, 5, 9, 2);
        lista.ordenarTotal(Comparator.naturalOrder());
        for (int idx = 0; idx < lista.tamano() - 1; idx++)
            assertTrue(lista.obtener(idx).compareTo(lista.obtener(idx + 1)) <= 0);
    }

   
    @Test
    public void ordenarTotal_unElemento() {
        ListaEnlazada<Integer> lista = listaCon(42);
        lista.ordenarTotal(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(42), lista.obtener(0));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // ordenarParcial
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void ordenarParcial_retornaListaOrdenada() {
        ListaEnlazada<Integer> lista = listaCon(3, 1, 2);
        TDALista<Integer> resultado = lista.ordenarParcial(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(1), resultado.obtener(0));
        assertEquals(Integer.valueOf(2), resultado.obtener(1));
        assertEquals(Integer.valueOf(3), resultado.obtener(2));
    }

    @Test
    public void ordenarParcial_noModificaOriginal() {
        ListaEnlazada<Integer> lista = listaCon(3, 1, 2);
        lista.ordenarParcial(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(3), lista.obtener(0));
        assertEquals(Integer.valueOf(1), lista.obtener(1));
        assertEquals(Integer.valueOf(2), lista.obtener(2));
    }

    @Test
    public void ordenarParcial_listaVacia() {
        TDALista<Integer> resultado =
                new ListaEnlazada<Integer>().ordenarParcial(Comparator.naturalOrder());
        assertEquals(0, resultado.tamano());
    }
}