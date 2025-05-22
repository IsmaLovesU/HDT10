package com.example;

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
            System.out.println("3.Modificar un grafo ");
            System.out.println("4. Agregar una nueva conexión ");
            System.out.println("5. Eliminar una conexión ");
            System.out.println("6. Salir");
            System.out.print("Ingrese el número de su elección: ");
            opcion = scanner.nextLine();
        
            switch (opcion) {
                case "1":
                    System.out.println("Ingrese la ciudad de origen");
                    System.out.println("Ingresa la ciudad del destino");

                    break;
                case "2":
                    System.out.print("Centro del grafo ");

                    break;
                case "3":
                    System.out.println("Modificar grafo");

                    break;
                case "4":
                    System.out.println("Agregar una conexión/ruta");

                    break;
                case "5":
                    System.out.print("Elimiar una conexión/ruta: ");

                    break;
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