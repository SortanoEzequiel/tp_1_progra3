package vista;

import controller.ControladorInicio;
import controller.ControladorTablero;
import nanograma.Nanograma;
import modelo.Tablero;

import javax.swing.*;
import java.awt.*;

/**
 * Orquestador principal de la aplicación. Expone métodos que usan los controladores
 * para: iniciar juego nuevo, reintentar mismo tablero y volver a la pantalla de inicio.
 */
public class Vista {

    private final VistaPantallaInicio pantallaInicio;
    private JFrame ventanaJuego;
    private Nanograma nanograma;

    public Vista() {
        // aplicar un Look & Feel más moderno si está disponible
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        pantallaInicio = new VistaPantallaInicio();
        // Controlador de inicio conecta con esta Vista
        pantallaInicio.setControlador(new ControladorInicio(this));
        pantallaInicio.setVisible(true);
    }

    /**
     * Inicia un juego nuevo (genera nueva solución/tablero).
     */
    public void iniciarJuego(int filas, int columnas) {
        // cerrar pantalla inicio
        pantallaInicio.setVisible(false);

        // crear nuevo nanograma con tablero nuevo
        nanograma = new Nanograma(filas, columnas);
        Tablero tablero = nanograma.getTablero();

        mostrarTablero(tablero);
    }

    /**
     * Muestra un tablero usando el modelo pasado (no crea nueva solución).
     * Se usa para reintentar con el mismo tablero.
     */
    public void reintentarMismoTablero(Tablero tablero) {
        // cerrar ventana anterior si existe
        if (ventanaJuego != null) ventanaJuego.dispose();
        mostrarTablero(tablero);
    }

    /**
     * Muestra la pantalla de inicio (para cambiar dificultad, etc).
     */
    public void mostrarPantallaInicio() {
        if (ventanaJuego != null) {
            ventanaJuego.dispose();
            ventanaJuego = null;
        }
        pantallaInicio.setVisible(true);
    }

    /**
     * Método privado que crea la ventana del tablero, instancia la vista/ controlador
     * y conecta todos los listeners limpitos.
     */
    private void mostrarTablero(Tablero tablero) {
        ventanaJuego = new JFrame("Nanograma - Tablero");
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setSize(500, 500);
        ventanaJuego.setLocationRelativeTo(null);

        VistaTablero vistaTablero = new VistaTablero(tablero);
        ControladorTablero controladorTablero = new ControladorTablero(tablero, vistaTablero, this);

        // conectar listeners
        vistaTablero.addListenerToAllCells(controladorTablero);
        vistaTablero.addComprobarListener(controladorTablero);
        vistaTablero.addReintentarListener(controladorTablero);
        vistaTablero.addCambiarTableroListener(controladorTablero);

        ventanaJuego.getContentPane().add(vistaTablero, BorderLayout.CENTER);
        ventanaJuego.setVisible(true);
    }

    // punto de entrada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Vista::new);
    }
}
