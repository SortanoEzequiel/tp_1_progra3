package modelo;

import java.util.Random;

public class Tablero {
    private int filas;
    private int columnas;
    private boolean[][] celdas;
    private int[][] pistasFilas;
    private int[][] pistasColumnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new boolean[filas][columnas];
        generarTableroAleatorio();
        generarPistas();
    }

    private void generarTableroAleatorio() {
        Random rand = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = rand.nextBoolean();
            }
        }
    }

    private void generarPistas() {
        pistasFilas = new int[filas][];
        pistasColumnas = new int[columnas][];

        // Pistas filas
        for (int i = 0; i < filas; i++) {
            pistasFilas[i] = contarSecuencias(celdas[i]);
        }

        // Pistas columnas
        for (int j = 0; j < columnas; j++) {
            boolean[] columna = new boolean[filas];
            for (int i = 0; i < filas; i++) {
                columna[i] = celdas[i][j];
            }
            pistasColumnas[j] = contarSecuencias(columna);
        }
    }

    private int[] contarSecuencias(boolean[] linea) {
        int contador = 0;
        java.util.List<Integer> secuencias = new java.util.ArrayList<>();

        for (boolean celda : linea) {
            if (celda) {
                contador++;
            } else if (contador > 0) {
                secuencias.add(contador);
                contador = 0;
            }
        }
        if (contador > 0) {
            secuencias.add(contador);
        }

        if (secuencias.isEmpty()) {
            return new int[]{0};
        }

        return secuencias.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public boolean[][] getCeldas() { return celdas; }
    public int[][] getPistasFilas() { return pistasFilas; }
    public int[][] getPistasColumnas() { return pistasColumnas; }
}
