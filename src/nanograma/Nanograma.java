package nanograma;

import modelo.Tablero;

public class Nanograma {
    private Tablero tablero;

    public Nanograma(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void nuevoTablero(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
    }
}
