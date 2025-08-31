package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	        //titulo de bienvenida
	        JLabel lbl_titulo = new JLabel("Bienvenido a Nonograma");
	        lbl_titulo.setBounds(100, 50, 300, 40);
	        lbl_titulo.setForeground(Color.WHITE);
	        lbl_titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
	        lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
	        frame.getContentPane().add(lbl_titulo);
	        //botton de inicio
	        JButton btn_iniciar = new JButton("Iniciar Juego");
	        btn_iniciar.setBounds(175, 150, 150, 40);
	        btn_iniciar.setBackground(new Color(76, 175, 80));
	        btn_iniciar.setForeground(Color.WHITE);
	        btn_iniciar.setFont(new Font("SansSerif", Font.PLAIN, 16));
	        btn_iniciar.setFocusPainted(false);
	        btn_iniciar.setBorder(BorderFactory.createEmptyBorder());
	        frame.getContentPane().add(btn_iniciar);
	        //inicia el juego desde el controlador
	        btn_iniciar.addActionListener(e -> {
	            controlador.iniciarJuego();
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
