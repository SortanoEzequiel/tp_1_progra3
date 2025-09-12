package modelo;

public class EventoCambioCelda {
    private final int fila;
    private final int columna;
    private final int nuevoEstado;

    public EventoCambioCelda(int fila, int columna, int nuevoEstado) {
        this.fila = fila;
        this.columna = columna;
        this.nuevoEstado = nuevoEstado;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public int getNuevoEstado() { return nuevoEstado; }
}
