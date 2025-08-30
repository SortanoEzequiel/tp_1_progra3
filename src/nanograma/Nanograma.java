package nanograma;
//peperooni
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;

public class Nanograma {

	private JFrame frame;
	private JPanel tablero;
	public Nanograma() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));		
		
		tablero = new JPanel(new GridLayout(5, 5));
		frame.getContentPane().add(tablero);
		JButton[][] celdas = new JButton[5][5];
		

		for (int fila = 0; fila < 5; fila++) {
		    for (int col = 0; col < 5; col++) {
		        JButton celda = new JButton();
		        int[] estado = {0};

		      
		        celda.setOpaque(true);
		        celda.setContentAreaFilled(true);
		        celda.setBorderPainted(true);
		        celda.setFocusPainted(false);
		        celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		        celda.setBackground(Color.WHITE);
		        celda.addActionListener(e -> {
		            estado[0] = (estado[0] + 1) % 3;
		            switch (estado[0]) {
		                case 0:
		                    celda.setBackground(Color.WHITE);
		                    celda.setText("");
		                    break;
		                case 1:
		                    celda.setBackground(Color.BLACK);
		                    celda.setText("");
		                    break;
		                case 2:
		                    celda.setBackground(Color.WHITE);
		                    celda.setForeground(Color.RED);
		                    celda.setFont(new Font("Arial", Font.BOLD, 45));
		                    celda.setText("X");
		                    break;
		            }
		        });

		        celdas[fila][col] = celda;
		        tablero.add(celda);
		    }
	}
}

public JFrame getFrame() {
	return this.frame;
}
}
