package com.example;

/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {

    @Test
    public void testAgregarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("CiudadA", "CiudadB", new int[]{5, 6, 8, 10});
        
        int[][] matriz = grafo.getMatrizClima(0);
        int i = grafo.getIndiceCiudad("CiudadA");
        int j = grafo.getIndiceCiudad("CiudadB");

        assertTrue(matriz[i][j] == 5);
    }

    @Test
    public void testModificarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("Xela", "Guate", new int[]{10, 12, 15, 20});
        boolean modificado = grafo.modificarConexion("Xela", "Guate", new int[]{2, 3, 5, 7});

        int tiempo = grafo.getMatrizClima(0)[grafo.getIndiceCiudad("Xela")][grafo.getIndiceCiudad("Guate")];
        assertTrue(modificado);
        assertEquals(2, tiempo);
    }

    @Test
    public void testEliminarConexion() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("Mixco", "VillaNueva", new int[]{10, 15, 20, 30});
        grafo.eliminarConexion("Mixco", "VillaNueva");

        int valor = grafo.getMatrizClima(0)[grafo.getIndiceCiudad("Mixco")][grafo.getIndiceCiudad("VillaNueva")];
        assertTrue(valor >= Integer.MAX_VALUE / 3);
    }

    @Test
    public void testCargaDesdeArchivo() {
        Grafo grafo = new Grafo();
        grafo.cargarGrafosDeArchivo("resources/logistica.txt");
        assertTrue(grafo.getCiudades().size() > 0);
    }
}

