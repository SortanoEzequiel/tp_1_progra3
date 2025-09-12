package modelo;

import java.util.Random;

public class CreacionTablero {

    private int[][] matrizJuego;
    private String dificultad = "";

    public CreacionTablero() {
    }

    // Método principal
    public void generarTablero(String dificultad) {
        this.dificultad = dificultad.toLowerCase().trim(); // Normalizamos entrada
        tamañoDeTablero(this.dificultad);
        llenarTablero(matrizJuego);
    }

    // Método opcional
    public void reiniciarTablero() {
        generarTablero(this.dificultad);
    }

    // --- Auxiliares ---

    private void tamañoDeTablero(String dificultad) {
        switch (dificultad) {
            case "facil":
                matrizJuego = new int[5][5];
                break;
            case "medio":
                matrizJuego = new int[10][10];
                break;
            case "dificil":
                matrizJuego = new int[15][15];
                break;
            default:
                matrizJuego = new int[5][5]; // Valor por defecto
                break;
        }
    }

    private void llenarTablero(int[][] tablero) {
        int tamaño = tablero.length;
        for (int fila = 0; fila < tamaño; fila++) {
            llenarFila(tablero[fila], tamaño);
        }
    }

    private void llenarFila(int[] fila, int tamaño) {
        Random aleatorio = new Random();
        for (int i = 0; i < tamaño; i++) {
            fila[i] = aleatorio.nextInt(2); // 0 o 1 directamente
        }
    }

    public int[][] obtenerMatrizJuego() {
        return matrizJuego;
    }

    public String getDificultad() {
        return dificultad;
    }
}
