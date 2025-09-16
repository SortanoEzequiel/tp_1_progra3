package modelo;

import java.util.Random;

public class CreacionTablero {
    public static Tablero crearTablero(int filas, int columnas) {
        return new Tablero(filas, columnas);
    }
}

