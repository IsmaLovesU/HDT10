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

public class FloydTest{

    @Test
    public void testRutaMasCorta() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", new int[]{5, 6, 7, 8});
        grafo.agregarConexion("B", "C", new int[]{3, 4, 5, 6});

        Floyd floyd = new Floyd(grafo.getMatrizClima(0), grafo.getCiudades());
        List<String> ruta = floyd.obtenerRuta(
            grafo.getIndiceCiudad("A"),
            grafo.getIndiceCiudad("C")
        );

        assertEquals(List.of("A", "B", "C"), ruta);
    }

    @Test
    public void testCentroDelGrafo() {
        Grafo grafo = new Grafo();
        grafo.agregarConexion("A", "B", new int[]{2, 3, 4, 5});
        grafo.agregarConexion("B", "C", new int[]{2, 3, 4, 5});
        grafo.agregarConexion("C", "A", new int[]{2, 3, 4, 5});

        Floyd floyd = new Floyd(grafo.getMatrizClima(0), grafo.getCiudades());
        String centro = floyd.obtenerCentroDelGrafo();

        assertNotNull(centro);
        assertTrue(List.of("A", "B", "C").contains(centro));
    }
}
