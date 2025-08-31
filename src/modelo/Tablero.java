package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tablero {
   private JFrame frame;
   private JButton[][] celdas = new JButton[5][5];
   private int[][] solucion = {
		    {1, 1, 1, 1, 0},
		    {0, 1, 0, 0, 0},
		    {1, 0, 1, 1, 0},
		    {0, 1, 0, 1, 0},
		    {1, 0, 0, 0, 1}
		};

public Tablero() {
	   
   }
   
  

//   public JButton comprobarResultadosButton() {
//   	JButton comprobarBtn = new JButton("Comprobar");
//   	comprobarBtn.addActionListener(e -> {
//   	    boolean correcto = true;
//   	    for (int fila = 0; fila < 5; fila++) {
//   	        for (int col = 0; col < 5; col++) {
//   	            Color color = celdas[fila][col].getBackground();
//   	            int valor = (color.equals(Color.BLACK)) ? 1 : 0;
//   	            if (valor != solucion[fila][col]) {
//   	                correcto = false;
//   	                break;
//   	            }
//   	        }
//   	    }
//   	    String mensaje = correcto ? "¡Correcto! 🎉" : "Ups, hay errores 😅";
//   	    JOptionPane.showMessageDialog(frame, mensaje);
//   	});
//   	return comprobarBtn;
//   }
   
   public String comprobarResultados() {
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
	    return correcto ? "¡Correcto! 🎉" : "Ups, hay errores 😅";
	}

   public String generarPistaFila(int[] fila) {
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

   public String generarPistaColumna(int[][] matriz, int col) {
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

  public int[][] getSolucion(){
	  return solucion;
  }

}
