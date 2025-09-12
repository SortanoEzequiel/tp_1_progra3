package vista;

import java.awt.*;
import javax.swing.*;
import controller.ControladorInicio;

public class VistaTablero {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] celdas = new JButton[5][5];
    private ControladorInicio controlador;
    private JPanel panelInferiorActual;
    private JPanel pistasColumnas;
    private JPanel pistasFilas;

    public VistaTablero(ControladorInicio controlador) {
        this.controlador = controlador;
    }

    public JFrame generarTablero() {
        frame = new JFrame("Nonograma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(5, 5));
        frame.add(panel, BorderLayout.CENTER);

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

                celda.addActionListener(e -> controlador.celdaClickeada(f, c));

                celdas[fila][col] = celda;
                panel.add(celda);
            }
        }

        controlador.setCeldas(celdas);
        agregarPistas();
        actualizarPanelInferior(wrapComprobarButton());

        return frame;
    }

    public void agregarPistas() {
        if (pistasColumnas != null) frame.remove(pistasColumnas);
        if (pistasFilas != null) frame.remove(pistasFilas);

        pistasColumnas = new JPanel(new GridLayout(1, 5));
        pistasFilas = new JPanel(new GridLayout(5, 1));

        for (int col = 0; col < 5; col++) {
            String pistaTexto = controlador.generarPistaColumna(col);
            String[] numeros = pistaTexto.split(" ");
            JPanel pistaColPanel = new JPanel(new GridLayout(numeros.length, 1));
            for (String num : numeros) {
                JLabel etiqueta = new JLabel(num, SwingConstants.CENTER);
                pistaColPanel.add(etiqueta);
            }
            pistasColumnas.add(pistaColPanel);
        }
        frame.add(pistasColumnas, BorderLayout.NORTH);

        for (int fila = 0; fila < 5; fila++) {
            String pistaTexto = controlador.generarPistaFila(fila);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }
        frame.add(pistasFilas, BorderLayout.WEST);
    }

    public JButton comprobarResultadosButton() {
        JButton comprobarBtn = new JButton("Comprobar");
        comprobarBtn.addActionListener(e -> {
            String mensaje = controlador.comprobarResultados();
            JOptionPane.showMessageDialog(frame, mensaje);

            JPanel opcionesPanel = new JPanel(new GridLayout(1, 2, 10, 10));

            if (mensaje.equals("¡Ganaste!")) {
                JButton jugarOtroBtn = new JButton("Jugar otro");
                jugarOtroBtn.addActionListener(ev -> {
                    frame.dispose();
                    controlador.iniciarJuego("facil"); // o dificultad actual
                });
                opcionesPanel.add(jugarOtroBtn);
            } else {
                JButton intentarDeNuevoBtn = new JButton("Intentar de nuevo");
                intentarDeNuevoBtn.addActionListener(ev -> reconstruirTablero());

                JButton cambiarTableroBtn = new JButton("Cambiar tablero");
                cambiarTableroBtn.addActionListener(ev -> {
                    frame.dispose();
                    controlador.iniciarJuego("facil"); // o nueva dificultad
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
        controlador.reiniciarTablero();

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

                celda.addActionListener(e -> controlador.celdaClickeada(f, c));

                celdas[fila][col] = celda;
                panel.add(celda);
            }
        }

        controlador.setCeldas(celdas);
        agregarPistas();
        actualizarPanelInferior(wrapComprobarButton());
        frame.revalidate();
        frame.repaint();
    }

    public void actualizarPanelInferior(JPanel nuevoPanel) {
        if (panelInferiorActual != null) {
            frame.remove(panelInferiorActual);
        }
        panelInferiorActual = nuevoPanel;
        frame.add(panelInferiorActual, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    public JPanel wrapComprobarButton() {
        JPanel panel = new JPanel();
        panel.add(comprobarResultadosButton());
        return panel;
    }
}
