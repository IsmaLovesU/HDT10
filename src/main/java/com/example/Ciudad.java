package com.example;

/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

public class Ciudad {
    private String nombre;
    private int indice;

    public Ciudad(String nombre, int indice) {
        this.nombre = nombre;
        this.indice = indice;
    }

    public String getNombre() { return nombre; }
    public int getIndice() { return indice; }
}
