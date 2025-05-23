package com.example;

/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

import java.util.*;

public class Floyd {
    private final int INF = Integer.MAX_VALUE / 2;
    private int[][] dist;
    private int[][] next;
    private List<Ciudad> ciudades;

    public Floyd(int[][] matriz, List<Ciudad> ciudades) {
        int n = matriz.length;
        this.ciudades = ciudades;
        dist = new int[n][n];
        next = new int[n][n];

        // Inicializar
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = matriz[i][j];
                if (matriz[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Aplicar Floyd-Warshall
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
    }

    public List<String> obtenerRuta(int u, int v) {
        if (next[u][v] == -1) return null;
        List<String> ruta = new ArrayList<>();
        while (u != v) {
            ruta.add(ciudades.get(u).getNombre());
            u = next[u][v];
        }
        ruta.add(ciudades.get(v).getNombre());
        return ruta;
    }

    public int[][] getDistancias() {
        return dist;
    }

    public String obtenerCentroDelGrafo() {
        int n = dist.length;
        int[] excentricidad = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] > max) {
                    max = dist[i][j];
                }
            }
            excentricidad[i] = max;
        }

        int centro = 0;
        for (int i = 1; i < n; i++) {
            if (excentricidad[i] < excentricidad[centro]) {
                centro = i;
            }
        }

        return ciudades.get(centro).getNombre();
    }
}
