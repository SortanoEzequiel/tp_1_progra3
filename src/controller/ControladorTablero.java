package controller;

import vista.Vista;
import vista.VistaTablero;
import modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del tablero: maneja clicks en celdas, el boton "Comprobar",
 * y los botones Reintentar / Cambiar Tablero.
 *
 * - estadoJugador guarda las celdas que el usuario marcó (true = negra).
 * - Al reintentar con el mismo tablero la vista se recrea desde Vista.reintentarMismoTablero(...)
 */
public class ControladorTablero implements ActionListener {

    private final Tablero tablero;
    private final VistaTablero vista;
    private final Vista app;
    private boolean[][] estadoJugador;

    public ControladorTablero(Tablero tablero, VistaTablero vista, Vista app) {
        this.tablero = tablero;
        this.vista = vista;
        this.app = app;
        this.estadoJugador = new boolean[tablero.getFilas()][tablero.getColumnas()];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd == null) return;

        try {
            if (cmd.contains(",")) {
                // click en celda "fila,col"
                String[] partes = cmd.split(",");
                int fila = Integer.parseInt(partes[0]);
                int col = Integer.parseInt(partes[1]);

                JButton fuente = (JButton) e.getSource();
                boolean nuevaMarca = !estadoJugador[fila][col];
                estadoJugador[fila][col] = nuevaMarca;
                // actualizar vista
                vista.setCeldaMarcada(fila, col, nuevaMarca);

            } else if (cmd.equals("COMPROBAR")) {
                // usuario pide comprobar su solución actual
                if (esVictoria()) {
                    mostrarDialogoVictoria();
                } else {
                    mostrarDialogoDerrota();
                }

            } else if (cmd.equals("REINTENTAR")) {
                // volver a intentar con el mismo tablero -> recrea la vista pero mantiene la solución
                app.reintentarMismoTablero(tablero);

            } else if (cmd.equals("CAMBIAR")) {
                // volver a pantalla inicio para elegir otra dificultad
                app.mostrarPantallaInicio();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean esVictoria() {
        boolean[][] solucion = tablero.getCeldas();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                // si la solución tiene true (negra) debe estar marcada por el jugador
                // y si la solución es false, el jugador no debe marcarla.
                if (solucion[i][j] != estadoJugador[i][j]) return false;
            }
        }
        return true;
    }

    private void mostrarDialogoVictoria() {
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¡Felicidades! Completaste correctamente el Nanograma.\n¿Qué querés hacer ahora?",
                "Victoria",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Volver a intentar (mismo tablero)", "Cambiar de tablero", "Salir"},
                "Volver a intentar (mismo tablero)"
        );

        switch (opcion) {
            case 0: // volver a intentar con el mismo tablero
                app.reintentarMismoTablero(tablero);
                break;
            case 1: // cambiar tablero
                app.mostrarPantallaInicio();
                break;
            default:
                System.exit(0);
        }
    }

    private void mostrarDialogoDerrota() {
        int opcion = JOptionPane.showOptionDialog(
                null,
                "La solución actual no coincide con el tablero.\n¿Qué querés hacer?",
                "Comprobación incorrecta",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Volver a intentar (mismo tablero)", "Cambiar de tablero", "Salir"},
                "Volver a intentar (mismo tablero)"
        );

        switch (opcion) {
            case 0:
                // recreamos la vista con el mismo tablero (estadoJugador se reiniciará en el nuevo controlador)
                app.reintentarMismoTablero(tablero);
                break;
            case 1:
                app.mostrarPantallaInicio();
                break;
            default:
                System.exit(0);
        }
    }
}
