package com.example;

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
