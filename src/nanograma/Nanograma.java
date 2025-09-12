package nanograma;

import javax.swing.JFrame;
import controller.ControladorInicio;

public class Nanograma {

    private JFrame frame;
    private ControladorInicio controlador;

    public Nanograma() {
        initialize();
    }

    public void initialize() {
        controlador = new ControladorInicio();
        controlador.iniciarJuego("Facil"); // o pedir dificultad al usuario
        frame = controlador.getVistaFrame(); // nuevo método en el controlador
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void mostrar() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }
}
