package vista;

import controller.ControladorInicio;

import javax.swing.*;
import java.awt.*;

public class VistaTablero {
    private final ControladorInicio controlador;
    private final JButton[][] celdas = new JButton[5][5];
    private final JLabel[] pistasFila = new JLabel[5];
    private final JLabel[] pistasColumna = new JLabel[5];
    private JPanel panelPrincipal;
    private JPanel panelInferiorActual;
    private JPanel pistasColumnas;
    private JPanel pistasFilas;
    private JPanel panel;
    private JFrame frame;
    private JPanel centro;


    public VistaTablero(ControladorInicio controlador) {
        this.controlador = controlador;
    }

    public JFrame generarTablero() {
        frame = new JFrame("Nonograma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());

        // Pistas de columna (arriba)
        pistasColumnas = new JPanel(new GridLayout(1, 5));
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
        panelPrincipal.add(pistasColumnas, BorderLayout.NORTH);

        // Pistas de fila (izquierda)
        pistasFilas = new JPanel(new GridLayout(5, 1));
        for (int fila = 0; fila < 5; fila++) {
            String pistaTexto = controlador.generarPistaFila(fila);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }

        // Panel de celdas
        panel = new JPanel(new GridLayout(5, 5));
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

        // Combinar pistas de fila y tablero
        centro = new JPanel(new BorderLayout());
        centro.add(pistasFilas, BorderLayout.WEST);
        centro.add(panel, BorderLayout.CENTER);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        // Panel inferior con botón Comprobar
        actualizarPanelInferior(wrapComprobarButton());

        frame.add(panelPrincipal);
        return frame;
    }

    public JButton getCelda(int fila, int col) {
        return celdas[fila][col];
    }

    public void setCeldas(JButton[][] nuevasCeldas) {
        for (int i = 0; i < 5; i++)
            System.arraycopy(nuevasCeldas[i], 0, celdas[i], 0, 5);
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

    public void agregarPistas() {
    	if (pistasFilas != null && pistasFilas.getParent() != null) {
    	    pistasFilas.getParent().remove(pistasFilas);
    	}
    	if (pistasColumnas != null && pistasColumnas.getParent() != null) {
    	    pistasColumnas.getParent().remove(pistasColumnas);
    	}

        // reconstruir pistas de columna
        pistasColumnas = new JPanel(new GridLayout(1, 5));
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
        panelPrincipal.add(pistasColumnas, BorderLayout.NORTH);

        // eliminar y reconstruir el panel central (pistas de fila + tablero)
        panelPrincipal.remove(centro); // ← asegurate de tener centro como atributo

        pistasFilas = new JPanel(new GridLayout(5, 1));
        for (int fila = 0; fila < 5; fila++) {
            String pistaTexto = controlador.generarPistaFila(fila);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }

        centro = new JPanel(new BorderLayout());
        centro.add(pistasFilas, BorderLayout.WEST);
        centro.add(panel, BorderLayout.CENTER);
        panelPrincipal.add(centro, BorderLayout.CENTER);
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

    public JButton comprobarResultadosButton() {
        JButton comprobarBtn = new JButton("Comprobar");
        comprobarBtn.addActionListener(e -> {
            String mensaje = controlador.comprobarResultados();
            JOptionPane.showMessageDialog(frame, mensaje);

            JPanel opcionesPanel = new JPanel(new FlowLayout());

            if (mensaje.equals("¡Ganaste!")) {
                JButton jugarOtroBtn = new JButton("Jugar otro");
                jugarOtroBtn.addActionListener(ev -> {
                    frame.dispose();
                    controlador.iniciarJuego("facil");
                });
                opcionesPanel.add(jugarOtroBtn);
            } else {
                JButton intentarDeNuevoBtn = new JButton("Intentar de nuevo");
                intentarDeNuevoBtn.addActionListener(ev -> reconstruirTablero());

                JButton cambiarTableroBtn = new JButton("Cambiar tablero");
                cambiarTableroBtn.addActionListener(ev -> {
                    frame.dispose();
                    controlador.iniciarJuego("facil");
                });

                opcionesPanel.add(intentarDeNuevoBtn);
                opcionesPanel.add(cambiarTableroBtn);
            }

            actualizarPanelInferior(opcionesPanel);
        });
        return comprobarBtn;
    }
}
