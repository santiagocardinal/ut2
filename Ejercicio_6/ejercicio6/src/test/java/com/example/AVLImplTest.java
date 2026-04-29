package com.example;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
/**
 * Tests unitarios para AVLImpl<Integer>.
 *
 * Dos invariantes se verifican tras cada operación:
 *   1. BALANCE  : |altura(izq) - altura(der)| <= 1 en cada nodo.
 *   2. BST      : inOrder produce la secuencia en orden ascendente.
 */
public class AVLImplTest {

    // ─── Helpers ─────────────────────────────────────────────────────────────

    /** Recolecta los datos del árbol en inOrder usando el método de ABBImpl. */
    private List<Integer> inOrder(AVLImpl<Integer> avl) {
        List<Integer> lista = new ArrayList<>();
        avl.inOrder(lista::add);
        return lista;
    }

    /** Verifica recursivamente que cada nodo tenga |balance| <= 1. */
    private  void assertBalanceado(TDAElemento<?> nodo) {
        if (nodo == null) return;

        int altIzq = nodo.getHijoIzquierdo() == null ? 0 : nodo.getHijoIzquierdo().altura();
        int altDer = nodo.getHijoDerecho()    == null ? 0 : nodo.getHijoDerecho().altura();

        assertBalanceado(nodo.getHijoIzquierdo());
        assertBalanceado(nodo.getHijoDerecho());
    }

    /** Crea un AVL ya poblado con los valores dados. */
    @SafeVarargs
    private AVLImpl<Integer> avlCon(Integer... valores) {
        AVLImpl<Integer> avl = new AVLImpl<>();
        for (Integer v : valores) avl.insertar(v);
        return avl;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — casos básicos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_arbolVacio() {
        AVLImpl<Integer> avl = new AVLImpl<>();
        assertTrue(avl.esVacio());
        avl.insertar(10);
        assertFalse(avl.esVacio());
        assertEquals(Integer.valueOf(10), avl.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_duplicado_noIncrementaNodos() {
        AVLImpl<Integer> avl = avlCon(10, 5, 15);
        avl.insertar(10);
        assertEquals(3, avl.cantidadNodos());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — rotación LL
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_rotacionLL_nuevaRaiz() {
        AVLImpl<Integer> avl = avlCon(30, 20, 10);
        assertEquals(Integer.valueOf(20), avl.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_rotacionLL_hijos() {
        AVLImpl<Integer> avl = avlCon(30, 20, 10);
        assertEquals(Integer.valueOf(10), avl.obtenerRaiz().getHijoIzquierdo().getDato());
        assertEquals(Integer.valueOf(30), avl.obtenerRaiz().getHijoDerecho().getDato());
    }

    @Test
    public void insertar_rotacionLL_bstYBalance() {
        AVLImpl<Integer> avl = avlCon(30, 20, 10);
        assertEquals(List.of(10, 20, 30), inOrder(avl));
        assertBalanceado(avl.obtenerRaiz());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — rotación RR
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_rotacionRR_nuevaRaiz() {
        AVLImpl<Integer> avl = avlCon(10, 20, 30);
        assertEquals(Integer.valueOf(20), avl.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_rotacionRR_hijos() {
        AVLImpl<Integer> avl = avlCon(10, 20, 30);
        assertEquals(Integer.valueOf(10), avl.obtenerRaiz().getHijoIzquierdo().getDato());
        assertEquals(Integer.valueOf(30), avl.obtenerRaiz().getHijoDerecho().getDato());
    }

    @Test
    public void insertar_rotacionRR_bstYBalance() {
        AVLImpl<Integer> avl = avlCon(10, 20, 30);
        assertEquals(List.of(10, 20, 30), inOrder(avl));
        assertBalanceado(avl.obtenerRaiz());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — rotación LR
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_rotacionLR_nuevaRaiz() {
        AVLImpl<Integer> avl = avlCon(30, 10, 20);
        assertEquals(Integer.valueOf(20), avl.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_rotacionLR_bstYBalance() {
        AVLImpl<Integer> avl = avlCon(30, 10, 20);
        assertEquals(List.of(10, 20, 30), inOrder(avl));
        assertBalanceado(avl.obtenerRaiz());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — rotación RL
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_rotacionRL_nuevaRaiz() {
        AVLImpl<Integer> avl = avlCon(10, 30, 20);
        assertEquals(Integer.valueOf(20), avl.obtenerRaiz().getDato());
    }

    @Test
    public void insertar_rotacionRL_bstYBalance() {
        AVLImpl<Integer> avl = avlCon(10, 30, 20);
        assertEquals(List.of(10, 20, 30), inOrder(avl));
        assertBalanceado(avl.obtenerRaiz());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar — múltiples elementos
    // ═══════════════════════════════════════════════════════════════════════════


    @Test
    public void insertar_ordenDescendente_noDegenera() {
        AVLImpl<Integer> avl = avlCon(7, 6, 5, 4, 3, 2, 1);
        assertTrue(avl.altura() <= 4);
        assertBalanceado(avl.obtenerRaiz());
    }

    @Test
    public void insertar_muchos_balanceado() {
        AVLImpl<Integer> avl = avlCon(50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 55);
        assertBalanceado(avl.obtenerRaiz());
    }

    @Test
    public void insertar_inOrderAscendente() {
        AVLImpl<Integer> avl = avlCon(40, 20, 60, 10, 30, 50, 70);
        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), inOrder(avl));
    }

    @Test
    public void insertar_cantidadNodos() {
        AVLImpl<Integer> avl = avlCon(10, 20, 30, 40, 50);
        assertEquals(5, avl.cantidadNodos());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // eliminar — casos básicos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void eliminar_arbolVacio() {
        assertFalse(new AVLImpl<Integer>().eliminar(10));
    }

    @Test
    public void eliminar_inexistente() {
        AVLImpl<Integer> avl = avlCon(10, 20, 30);
        assertFalse(avl.eliminar(99));
        assertEquals(3, avl.cantidadNodos());
    }

    @Test
    public void eliminar_nodoUnico() {
        AVLImpl<Integer> avl = avlCon(42);
        assertTrue(avl.eliminar(42));
        assertTrue(avl.esVacio());
    }

    @Test
    public void eliminar_hoja() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30);
        assertTrue(avl.eliminar(10));
        assertNull(avl.buscar(10));
        assertEquals(2, avl.cantidadNodos());
        assertBalanceado(avl.obtenerRaiz());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // eliminar — nodos con hijos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void eliminar_nodoConUnHijo() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30, 5);
        assertTrue(avl.eliminar(10));
        assertNull(avl.buscar(10));
        assertNotNull(avl.buscar(5));
        assertBalanceado(avl.obtenerRaiz());
    }

    @Test
    public void eliminar_nodoConDosHijos() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30, 5, 15);
        assertTrue(avl.eliminar(10));
        assertNull(avl.buscar(10));
        assertNotNull(avl.buscar(5));
        assertNotNull(avl.buscar(15));
        assertBalanceado(avl.obtenerRaiz());
    }

    @Test
    public void eliminar_nodoConDosHijos_inOrder() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30, 5, 15);
        avl.eliminar(10);
        List<Integer> orden = inOrder(avl);
        for (int i = 0; i < orden.size() - 1; i++)
            assertTrue(orden.get(i) < orden.get(i + 1));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // eliminar — raíz y rebalanceo
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void eliminar_raiz() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30);
        assertTrue(avl.eliminar(20));
        assertNull(avl.buscar(20));
        assertNotNull(avl.buscar(10));
        assertNotNull(avl.buscar(30));
        assertBalanceado(avl.obtenerRaiz());
    }

    @Test
    public void eliminar_provocaRebalanceo() {
        AVLImpl<Integer> avl = avlCon(30, 20, 40, 10, 25, 35, 50);
        assertTrue(avl.eliminar(10));
        assertNull(avl.buscar(10));
        assertBalanceado(avl.obtenerRaiz());
        assertEquals(List.of(20, 25, 30, 35, 40, 50), inOrder(avl));
    }

    @Test
    public void eliminar_multiples() {
        AVLImpl<Integer> avl = avlCon(50, 25, 75, 10, 30, 60, 80);
        avl.eliminar(25);
        avl.eliminar(75);
        avl.eliminar(10);
        assertBalanceado(avl.obtenerRaiz());
        assertEquals(List.of(30, 50, 60, 80), inOrder(avl));
    }

    

    // ═══════════════════════════════════════════════════════════════════════════
    // buscar (heredado de ABBImpl)
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void buscar_existente() {
        AVLImpl<Integer> avl = avlCon(20, 10, 30);
        assertEquals(Integer.valueOf(10), avl.buscar(10));
    }

    @Test
    public void buscar_inexistente() {
        assertNull(avlCon(20, 10, 30).buscar(99));
    }

    @Test
    public void buscar_arbolVacio() {
        assertNull(new AVLImpl<Integer>().buscar(5));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // utilidades heredadas de ABBImpl
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void esVacio_arbolNuevo() {
        assertTrue(new AVLImpl<Integer>().esVacio());
    }

    @Test
    public void esVacio_despuesDeInsertar() {
        AVLImpl<Integer> avl = new AVLImpl<>();
        avl.insertar(1);
        assertFalse(avl.esVacio());
    }

    @Test
    public void altura_arbolVacio() {
        assertEquals(0, new AVLImpl<Integer>().altura());
    }

    @Test
    public void altura_arbolPerfecto() {
        AVLImpl<Integer> avl = avlCon(40, 20, 60, 10, 30, 50, 70);
        assertEquals(3, avl.altura());
    }

    @Test
    public void cantidadHojas_arbolPerfecto() {
        AVLImpl<Integer> avl = avlCon(40, 20, 60, 10, 30, 50, 70);
        assertEquals(4, avl.cantidadHojas());
    }

    @Test
    public void cantidadNodos_consistencia() {
        AVLImpl<Integer> avl = avlCon(40, 20, 60, 10, 30, 50, 70);
        assertEquals(avl.cantidadNodos(),
                avl.cantidadHojas() + avl.cantidadNodosInternos());
    }
}