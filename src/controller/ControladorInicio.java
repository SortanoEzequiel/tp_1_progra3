package controller;

import nanograma.Nanograma;

public class ControladorInicio {

    public void iniciarJuego() {
        Nanograma nanograma = new Nanograma();
        nanograma.getFrame().setVisible(true);
    }
}