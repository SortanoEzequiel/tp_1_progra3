package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPantallaInicio extends JFrame {

    private JButton botonFacil;
    private JButton botonMedio;
    private JButton botonDificil;
    private JButton botonSalir;

    public VistaPantallaInicio() {
        setTitle("Nanograma - Pantalla de Inicio");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("¡Bienvenido a Nanograma!", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        botonFacil = crearBoton("Fácil (5x5)", new Color(144, 238, 144));
        botonMedio = crearBoton("Medio (6x6)", new Color(255, 223, 186));
        botonDificil = crearBoton("Difícil (7x7)", new Color(255, 160, 122));
        botonSalir = crearBoton("Salir", new Color(240, 128, 128));

        panelBotones.add(botonFacil);
        panelBotones.add(botonMedio);
        panelBotones.add(botonDificil);
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(colorFondo);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return boton;
    }

    public void setControlador(ActionListener controlador) {
        botonFacil.setActionCommand("FACIL");
        botonMedio.setActionCommand("MEDIO");
        botonDificil.setActionCommand("DIFICIL");
        botonSalir.setActionCommand("SALIR");

        botonFacil.addActionListener(controlador);
        botonMedio.addActionListener(controlador);
        botonDificil.addActionListener(controlador);
        botonSalir.addActionListener(controlador);
    }
}
