package nanograma;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
	private int[][] solucion = {
		    {1, 1, 1, 1, 0},
		    {0, 1, 0, 0, 0},
		    {1, 0, 1, 1, 0},
		    {0, 1, 0, 1, 0},
		    {1, 0, 0, 0, 1}
		};
	private JButton[][] celdas = new JButton[5][5];
	public Nanograma() {
		initialize();
	}

	private void initialize() {
		frame = generarTablero(); 
		agregarPistas(frame);

		
		//comparamos el resultado esperado con el enviado por el usuario
		JButton comprobarBtn = comprobarResultadosButton();
		frame.getContentPane().add(comprobarBtn, BorderLayout.SOUTH);
}

private JFrame generarTablero() {
	frame = new JFrame();
	frame.getContentPane().setBackground(Color.WHITE);
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new BorderLayout());	
	GridBagConstraints gbc = new GridBagConstraints();
	tablero = new JPanel(new GridLayout(5, 5));

	frame.getContentPane().add(tablero);
	
	

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
	return frame;
}

private JButton comprobarResultadosButton() {
	JButton comprobarBtn = new JButton("Comprobar");
	comprobarBtn.addActionListener(e -> {
	    boolean correcto = true;
	    for (int fila = 0; fila < 5; fila++) {
	        for (int col = 0; col < 5; col++) {
	            Color color = celdas[fila][col].getBackground();
	            int valor = (color.equals(Color.BLACK)) ? 1 : 0;
	            if (valor != solucion[fila][col]) {
	                correcto = false;
	                break;
	            }
	        }
	    }
	    String mensaje = correcto ? "¡Correcto! 🎉" : "Ups, hay errores 😅";
	    JOptionPane.showMessageDialog(frame, mensaje);
	});
	return comprobarBtn;
}

private String generarPistaFila(int[] fila) {
    StringBuilder pista = new StringBuilder();
    int contador = 0;
    for (int val : fila) {
        if (val == 1) {
            contador++;
        } else if (contador > 0) {
            pista.append(contador).append(" ");
            contador = 0;
        }
    }
    if (contador > 0) pista.append(contador);
    return pista.length() == 0 ? "0" : pista.toString().trim();
}

private String generarPistaColumna(int[][] matriz, int col) {
    StringBuilder pista = new StringBuilder();
    int contador = 0;
    for (int fila = 0; fila < matriz.length; fila++) {
        if (matriz[fila][col] == 1) {
            contador++;
        } else if (contador > 0) {
            pista.append(contador).append(" ");
            contador = 0;
        }
    }
    if (contador > 0) pista.append(contador);
    return pista.length() == 0 ? "0" : pista.toString().trim();
}

private void agregarPistas(JFrame frame) {
    // Pistas de columnas (verticales, arriba del tablero)
    JPanel pistasColumnas = new JPanel(new GridLayout(1, 5)); // 2 filas de pistas por columna
    for (int col = 0; col < solucion[0].length; col++) {
        String pistaTexto = generarPistaColumna(solucion, col);
        String[] numeros = pistaTexto.split(" ");
        
        // Creamos un panel vertical para cada columna
        JPanel pistaColPanel = new JPanel(new GridLayout(numeros.length, 1));
        for (String num : numeros) {
            JLabel etiqueta = new JLabel(num, SwingConstants.CENTER);
            pistaColPanel.add(etiqueta);
        }
        pistasColumnas.add(pistaColPanel);
    }
    frame.getContentPane().add(pistasColumnas, BorderLayout.NORTH);

    // Pistas de filas (izquierda)
    JPanel pistasFilas = new JPanel(new GridLayout(5, 1));
    for (int fila = 0; fila < solucion.length; fila++) {
        String pistaTexto = generarPistaFila(solucion[fila]);
        JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
        pistasFilas.add(pista);
    }
    frame.getContentPane().add(pistasFilas, BorderLayout.WEST);
}

public JFrame getFrame() {
	return this.frame;
}
}
