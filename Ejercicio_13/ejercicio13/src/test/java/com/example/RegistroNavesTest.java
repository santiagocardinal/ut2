package com.example;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class RegistroNavesTest {

    private RegistroNaves registro;

    @Before
    public void setUp() {
        registro = new RegistroNaves();
    }
    //constructor y getters
    @Test
    public void nave_getters() {
        RegistroNaves.Nave nave = new RegistroNaves.Nave(42, "Explorador", 75);
        assertEquals(42,           nave.getCodigo());
        assertEquals("Explorador", nave.getClase());
        assertEquals(75,           nave.getCombustible());
    }

    @Test
    public void nave_compareToMenor() {
        RegistroNaves.Nave n1 = new RegistroNaves.Nave(10, "Explorador", 0);
        RegistroNaves.Nave n2 = new RegistroNaves.Nave(20, "Destructor", 0);
        assertTrue(n1.compareTo(n2) < 0);
    }

    @Test
    public void nave_compareToMayor() {
        RegistroNaves.Nave n1 = new RegistroNaves.Nave(20, "Explorador", 0);
        RegistroNaves.Nave n2 = new RegistroNaves.Nave(10, "Destructor", 0);
        assertTrue(n1.compareTo(n2) > 0);
    }

    @Test
    public void nave_compareToIgual() {
        RegistroNaves.Nave n1 = new RegistroNaves.Nave(10, "Explorador", 0);
        RegistroNaves.Nave n2 = new RegistroNaves.Nave(10, "Destructor", 0);
        assertEquals(0, n1.compareTo(n2));
    }

    @Test
    public void nave_toString() {
        RegistroNaves.Nave nave = new RegistroNaves.Nave(10, "Explorador", 50);
        assertEquals("(10, \"Explorador\", 50)", nave.toString());
    }

  
    //registro vacío
    @Test
    public void registroVacio_codigosVacio() {
        assertTrue(registro.obtenerCodigosExploradoras().isEmpty());
    }

    @Test
    public void registroVacio_promedioEsCero() {
        assertEquals(0.0, registro.promedioCombustibleExplorador(), 0.0001);
    }

    
    //sin naves exploradoras

    @Test
    public void sinExploradores_codigosVacio() {
        registro.insertar(new RegistroNaves.Nave(10, "Destructor", 90));
        registro.insertar(new RegistroNaves.Nave(20, "Carguero",   20));
        assertTrue(registro.obtenerCodigosExploradoras().isEmpty());
    }

    @Test
    public void sinExploradores_promedioEsCero() {
        registro.insertar(new RegistroNaves.Nave(10, "Médica", 100));
        assertEquals(0.0, registro.promedioCombustibleExplorador(), 0.0001);
    }

    // obtenerCodigosExploradoras

    @Test
    public void obtenerCodigos_unaExploradora() {
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 50));
        assertEquals(Arrays.asList(10), registro.obtenerCodigosExploradoras());
    }

    @Test
    public void obtenerCodigos_variasMezcladas() {
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 0));
        registro.insertar(new RegistroNaves.Nave(20, "Destructor", 90));
        registro.insertar(new RegistroNaves.Nave(40, "Explorador", 50));
        assertEquals(Arrays.asList(10, 40), registro.obtenerCodigosExploradoras());
    }

    @Test
    public void obtenerCodigos_ordenadosPorCodigo() {
        // Se insertan fuera de orden; el inorden del AVL los devuelve ordenados
        registro.insertar(new RegistroNaves.Nave(70, "Explorador", 14));
        registro.insertar(new RegistroNaves.Nave(10, "Explorador",  0));
        registro.insertar(new RegistroNaves.Nave(40, "Explorador", 50));
        assertEquals(Arrays.asList(10, 40, 70), registro.obtenerCodigosExploradoras());
    }
    // promedioCombustibleExplorador

    @Test
    public void promedio_unaSolaExploradora() {
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 80));
        assertEquals(80.0, registro.promedioCombustibleExplorador(), 0.0001);
    }

    @Test
    public void promedio_variasExploradoras() {
        // (0 + 50 + 14 + 26) / 4 = 22.5
        registro.insertar(new RegistroNaves.Nave(10,  "Explorador",  0));
        registro.insertar(new RegistroNaves.Nave(40,  "Explorador", 50));
        registro.insertar(new RegistroNaves.Nave(70,  "Explorador", 14));
        registro.insertar(new RegistroNaves.Nave(100, "Explorador", 26));
        assertEquals(22.5, registro.promedioCombustibleExplorador(), 0.0001);
    }

    @Test
    public void promedio_ignoraNavesDOtraClase() {
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 60));
        registro.insertar(new RegistroNaves.Nave(20, "Destructor", 200));
        registro.insertar(new RegistroNaves.Nave(30, "Explorador", 40));
        assertEquals(50.0, registro.promedioCombustibleExplorador(), 0.0001);
    }

    // RegistroNaves: insertar duplicado

    @Test
    public void insertarDuplicadoNoloAgrega() {
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 50));
        registro.insertar(new RegistroNaves.Nave(10, "Explorador", 50));
        List<Integer> codigos = registro.obtenerCodigosExploradoras();
        assertEquals(1, codigos.size());
        assertEquals(10, (int) codigos.get(0));
    }
}
