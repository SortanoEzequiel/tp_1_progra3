package controller;

import javax.swing.JButton;
import javax.swing.JFrame;

import modelo.CreacionTablero;
import modelo.Tablero;
import vista.VistaTablero;

public class ControladorInicio {
    private Tablero tablero;
    private VistaTablero vista;
    private CreacionTablero creador;
    private String dificultad;

    public void iniciarJuego(String dificultad) {
        this.dificultad = dificultad;
        creador = new CreacionTablero();
        creador.generarTablero(dificultad);
        int[][] matriz = creador.obtenerMatrizJuego();
        tablero = new Tablero(matriz);
        vista = new VistaTablero(this);
        vista.generarTablero().setVisible(true);
    }

    public void celdaClickeada(int fila, int col) {
        tablero.actualizarEstado(fila, col);
    }

    public String comprobarResultados() {
        return tablero.comprobarResultados();
    }

    public String generarPistaFila(int fila) {
        return tablero.generarPistaFila(tablero.getSolucion()[fila]);
    }

    public String generarPistaColumna(int col) {
        return tablero.generarPistaColumna(tablero.getSolucion(), col);
    }

    public void reiniciarTablero() {
        tablero.reiniciarEstados();
    }

    public void setCeldas(JButton[][] celdas) {
        tablero.setCeldas(celdas);
    }
    public JFrame getVistaFrame() {
        return vista != null ? vista.generarTablero() : null;
    }

}
