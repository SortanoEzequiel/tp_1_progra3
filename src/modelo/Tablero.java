package modelo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Tablero {
    private JButton[][] celdas = new JButton[5][5];
    private int[][] solucion;
    private int[][] estados = new int[5][5];

    public Tablero(int[][] tableroYaCreado) {
        this.solucion = tableroYaCreado;
    }

    public void setCeldas(JButton[][] celdasExternas) {
        this.celdas = celdasExternas;
    }

    public void reiniciarEstados() {
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                estados[fila][col] = 0;
            }
        }
    }

    public void reiniciarCeldas() {
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                estados[fila][col] = 0;
                JButton celda = celdas[fila][col];
                celda.setBackground(Color.WHITE);
                celda.setText("");
                celda.setForeground(Color.BLACK);
                celda.setFont(new Font("Arial", Font.PLAIN, 12));
            }
        }
    }

    public void actualizarEstado(int fila, int col) {
        estados[fila][col] = (estados[fila][col] + 1) % 3;
        JButton celda = celdas[fila][col];

        switch (estados[fila][col]) {
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
    }

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
        return correcto ? "¡Ganaste!" : "Jugar otro";
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

    public int[][] getSolucion() {
        return solucion;
    }
}
