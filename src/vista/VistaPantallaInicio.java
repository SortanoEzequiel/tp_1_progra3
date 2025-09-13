package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.ControladorInicio;

public class VistaPantallaInicio {

    private JFrame frame;
    private ControladorInicio controlador;

    public VistaPantallaInicio() {
        this.controlador = new ControladorInicio();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 47)); // fondo oscuro

        // Título
        JLabel lbl_titulo = new JLabel("Bienvenido a Nonograma");
        lbl_titulo.setBounds(100, 50, 300, 40);
        lbl_titulo.setForeground(Color.WHITE);
        lbl_titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lbl_titulo);

        // Selector de dificultad
        JLabel lbl_dificultad = new JLabel("Selecciona dificultad:");
        lbl_dificultad.setBounds(175, 110, 150, 25);
        lbl_dificultad.setForeground(Color.WHITE);
        lbl_dificultad.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl_dificultad.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lbl_dificultad);

        JComboBox<String> combo_dificultad = new JComboBox<>();
        combo_dificultad.setBounds(175, 135, 150, 30);
        combo_dificultad.addItem("Facil");
        combo_dificultad.addItem("Medio");
        combo_dificultad.addItem("Dificil");
        frame.getContentPane().add(combo_dificultad);

        // Botón de inicio
        JButton btn_iniciar = new JButton("Iniciar Juego");
        btn_iniciar.setBounds(175, 180, 150, 40);
        btn_iniciar.setBackground(new Color(76, 175, 80));
        btn_iniciar.setForeground(Color.WHITE);
        btn_iniciar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btn_iniciar.setFocusPainted(false);
        btn_iniciar.setBorder(BorderFactory.createEmptyBorder());
        frame.getContentPane().add(btn_iniciar);

        // Acción del botón
        btn_iniciar.addActionListener(e -> {
            String seleccion = combo_dificultad.getSelectedItem().toString().toLowerCase();
            controlador.iniciarJuego(seleccion);
            frame.dispose();
        });
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VistaPantallaInicio vista = new VistaPantallaInicio();
            vista.mostrar();
        });
    }
}
