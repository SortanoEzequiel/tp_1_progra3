package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Vista;

public class ControladorInicio implements ActionListener {

    private Vista vista;

    public ControladorInicio(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "FACIL":
                vista.iniciarJuego(5, 5);
                break;
            case "MEDIO":
                vista.iniciarJuego(6, 6);
                break;
            case "DIFICIL":
                vista.iniciarJuego(7, 7);
                break;
            case "SALIR":
                System.exit(0);
                break;
        }
    }
}
