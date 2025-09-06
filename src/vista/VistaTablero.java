package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.CreacionTablero;
import modelo.Tablero;
public class VistaTablero {
	
	private JFrame frame;
	private Tablero tablero;
	private JPanel panel;
	private JButton[][] celdas = new JButton[5][5];
	

    public VistaTablero() {
		CreacionTablero creador = new CreacionTablero();
		creador.generarTablero("facil");
		int[][] matrizJuego = creador.obtenerMatrizJuego();
		this.tablero = new Tablero(matrizJuego);

		        System.out.println("Matriz creada:");
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                System.out.print(matrizJuego[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public JFrame generarTablero() {
       	frame = new JFrame();
       	frame.getContentPane().setBackground(Color.WHITE);
       	frame.setBounds(100, 100, 450, 300);
       	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	frame.getContentPane().setLayout(new BorderLayout());	
       	panel= new JPanel(new GridLayout(5, 5));

       	frame.getContentPane().add(panel);
       	
       	

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

       	        celdas [fila][col] = celda;
       	        panel.add(celda);
       	    }
       }
       	return frame;
       }

  
    
    public void agregarPistas(JFrame frame) {
        // Pistas de columnas (verticales, arriba del tablero)
        JPanel pistasColumnas = new JPanel(new GridLayout(1, 5)); // 2 filas de pistas por columna
        for (int col = 0; col < tablero.getSolucion()[0].length; col++) {
            String pistaTexto = tablero.generarPistaColumna(tablero.getSolucion(), col);
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
        for (int fila = 0; fila < tablero.getSolucion().length; fila++) {
            String pistaTexto = tablero.generarPistaFila(tablero.getSolucion()[fila]);
            JLabel pista = new JLabel(pistaTexto, SwingConstants.RIGHT);
            pistasFilas.add(pista);
        }
        frame.getContentPane().add(pistasFilas, BorderLayout.WEST);
    }
    
    public JButton comprobarResultadosButton() {
    	JButton comprobarBtn = new JButton("Comprobar");
    	comprobarBtn.addActionListener(e -> {
    	    String mensaje = tablero.comprobarResultados();
    	    JOptionPane.showMessageDialog(frame, mensaje);
    	});
    	return comprobarBtn;
    }
 }

