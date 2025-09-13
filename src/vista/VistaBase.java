package vista;

import javax.swing.*;

public abstract class VistaBase extends JFrame {
    public VistaBase(String titulo, int ancho, int alto) {
        super(titulo);
        setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }

    public void ocultar() {
        setVisible(false);
    }
}

