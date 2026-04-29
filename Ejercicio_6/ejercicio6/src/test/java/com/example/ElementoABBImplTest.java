package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;



public class ElementoABBImplTest {

   
    private ElementoABBImpl<Integer> arbolReferencia() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        raiz.insertar(5);
        raiz.insertar(15);
        raiz.insertar(3);
        raiz.insertar(7);
        raiz.insertar(20);
        return raiz;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // Constructor y getters / setters
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void constructor_inicializaCorrectamente() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(42);

        assertEquals(Integer.valueOf(42), nodo.getDato());
        assertNull(nodo.getHijoIzquierdo());
        assertNull(nodo.getHijoDerecho());
    }

    @Test
    public void setDato_getDato() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(1);
        nodo.setDato(99);
        assertEquals(Integer.valueOf(99), nodo.getDato());
    }

    @Test
    public void setYGetHijoIzquierdo() {
        ElementoABBImpl<Integer> padre = new ElementoABBImpl<>(10);
        ElementoABBImpl<Integer> hijo  = new ElementoABBImpl<>(5);
        padre.setHijoIzquierdo(hijo);
        assertSame(hijo, padre.getHijoIzquierdo());
    }

    @Test
    public void setYGetHijoDerecho() {
        ElementoABBImpl<Integer> padre = new ElementoABBImpl<>(10);
        ElementoABBImpl<Integer> hijo  = new ElementoABBImpl<>(15);
        padre.setHijoDerecho(hijo);
        assertSame(hijo, padre.getHijoDerecho());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // insertar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void insertar_valorMayor() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        assertTrue(raiz.insertar(15));
        assertNotNull(raiz.getHijoDerecho());
        assertEquals(Integer.valueOf(15), raiz.getHijoDerecho().getDato());
    }

    @Test
    public void insertar_valorMenor() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        assertTrue(raiz.insertar(5));
        assertNotNull(raiz.getHijoIzquierdo());
        assertEquals(Integer.valueOf(5), raiz.getHijoIzquierdo().getDato());
    }

    @Test
    public void insertar_duplicadoRetornaFalse() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        assertFalse(raiz.insertar(10));
    }

   

    // ═══════════════════════════════════════════════════════════════════════════
    // buscar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void buscar_inexistente() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertNull(raiz.buscar(99));
    }

    @Test
    public void buscar_nodoUnico_noEncontrado() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(5);
        assertNull(nodo.buscar(3));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // eliminar
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void eliminar_hoja() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        TDAElemento<Integer> nuevaRaiz = raiz.eliminar(3);
        assertNull(nuevaRaiz.buscar(3));
    }

    @Test
    public void eliminar_nodoConUnHijo() {
        // árbol: 10 -> der: 15 -> der: 20  (15 tiene solo hijo derecho)
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        raiz.insertar(15);
        raiz.insertar(20);

        TDAElemento<Integer> nuevaRaiz = raiz.eliminar(15);
        assertNull(nuevaRaiz.buscar(15));
        assertNotNull(nuevaRaiz.buscar(20));
    }

    @Test
    public void eliminar_nodoConDosHijos() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        TDAElemento<Integer> nuevaRaiz = raiz.eliminar(5);

        assertNull(nuevaRaiz.buscar(5));
        // los hijos del nodo eliminado deben seguir en el árbol
        assertNotNull(nuevaRaiz.buscar(3));
        assertNotNull(nuevaRaiz.buscar(7));
    }

    @Test
    public void eliminar_raizConDosHijos() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        TDAElemento<Integer> nuevaRaiz = raiz.eliminar(10);

        assertNull(nuevaRaiz.buscar(10));
        // el resto del árbol debe sobrevivir
        assertNotNull(nuevaRaiz.buscar(5));
        assertNotNull(nuevaRaiz.buscar(15));
        assertNotNull(nuevaRaiz.buscar(20));
    }

    @Test
    public void eliminar_inexistente() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        TDAElemento<Integer> nuevaRaiz = raiz.eliminar(99);
        // todos los nodos siguen presentes
        assertNotNull(nuevaRaiz.buscar(10));
        assertNotNull(nuevaRaiz.buscar(3));
        assertNotNull(nuevaRaiz.buscar(20));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // Recorridos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void inOrder_ordenAscendente() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        List<Integer> resultado = new ArrayList<>();
        raiz.inOrder(nodo -> resultado.add(nodo.getDato()));

        assertEquals(List.of(3, 5, 7, 10, 15, 20), resultado);
    }

    @Test
    public void preOrder_raizPrimero() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        List<Integer> resultado = new ArrayList<>();
        raiz.preOrder(nodo -> resultado.add(nodo.getDato()));

        assertEquals(List.of(10, 5, 3, 7, 15, 20), resultado);
    }

    @Test
    public void postOrder_raizAlFinal() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        List<Integer> resultado = new ArrayList<>();
        raiz.postOrder(nodo -> resultado.add(nodo.getDato()));

        assertEquals(List.of(3, 7, 5, 20, 15, 10), resultado);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // esHoja
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void esHoja_sinHijos() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(5);
        assertTrue(nodo.esHoja());
    }

    @Test
    public void esHoja_conHijoIzq() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        raiz.insertar(5);
        assertFalse(raiz.esHoja());
    }

    @Test
    public void esHoja_conHijoDer() {
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(10);
        raiz.insertar(15);
        assertFalse(raiz.esHoja());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // cantidadHojas
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void cantidadHojas_nodoUnico() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(10);
        assertEquals(1, nodo.cantidadHojas());
    }

    @Test
    public void cantidadHojas_arbolReferencia() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertEquals(3, raiz.cantidadHojas());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // cantidadNodosInternos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void cantidadNodosInternos_nodoUnico() {
        ElementoABBImpl<Integer> nodo = new ElementoABBImpl<>(10);
        assertEquals(0, nodo.cantidadNodosInternos());
    }

    @Test
    public void cantidadNodosInternos_arbolReferencia() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertEquals(3, raiz.cantidadNodosInternos());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // cantidadNodos
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void cantidadNodos_nodoUnico() {
        assertEquals(1, new ElementoABBImpl<>(10).cantidadNodos());
    }

    @Test
    public void cantidadNodos_arbolReferencia() {
        assertEquals(6, arbolReferencia().cantidadNodos());
    }

    @Test
    public void cantidadNodos_consistencia() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertEquals(raiz.cantidadNodos(),
                raiz.cantidadHojas() + raiz.cantidadNodosInternos());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // altura
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void altura_nodoUnico() {
        assertEquals(1, new ElementoABBImpl<>(10).altura());
    }

    @Test
    public void altura_arbolReferencia() {
        assertEquals(3, arbolReferencia().altura());
    }

    @Test
    public void altura_arbolDegenerado() {
        // inserciones en orden ascendente → lista hacia la derecha
        ElementoABBImpl<Integer> raiz = new ElementoABBImpl<>(1);
        raiz.insertar(2);
        raiz.insertar(3);
        raiz.insertar(4);
        assertEquals(4, raiz.altura());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // obtenerNivel
    // ═══════════════════════════════════════════════════════════════════════════

    @Test
    public void obtenerNivel_raiz() {
        assertEquals(0, arbolReferencia().obtenerNivel(10));
    }

    @Test
    public void obtenerNivel_nivel1() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertEquals(1, raiz.obtenerNivel(5));
        assertEquals(1, raiz.obtenerNivel(15));
    }

    @Test
    public void obtenerNivel_nivel2() {
        ElementoABBImpl<Integer> raiz = arbolReferencia();
        assertEquals(2, raiz.obtenerNivel(3));
        assertEquals(2, raiz.obtenerNivel(7));
        assertEquals(2, raiz.obtenerNivel(20));
    }

    @Test
    public void obtenerNivel_inexistente() {
        assertEquals(-1, arbolReferencia().obtenerNivel(99));
    }

   
}