package com.example;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ABBImplTest {

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private ABBImpl<Integer> arbolReferencia() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        abb.insertar(5);
        abb.insertar(15);
        abb.insertar(3);
        abb.insertar(7);
        abb.insertar(20);
        return abb;
    }

    private List<Integer> inOrder(ABBImpl<Integer> abb) {
        List<Integer> lista = new ArrayList<>();
        abb.inOrder(lista::add);
        return lista;
    }

    private List<Integer> preOrder(ABBImpl<Integer> abb) {
        List<Integer> lista = new ArrayList<>();
        abb.preOrder(lista::add);
        return lista;
    }

    private List<Integer> postOrder(ABBImpl<Integer> abb) {
        List<Integer> lista = new ArrayList<>();
        abb.postOrder(lista::add);
        return lista;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // esVacio
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void esVacio_arbolNuevo() {
        assertTrue(new ABBImpl<Integer>().esVacio());
    }

    @Test
    public void esVacio_despuesDeInsertar() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        assertFalse(abb.esVacio());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // obtenerRaiz
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void obtenerRaiz_arbolVacio() {
        assertNull(new ABBImpl<Integer>().obtenerRaiz());
    }

    @Test
    public void obtenerRaiz_primerElemento() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        assertEquals(Integer.valueOf(10), abb.obtenerRaiz().getDato());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_arbolVacio() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        assertTrue(abb.insertar(10));
        assertEquals(Integer.valueOf(10), abb.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_valorMayor() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        abb.insertar(15);
        assertEquals(Integer.valueOf(15), abb.obtenerRaiz().getHijoDerecho().getDato());
    }

    @Test
    public void insertar_valorMenor() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        abb.insertar(5);
        assertEquals(Integer.valueOf(5), abb.obtenerRaiz().getHijoIzquierdo().getDato());
    }

    @Test
    public void insertar_duplicado() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        assertFalse(abb.insertar(10));
    }

    @Test
    public void insertar_duplicado_noIncrementaNodos() {
        ABBImpl<Integer> abb = arbolReferencia();
        abb.insertar(10);
        assertEquals(6, abb.cantidadNodos());
    }

    @Test
    public void insertar_variosElementos_estructuraBST() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertEquals(Integer.valueOf(10), abb.obtenerRaiz().getDato());
        assertEquals(Integer.valueOf(5),  abb.obtenerRaiz().getHijoIzquierdo().getDato());
        assertEquals(Integer.valueOf(15), abb.obtenerRaiz().getHijoDerecho().getDato());
        assertEquals(Integer.valueOf(3),  abb.obtenerRaiz().getHijoIzquierdo().getHijoIzquierdo().getDato());
        assertEquals(Integer.valueOf(7),  abb.obtenerRaiz().getHijoIzquierdo().getHijoDerecho().getDato());
        assertEquals(Integer.valueOf(20), abb.obtenerRaiz().getHijoDerecho().getHijoDerecho().getDato());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // buscar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void buscar_arbolVacio() {
        assertNull(new ABBImpl<Integer>().buscar(10));
    }

    @Test
    public void buscar_raiz() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertEquals(Integer.valueOf(10), abb.buscar(10));
    }

    @Test
    public void buscar_hoja() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertEquals(Integer.valueOf(3), abb.buscar(3));
    }

    @Test
    public void buscar_inexistente() {
        assertNull(arbolReferencia().buscar(99));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // eliminar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void eliminar_arbolVacio() {
        assertFalse(new ABBImpl<Integer>().eliminar(10));
    }

    @Test
    public void eliminar_inexistente() {
        assertFalse(arbolReferencia().eliminar(99));
    }

    @Test
    public void eliminar_inexistente_noAlteraNodos() {
        ABBImpl<Integer> abb = arbolReferencia();
        abb.eliminar(99);
        assertEquals(6, abb.cantidadNodos());
    }

    @Test
    public void eliminar_hoja() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertTrue(abb.eliminar(3));
        assertNull(abb.buscar(3));
    }

    @Test
    public void eliminar_hoja_reduceCantidad() {
        ABBImpl<Integer> abb = arbolReferencia();
        abb.eliminar(3);
        assertEquals(5, abb.cantidadNodos());
    }

    @Test
    public void eliminar_nodoConUnHijo() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        abb.insertar(15);
        abb.insertar(20);
        assertTrue(abb.eliminar(15));
        assertNull(abb.buscar(15));
        assertNotNull(abb.buscar(20));
    }

    @Test
    public void eliminar_nodoConDosHijos() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertTrue(abb.eliminar(5));
        assertNull(abb.buscar(5));
        assertNotNull(abb.buscar(3));
        assertNotNull(abb.buscar(7));
    }

    @Test
    public void eliminar_raiz() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertTrue(abb.eliminar(10));
        assertNull(abb.buscar(10));
    }

    @Test
    public void eliminar_raiz_restoIntacto() {
        ABBImpl<Integer> abb = arbolReferencia();
        abb.eliminar(10);
        assertNotNull(abb.buscar(5));
        assertNotNull(abb.buscar(15));
        assertNotNull(abb.buscar(20));
    }

    @Test
    public void eliminar_nodoUnico() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(42);
        assertTrue(abb.eliminar(42));
        assertTrue(abb.esVacio());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // Recorridos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void inOrder_ordenAscendente() {
        assertEquals(List.of(3, 5, 7, 10, 15, 20), inOrder(arbolReferencia()));
    }

    @Test
    public void inOrder_arbolVacio() {
        assertEquals(List.of(), inOrder(new ABBImpl<>()));
    }

    @Test
    public void preOrder_raizPrimero() {
        List<Integer> resultado = preOrder(arbolReferencia());
        assertEquals(Integer.valueOf(10), resultado.get(0));
        assertEquals(List.of(10, 5, 3, 7, 15, 20), resultado);
    }

    @Test
    public void preOrder_arbolVacio() {
        assertEquals(List.of(), preOrder(new ABBImpl<>()));
    }

    @Test
    public void postOrder_raizAlFinal() {
        List<Integer> resultado = postOrder(arbolReferencia());
        assertEquals(Integer.valueOf(10), resultado.get(resultado.size() - 1));
        assertEquals(List.of(3, 7, 5, 20, 15, 10), resultado);
    }

    @Test
    public void postOrder_arbolVacio() {
        assertEquals(List.of(), postOrder(new ABBImpl<>()));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // cantidadNodos / Hojas / Internos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void cantidadNodos_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().cantidadNodos());
    }

    @Test
    public void cantidadNodos_arbolReferencia() {
        assertEquals(6, arbolReferencia().cantidadNodos());
    }

    @Test
    public void cantidadHojas_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().cantidadHojas());
    }

    @Test
    public void cantidadHojas_arbolReferencia() {
        assertEquals(3, arbolReferencia().cantidadHojas());
    }

    @Test
    public void cantidadNodosInternos_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().cantidadNodosInternos());
    }

    @Test
    public void cantidadNodosInternos_arbolReferencia() {
        assertEquals(3, arbolReferencia().cantidadNodosInternos());
    }

    @Test
    public void cantidadNodos_consistencia() {
        ABBImpl<Integer> abb = arbolReferencia();
        assertEquals(abb.cantidadNodos(),
                abb.cantidadHojas() + abb.cantidadNodosInternos());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // altura
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void altura_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().altura());
    }

    @Test
    public void altura_arbolReferencia() {
        assertEquals(3, arbolReferencia().altura());
    }

    @Test
    public void altura_arbolDegenerado() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(1);
        abb.insertar(2);
        abb.insertar(3);
        abb.insertar(4);
        assertEquals(4, abb.altura());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // imprimirInOrden
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void imprimirInOrden_arbolVacio() {
        assertEquals("Árbol vacío", new ABBImpl<Integer>().imprimirInOrden());
    }

    @Test
    public void imprimirInOrden_contieneElementos() {
        String resultado = arbolReferencia().imprimirInOrden();
        assertTrue(resultado.contains("3"));
        assertTrue(resultado.contains("5"));
        assertTrue(resultado.contains("7"));
        assertTrue(resultado.contains("10"));
        assertTrue(resultado.contains("15"));
        assertTrue(resultado.contains("20"));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // completos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void completos_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().completos().tamano());
    }

    @Test
    public void completos_arbolReferencia() {
        TDALista<TDAElemento<Integer>> lista = arbolReferencia().completos();
        assertEquals(2, lista.tamano());

        List<Integer> datos = new ArrayList<>();
        for (int idx = 0; idx < lista.tamano(); idx++)
            datos.add(lista.obtener(idx).getDato());

        assertTrue(datos.contains(10));
        assertTrue(datos.contains(5));
    }

    @Test
    public void completos_sinNodosCompletos() {
        ABBImpl<Integer> abb = new ABBImpl<>();
        abb.insertar(10);
        abb.insertar(5);
        assertEquals(0, abb.completos().tamano());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // enNivel
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void enNivel_arbolVacio() {
        assertEquals(0, new ABBImpl<Integer>().enNivel(0).tamano());
    }

    @Test
    public void enNivel_cero() {
        TDALista<TDAElemento<Integer>> lista = arbolReferencia().enNivel(0);
        assertEquals(1, lista.tamano());
        assertEquals(Integer.valueOf(10), lista.obtener(0).getDato());
    }

    @Test
    public void enNivel_uno() {
        TDALista<TDAElemento<Integer>> lista = arbolReferencia().enNivel(1);
        assertEquals(2, lista.tamano());

        List<Integer> datos = new ArrayList<>();
        for (int idx = 0; idx < lista.tamano(); idx++)
            datos.add(lista.obtener(idx).getDato());

        assertTrue(datos.contains(5));
        assertTrue(datos.contains(15));
    }

    @Test
    public void enNivel_dos() {
        TDALista<TDAElemento<Integer>> lista = arbolReferencia().enNivel(2);
        assertEquals(3, lista.tamano());

        List<Integer> datos = new ArrayList<>();
        for (int idx = 0; idx < lista.tamano(); idx++)
            datos.add(lista.obtener(idx).getDato());

        assertTrue(datos.contains(3));
        assertTrue(datos.contains(7));
        assertTrue(datos.contains(20));
    }

    @Test
    public void enNivel_fueraDeRango() {
        assertEquals(0, arbolReferencia().enNivel(10).tamano());
    }
}