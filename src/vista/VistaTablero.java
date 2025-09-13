package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista del tablero con:
 * - grilla de botones (celdas)
 * - pistas (filas a la izquierda, columnas arriba)
 * - botones en el pie: Comprobar, Reintentar, Cambiar Tablero
 */
public class VistaTablero extends JPanel {

    private final JButton[][] botones;
    private final JButton botonComprobar;
    private final JButton botonReintentar;
    private final JButton botonCambiarTablero;

    public VistaTablero(modelo.Tablero tablero) {
        setLayout(new BorderLayout());

        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        // Panel central con la grilla de celdas
        JPanel panelCentral = new JPanel(new GridLayout(filas, columnas, 2, 2));
        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton celda = new JButton();
                celda.setPreferredSize(new Dimension(40, 40));
                celda.setBackground(Color.WHITE);
                celda.setOpaque(true);
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                // actionCommand con coordenadas "fila,col"
                celda.setActionCommand(i + "," + j);
                botones[i][j] = celda;
                panelCentral.add(celda);
            }
        }

        // Pistas filas (izquierda): texto horizontal "1 2"
        JPanel panelFilas = new JPanel(new GridLayout(filas, 1));
        panelFilas.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 8));
        for (int[] pistaFila : tablero.getPistasFilas()) {
            JLabel lbl = new JLabel(formatearHorizontal(pistaFila), SwingConstants.RIGHT);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            panelFilas.add(lbl);
        }

        // Pistas columnas (arriba): cada columna con números verticales
        JPanel panelColumnas = new JPanel(new GridLayout(1, columnas));
        panelColumnas.setBorder(BorderFactory.createEmptyBorder(8, 4, 4, 4));
        for (int[] pistaColumna : tablero.getPistasColumnas()) {
            panelColumnas.add(crearEtiquetaVertical(pistaColumna));
        }

        // Panel inferior con botones de control
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botonComprobar = new JButton("Comprobar");
        botonReintentar = new JButton("Reintentar");
        botonCambiarTablero = new JButton("Cambiar Tablero");

        botonComprobar.setActionCommand("COMPROBAR");
        botonReintentar.setActionCommand("REINTENTAR");
        botonCambiarTablero.setActionCommand("CAMBIAR");

        panelInferior.add(botonComprobar);
        panelInferior.add(botonReintentar);
        panelInferior.add(botonCambiarTablero);

        // Agregar subpaneles al principal
        add(panelColumnas, BorderLayout.NORTH);
        add(panelFilas, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // ---------- Métodos para conectar listeners ----------

    /**
     * Agrega el mismo ActionListener a todas las celdas de la grilla.
     * El ActionEvent.getActionCommand() será "fila,col".
     */
    public void addListenerToAllCells(ActionListener listener) {
        for (JButton[] fila : botones) {
            for (JButton btn : fila) {
                btn.addActionListener(listener);
            }
        }
    }

    public void addComprobarListener(ActionListener listener) {
        botonComprobar.addActionListener(listener);
    }

    public void addReintentarListener(ActionListener listener) {
        botonReintentar.addActionListener(listener);
    }

    public void addCambiarTableroListener(ActionListener listener) {
        botonCambiarTablero.addActionListener(listener);
    }

    // Permite que el controlador actualice visualmente una celda
    public void setCeldaMarcada(int fila, int columna, boolean marcada) {
        JButton btn = botones[fila][columna];
        btn.setBackground(marcada ? Color.BLACK : Color.WHITE);
    }

    // Reinicia visualmente todas las celdas (a blanco)
    public void resetVisualEstado() {
        for (JButton[] fila : botones) {
            for (JButton b : fila) {
                b.setBackground(Color.WHITE);
            }
        }
    }

    // ---------- Formateo de pistas ----------

    private String formatearHorizontal(int[] pista) {
        if (pista == null || pista.length == 0) return "-";
        // si la pista contiene un 0 sólo => significa "sin bloques", mostramos "-"
        if (pista.length == 1 && pista[0] == 0) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pista.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(pista[i]);
        }
        return sb.toString();
    }

    private JPanel crearEtiquetaVertical(int[] pista) {
        // Si pista = {0} mostramos un "-" en una sola celda
        if (pista == null || (pista.length == 1 && pista[0] == 0)) {
            JPanel p = new JPanel(new GridLayout(1, 1));
            JLabel l = new JLabel("-", SwingConstants.CENTER);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            p.add(l);
            return p;
        }

        JPanel p = new JPanel(new GridLayout(pista.length, 1));
        for (int num : pista) {
            JLabel l = new JLabel(String.valueOf(num), SwingConstants.CENTER);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            p.add(l);
        }
        return p;
    }
}
