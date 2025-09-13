package vista;

import controller.ControladorInicio;

import javax.swing.*;
import java.awt.*;

public class VistaTablero {
    private final ControladorInicio controlador;
    private JButton[][] celdas;
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
        int tamaño = controlador.getTamañoTablero();

        frame = new JFrame("Nonograma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());

        // Pistas de columna
        pistasColumnas = new JPanel(new GridLayout(1, tamaño));
        for (int col = 0; col < tamaño; col++) {
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

        // Pistas de fila
        pistasFilas = new JPanel(new GridLayout(tamaño, 1));
        for (int fila = 0; fila < tamaño; fila++) {
            String pistaTexto = controlador.generarPistaFila(fila);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }

        // Panel de celdas
        panel = new JPanel(new GridLayout(tamaño, tamaño));
        celdas = new JButton[tamaño][tamaño];
        for (int fila = 0; fila < tamaño; fila++) {
            for (int col = 0; col < tamaño; col++) {
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

        centro = new JPanel(new BorderLayout());
        centro.add(pistasFilas, BorderLayout.WEST);
        centro.add(panel, BorderLayout.CENTER);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        actualizarPanelInferior(wrapComprobarButton());

        frame.add(panelPrincipal);
        return frame;
    }

    public void reconstruirTablero() {
        int tamaño = controlador.getTamañoTablero();
        panel.removeAll();
        controlador.reiniciarTablero();

        celdas = new JButton[tamaño][tamaño];
        panel.setLayout(new GridLayout(tamaño, tamaño));

        for (int fila = 0; fila < tamaño; fila++) {
            for (int col = 0; col < tamaño; col++) {
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
        int tamaño = controlador.getTamañoTablero();

        if (pistasFilas != null && pistasFilas.getParent() != null) {
            pistasFilas.getParent().remove(pistasFilas);
        }
        if (pistasColumnas != null && pistasColumnas.getParent() != null) {
            pistasColumnas.getParent().remove(pistasColumnas);
        }

        pistasColumnas = new JPanel(new GridLayout(1, tamaño));
        for (int col = 0; col < tamaño; col++) {
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

        panelPrincipal.remove(centro);

        pistasFilas = new JPanel(new GridLayout(tamaño, 1));
        for (int fila = 0; fila < tamaño; fila++) {
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
                opcionesPanel.add(botonNuevoTablero());
            } else {
                JButton intentarDeNuevoBtn = new JButton("Intentar de nuevo");
                intentarDeNuevoBtn.addActionListener(ev -> reconstruirTablero());

                opcionesPanel.add(intentarDeNuevoBtn);
                opcionesPanel.add(botonNuevoTablero());
            }

            actualizarPanelInferior(opcionesPanel);
        });
        return comprobarBtn;
    }

    private JButton botonNuevoTablero() {
        JButton cambiarTableroBtn = new JButton("Cambiar tablero");
        cambiarTableroBtn.addActionListener(ev -> {
            String[] opciones = {"Facil", "Medio", "Dificil"};
            String seleccion = (String) JOptionPane.showInputDialog(
                    frame,
                    "Selecciona dificultad:",
                    "Nuevo tablero",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            if (seleccion != null) {
                frame.dispose();
                controlador.iniciarJuego(seleccion.toLowerCase());
            }
        });
        return cambiarTableroBtn;
    }
    public JButton getCelda(int fila, int col) {
        return celdas[fila][col];
    }
    public void setCeldas(JButton[][] nuevasCeldas) {
        this.celdas = nuevasCeldas;
    }


}
