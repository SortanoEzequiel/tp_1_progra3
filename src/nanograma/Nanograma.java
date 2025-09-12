package nanograma;

import javax.swing.JFrame;
import controller.ControladorInicio;

public class Nanograma {

    private ControladorInicio controlador;

    public Nanograma() {
        initialize();
    }

    public void initialize() {
        controlador = new ControladorInicio();
        controlador.iniciarJuego("medio"); // podrías reemplazar esto por una selección dinámica
    }

    public void mostrar() {
        JFrame frame = controlador.getVistaFrame();
        if (frame != null) {
            frame.setVisible(true);
        }
    }
}
