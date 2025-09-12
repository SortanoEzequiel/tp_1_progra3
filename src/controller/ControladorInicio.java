package controller;

import modelo.*;
import vista.VistaTablero;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControladorInicio implements CeldaListener {
    private Tablero tablero;
    private VistaTablero vista;
    private CreacionTablero generador;
    private String dificultadActual;

    public void iniciarJuego(String dificultad) {
        this.dificultadActual = dificultad;
        generador = new CreacionTablero();
        generador.generarTablero(dificultad);
        int[][] matriz = generador.obtenerMatrizJuego();

        tablero = new Tablero(matriz);
        tablero.agregarListener(this);

        vista = new VistaTablero(this);
        vista.generarTablero().setVisible(true);
    }



    public void celdaClickeada(int fila, int col) {
        tablero.actualizarEstado(fila, col);
    }
    public String generarPistaColumna(int col) {
        return tablero.generarPistaColumna(tablero.getSolucion(), col);
    }
    public String comprobarResultados() {
        return tablero.comprobarResultados();
    }
    public String generarPistaFila(int fila) {
        return tablero.generarPistaFila(tablero.getSolucion()[fila]);
    }
    public void reiniciarTablero() {
        if (tablero != null) {
            tablero.reiniciarEstados(); // ← mantiene la misma solución
        }
    }

    




    @Override
    public void celdaActualizada(EventoCambioCelda evento) {
        JButton celda = vista.getCelda(evento.getFila(), evento.getColumna());
        switch (evento.getNuevoEstado()) {
            case 0:
                celda.setBackground(java.awt.Color.WHITE);
                celda.setText("");
                break;
            case 1:
                celda.setBackground(java.awt.Color.BLACK);
                celda.setText("");
                break;
            case 2:
                celda.setBackground(java.awt.Color.WHITE);
                celda.setForeground(java.awt.Color.RED);
                celda.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 45));
                celda.setText("X");
                break;
        }
    }

    public void setCeldas(JButton[][] celdas) {
        vista.setCeldas(celdas);
    }
    public JFrame getVistaFrame() {
        return vista != null ? vista.generarTablero() : null;
    }
}
