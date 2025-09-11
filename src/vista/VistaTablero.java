package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import controller.ControladorInicio;
import modelo.CreacionTablero;
import modelo.Tablero;

public class VistaTablero {
    private JFrame frame;
    private Tablero tablero;
    private JPanel panel;
    private JButton[][] celdas = new JButton[5][5];
    private ControladorInicio controlador;
    private JPanel panelInferiorActual;
    private JPanel pistasColumnas;
    private JPanel pistasFilas;

    public VistaTablero() {
        CreacionTablero creador = new CreacionTablero();
        creador.generarTablero("facil");
        int[][] matrizJuego = creador.obtenerMatrizJuego();
        this.tablero = new Tablero(matrizJuego);
    }

    public JFrame generarTablero() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(5, 5));
        frame.getContentPane().add(panel);

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                JButton celda = new JButton();
                final int f = fila;
                final int c = col;

                celda.setOpaque(true);
                celda.setContentAreaFilled(true);
                celda.setBorderPainted(true);
                celda.setFocusPainted(false);
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                celda.setBackground(Color.WHITE);

                celda.addActionListener(e -> tablero.actualizarEstado(f, c));

                celdas[fila][col] = celda;
                panel.add(celda);
            }
        }

        tablero.setCeldas(celdas);
        agregarPistas(frame);
        actualizarPanelInferior(wrapComprobarButton());
        return frame;
    }

    public void agregarPistas(JFrame frame) {
        if (pistasColumnas != null) frame.getContentPane().remove(pistasColumnas);
        if (pistasFilas != null) frame.getContentPane().remove(pistasFilas);

        pistasColumnas = new JPanel(new GridLayout(1, 5));
        pistasFilas = new JPanel(new GridLayout(5, 1));

        for (int col = 0; col < tablero.getSolucion()[0].length; col++) {
            String pistaTexto = tablero.generarPistaColumna(tablero.getSolucion(), col);
            String[] numeros = pistaTexto.split(" ");
            JPanel pistaColPanel = new JPanel(new GridLayout(numeros.length, 1));
            for (String num : numeros) {
                JLabel etiqueta = new JLabel(num, SwingConstants.CENTER);
                pistaColPanel.add(etiqueta);
            }
            pistasColumnas.add(pistaColPanel);
        }
        frame.getContentPane().add(pistasColumnas, BorderLayout.NORTH);

        for (int fila = 0; fila < tablero.getSolucion().length; fila++) {
            String pistaTexto = tablero.generarPistaFila(tablero.getSolucion()[fila]);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }
        frame.getContentPane().add(pistasFilas, BorderLayout.WEST);
    }

    public JButton comprobarResultadosButton() {
        JButton comprobarBtn = new JButton("Comprobar");
        comprobarBtn.addActionListener(e -> {
            String mensaje = tablero.comprobarResultados();
            JOptionPane.showMessageDialog(frame, mensaje);

            JPanel opcionesPanel = new JPanel(new GridLayout(1, 2, 10, 10));

            if (mensaje.equals("¡Ganaste!")) {
                JButton jugarOtroBtn = new JButton("Jugar otro");
                jugarOtroBtn.addActionListener(ev -> {
                    frame.dispose();
                    controlador = new ControladorInicio();
                    controlador.iniciarJuego();
                });
                opcionesPanel.add(jugarOtroBtn);
            } else {
                JButton intentarDeNuevoBtn = new JButton("Intentar de nuevo");
                intentarDeNuevoBtn.addActionListener(ev -> reconstruirTablero());

                JButton cambiarTableroBtn = new JButton("Cambiar tablero");
                cambiarTableroBtn.addActionListener(ev -> {
                    frame.dispose();
                    VistaTablero nuevaVista = new VistaTablero();
                    JFrame nuevoFrame = nuevaVista.generarTablero();
                    nuevoFrame.setVisible(true);
                });

                opcionesPanel.add(intentarDeNuevoBtn);
                opcionesPanel.add(cambiarTableroBtn);
            }

            actualizarPanelInferior(opcionesPanel);
        });
        return comprobarBtn;
    }

    public void reconstruirTablero() {
        panel.removeAll();
        tablero.reiniciarEstados();

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                JButton celda = new JButton();
                final int f = fila;
                final int c = col;

                celda.setOpaque(true);
                celda.setContentAreaFilled(true);
                celda.setBorderPainted(true);
                celda.setFocusPainted(false);
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                celda.setBackground(Color.WHITE);

                celda.addActionListener(e -> tablero.actualizarEstado(f, c));

                celdas[fila][col] = celda;
                panel.add(celda);
            }
        }

        tablero.setCeldas(celdas);
        agregarPistas(frame);
        actualizarPanelInferior(wrapComprobarButton());
        frame.revalidate();
        frame.repaint();
    }

    public void actualizarPanelInferior(JPanel nuevoPanel) {
        if (panelInferiorActual != null) {
            frame.getContentPane().remove(panelInferiorActual);
        }
        panelInferiorActual = nuevoPanel;
        frame.getContentPane().add(panelInferiorActual, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    public JPanel wrapComprobarButton() {
        JPanel panel = new JPanel();
        panel.add(comprobarResultadosButton());
        return panel;
    }
}
