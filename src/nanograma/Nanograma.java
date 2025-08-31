package nanograma;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.ControladorTablero;

import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import vista.VistaTablero;
public class Nanograma {

	private JFrame frame;
	private JPanel tablero;
	
	
	public Nanograma() {
		initialize();
	}

	public void initialize() {
		VistaTablero vistaTablero = new VistaTablero();
		frame = vistaTablero.generarTablero(); 
		vistaTablero.agregarPistas(frame);

		
		//comparamos el resultado esperado con el enviado por el usuario
		JButton comprobarBtn;
		
			comprobarBtn = vistaTablero.comprobarResultadosButton();
		
		frame.getContentPane().add(comprobarBtn, BorderLayout.SOUTH);
    }
	
	
public JFrame getFrame() {
    	return this.frame;
    }


}



