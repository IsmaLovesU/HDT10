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

    // Actualiza los tiempos entre dos ciudades existentes
    public boolean modificarConexion(String ciudad1, String ciudad2, int[] nuevosTiempos) {
        Integer i = mapCitys.get(ciudad1);
        Integer j = mapCitys.get(ciudad2);
        if (i == null || j == null) return false;

        for (int clima = 0; clima < numClimas; clima++) {
            matrizTiempos[clima][i][j] = nuevosTiempos[clima];
        }
        return true;
    }

    // Agrega una nueva conexión entre dos ciudades (si existen o las crea)
    public void agregarConexion(String ciudad1, String ciudad2, int[] tiempos) {
        int i = getOAgregarIndice(ciudad1);
        int j = getOAgregarIndice(ciudad2);

        for (int clima = 0; clima < numClimas; clima++) {
            matrizTiempos[clima][i][j] = tiempos[clima];
        }
    }

    // Elimina una conexión (establece valor infinito)
    public boolean eliminarConexion(String ciudad1, String ciudad2) {
        Integer i = mapCitys.get(ciudad1);
        Integer j = mapCitys.get(ciudad2);
        if (i == null || j == null) return false;

        for (int clima = 0; clima < numClimas; clima++) {
            matrizTiempos[clima][i][j] = Integer.MAX_VALUE / 2;
        }
        return true;
    }

    // Agrega ciudad si no existe y retorna el índice
    private int getOAgregarIndice(String ciudad) {
        if (!mapCitys.containsKey(ciudad)) {
            int nuevoIndice = ciudades.size();
            mapCitys.put(ciudad, nuevoIndice);
            ciudades.add(new Ciudad(ciudad, nuevoIndice));
            for (int clima = 0; clima < numClimas; clima++) {
                matrizTiempos[clima] = redimensionarMatriz(matrizTiempos[clima], nuevoIndice + 1);
            }
        }
        return mapCitys.get(ciudad);
    }

    // Redimensiona una matriz cuadrada (aumenta en 1)
    private int[][] redimensionarMatriz(int[][] matrizVieja, int nuevoTamaño) {
        int[][] nueva = new int[nuevoTamaño][nuevoTamaño];
        for (int[] fila : nueva)
            Arrays.fill(fila, Integer.MAX_VALUE / 2);
        for (int i = 0; i < nuevoTamaño - 1; i++) {
            for (int j = 0; j < nuevoTamaño - 1; j++) {
                nueva[i][j] = matrizVieja[i][j];
            }
            nueva[i][i] = 0;
        }
        nueva[nuevoTamaño - 1][nuevoTamaño - 1] = 0;
        return nueva;
    }


    //El centro es la ciudad con menor excentricidad. Su camino más largo hacia cualquier otra ciudad es el más corto posible
    public List<Centro> calcularCentros() {
        List<Centro> centros = new ArrayList<>();
        String[] nombresClimas = {"Normal", "Lluvia", "Nieve", "Tormenta"};

        for (int clima = 0; clima < numClimas; clima++) {
            Floyd floyd = new Floyd(matrizTiempos[clima], ciudades);
            int[][] distancias = floyd.getDistancias();

            int n = ciudades.size();
            int centro = -1;
            int menorExcentricidad = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int excentricidad = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        excentricidad = Math.max(excentricidad, distancias[i][j]);
                    }
                }

                if (excentricidad < menorExcentricidad) {
                    menorExcentricidad = excentricidad;
                    centro = i;
                }
            }

            if (centro != -1) {
                centros.add(new Centro(nombresClimas[clima], ciudades.get(centro).getNombre(), menorExcentricidad));
            }
        }

        return centros;
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
