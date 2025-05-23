package com.example;
/**
 * Universidad del Valle de Guatemala 
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/

/**
 * Clase que representa la información del centro del grafo para un clima específico.
 * Guarda el nombre del clima, la ciudad que actúa como centro, y su excentricidad.
 */
public class Centro {
    private String clima;
    private String ciudadCentro;
    private int excentricidad;

    public Centro(String clima, String ciudadCentro, int excentricidad) {
        this.clima = clima;
        this.ciudadCentro = ciudadCentro;
        this.excentricidad = excentricidad;
    }

    public String getClima() {
        return clima;
    }

    public String getCiudadCentro() {
        return ciudadCentro;
    }

    public int getExcentricidad() {
        return excentricidad;
    }
}

