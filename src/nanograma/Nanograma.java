package nanograma;

import javax.swing.JFrame;
import controller.ControladorInicio;

public class Nanograma {

    private ControladorInicio controlador;

    public Nanograma(String dificultad) {
        initialize(dificultad);
    }

    public void initialize(String dificultad) {
        controlador = new ControladorInicio();
        controlador.iniciarJuego(dificultad);
    }

    public void mostrar() {
        JFrame frame = controlador.getVistaFrame();
        if (frame != null) {
            frame.setVisible(true);
        }
    }
}
