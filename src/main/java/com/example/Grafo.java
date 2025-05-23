package com.example;

/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

import java.io.*;
import java.util.*;

public class Grafo {
    private int numClimas = 4; // normal, lluvia, nieve, tormenta
    private List<Ciudad> ciudades = new ArrayList<>();
    private Map<String, Integer> mapCitys = new HashMap<>();
    private int[][][] matrizTiempos; // [clima][i][j]

    public int[][] getMatrizClima(int clima) {
    return matrizTiempos[clima];
    }

    public List<Ciudad> getCiudades() {
    return ciudades;
    }

    public int getIndiceCiudad(String nombre) {
    return (int) mapCitys.getOrDefault(nombre, -1);
    }


    public void cargarGrafosDeArchivo(String nom) {
        try (BufferedReader br = new BufferedReader(new FileReader(nom))) {
            List<String[]> conexiones = new ArrayList<>();
            String linea;

            // Leer todas las conexiones y guardar temporalmente
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(" ");
                //System.out.println("Línea leída: " + linea); // Ver línea leída
                if (c.length != 6) continue;

                String ciudad1 = c[0];
                String ciudad2 = c[1];

                // Agregar ciudades al mapa y lista si no existen
                if (!mapCitys.containsKey(ciudad1)) {
                    mapCitys.put(ciudad1, ciudades.size());
                    ciudades.add(new Ciudad(ciudad1, ciudades.size()));
                    //System.out.println("Ciudad agregada: " + ciudad1);
                }
                if (!mapCitys.containsKey(ciudad2)) {
                    mapCitys.put(ciudad2, ciudades.size());
                    ciudades.add(new Ciudad(ciudad2, ciudades.size()));
                   //System.out.println("Ciudad agregada: " + ciudad2);
                }

                conexiones.add(c); // Guardamos la conexión para después
            }

            // Mostrar las conexiones interpretadas
            // System.out.println("\nConexiones cargadas:");
            // for (String[] datos : conexiones) {
            //     System.out.println(Arrays.toString(datos));
            // }


            //Inicializar la matriz de tiempos
            int n = ciudades.size();
            matrizTiempos = new int[numClimas][n][n];


            // Inicializamos la matriz con valores altos (infinito)
            //Tiempo de ciudad en si misma 0
            for (int clima = 0; clima < numClimas; clima++) {
                for (int i = 0; i < n; i++) {
                    Arrays.fill(matrizTiempos[clima][i], Integer.MAX_VALUE / 2);
                    matrizTiempos[clima][i][i] = 0;
                }
            }

            // Rellenar la matriz con los datos leídos
            for (String[] datos : conexiones) {
                int i = mapCitys.get(datos[0]);
                int j = mapCitys.get(datos[1]);

                for (int clima = 0; clima < numClimas; clima++) {
                    matrizTiempos[clima][i][j] = Integer.parseInt(datos[2 + clima]);
                }
            }


        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
