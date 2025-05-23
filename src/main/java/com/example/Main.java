package com.example;

import java.util.List;

/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        //Para leer el arhivo y cargar los grafos
        Grafo grafo = new Grafo();
        grafo.cargarGrafosDeArchivo("resources/logistica.txt");

        String opcion ="";

        while (!opcion.equals("6")) {  // Usamos .equals() en lugar de !=
            System.out.println("MENU");
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar un grafo ");
            System.out.println("4. Agregar una nueva conexión ");
            System.out.println("5. Eliminar una conexión ");
            System.out.println("6. Salir");
            System.out.print("Ingrese el número de su elección: ");
            opcion = scanner.nextLine();
        
            switch (opcion) {

                // Ruta más corta con tiempo
                case "1":
                    // Ingreso de la ciuddad origen y destino para el calculo
                    System.out.print("Ingrese la ciudad de origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    String destino = scanner.nextLine();

                    // Obtención de los indices por ciudad
                    int indiceOrigen = grafo.getIndiceCiudad(origen);
                    int indiceDestino = grafo.getIndiceCiudad(destino);

                    // No se permiten valores negativos
                    if (indiceOrigen == -1 || indiceDestino == -1) {
                        System.out.println("Una o ambas ciudades no existen.");
                        break;
                    }

                    // Implemetación del algoritmo por matrzi de adyacencia
                    Floyd floyd1 = new Floyd(grafo.getMatrizClima(0), grafo.getCiudades());
                    List<String> ruta = floyd1.obtenerRuta(indiceOrigen, indiceDestino);
                    int[][] distancias = floyd1.getDistancias();

                    // Comprobaciones y respuesta 
                    if (ruta == null) {
                        System.out.println("No hay ruta disponible.");
                    } else {
                        System.out.println("Ruta más corta: " + String.join(" -> ", ruta));
                        System.out.println("Tiempo total: " + distancias[indiceOrigen][indiceDestino] + " horas");
                    }
                    break;

                // Centro del grafo
                case "2":
                    Floyd floyd2 = new Floyd(grafo.getMatrizClima(0), grafo.getCiudades());
                    String centro = floyd2.obtenerCentroDelGrafo();
                    System.out.println("Centro del grafo: " + centro);
                    break;
                // Modificar conexión entre nodos o "ciudades"
                case "3":
                    // Ingreso de las ciudades para la modificación de la conexión entre ellas
                    System.out.println("Modificar conexión entre ciudades:");
                    System.out.print("Ciudad origen: ");
                    String origenMod = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destinoMod = scanner.nextLine();

                    // Nuevos valores para la arista 
                    int[] nuevosTiempos = new int[4];
                    System.out.print("Nuevo tiempo con clima normal: ");
                    nuevosTiempos[0] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo tiempo con lluvia: ");
                    nuevosTiempos[1] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo tiempo con nieve: ");
                    nuevosTiempos[2] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo tiempo con tormenta: ");
                    nuevosTiempos[3] = Integer.parseInt(scanner.nextLine());

                    // Comprobación y modificación
                    if (grafo.modificarConexion(origenMod, destinoMod, nuevosTiempos)) {
                        System.out.println("Conexión actualizada.");
                    } else {
                        System.out.println("No se pudo modificar la conexión.");
                    }
                    break;
                
                // Agregar una conexión nueva 
                case "4":
                    // Ingreso de las ciudades para la agregación de una nueva arista entre ellas
                    System.out.println("Agregar nueva conexión entre ciudades:");
                    System.out.print("Ciudad origen: ");
                    String ciudad1 = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String ciudad2 = scanner.nextLine();

                    // Ingreso de los valores de la arista
                    int[] tiemposAgregar = new int[4];
                    System.out.print("Tiempo con clima normal: ");
                    tiemposAgregar[0] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tiempo con lluvia: ");
                    tiemposAgregar[1] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tiempo con nieve: ");
                    tiemposAgregar[2] = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tiempo con tormenta: ");
                    tiemposAgregar[3] = Integer.parseInt(scanner.nextLine());

                    // Agregación final de la conexión
                    grafo.agregarConexion(ciudad1, ciudad2, tiemposAgregar);
                    System.out.println("Conexión agregada.");
                    break;
                
                // Eliminar la conexión entre nodos 
                case "5":
                    // Ingreso de la ciudades para eliminar la conexión entre ellas
                    System.out.println("Eliminar conexión entre ciudades:");
                    System.out.print("Ciudad origen: ");
                    String origenEli = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destinoEli = scanner.nextLine();

                    // Comprobación y eliminación final
                    if (grafo.eliminarConexion(origenEli, destinoEli)) {
                        System.out.println("Conexión eliminada.");
                    } else {
                        System.out.println("No se pudo eliminar la conexión.");
                    }
                    break;

                // Salida del programa 
                case "6":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close(); 
    }
}