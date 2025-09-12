package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int[][] solucion;
    private int[][] estados = new int[5][5];
    private List<CeldaListener> listeners = new ArrayList<>();

    public Tablero(int[][] tableroYaCreado) {
        this.solucion = tableroYaCreado;
    }
    
    public void agregarListener(CeldaListener listener) {
        listeners.add(listener);
    }

    public void actualizarEstado(int fila, int col) {
        estados[fila][col] = (estados[fila][col] + 1) % 3;
        notificarCambio(fila, col, estados[fila][col]);
    }

    private void notificarCambio(int fila, int col, int nuevoEstado) {
        EventoCambioCelda evento = new EventoCambioCelda(fila, col, nuevoEstado);
        for (CeldaListener listener : listeners) {
            listener.celdaActualizada(evento);
        }
    }

    public String comprobarResultados() {
        boolean correcto = true;
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                int valor = (estados[fila][col] == 1) ? 1 : 0;
                if (valor != solucion[fila][col]) {
                    correcto = false;
                    break;
                }
            }
        }
        return correcto ? "¡Ganaste!" : "Jugar otro";
    }

    public String generarPistaFila(int[] fila) {
    	for (int f = 0; f < solucion.length; f++) {
            for (int col = 0; col < solucion[f].length; col++) {
                System.out.print(solucion[f][col] + "\t"); // tabulación para alinear columnas
            }
            System.out.println(); // salto de línea al final de cada fila
        }
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
        StringBuilder pista = new StringBuilder("<html>");
        int contador = 0;
        for (int fila = 0; fila < matriz.length; fila++) {
            if (matriz[fila][col] == 1) {
                contador++;
            } else if (contador > 0) {
                pista.append(contador).append("<br>");
                contador = 0;
            }
        }
        if (contador > 0) pista.append(contador).append("<br>");
        if (pista.toString().equals("<html>")) pista.append("0<br>");
        pista.append("</html>");
        return pista.toString();
    }
    public void reiniciarEstados() {
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                estados[fila][col] = 0;
            }
        }
    }
    



    public int[][] getSolucion() {
        return solucion;
    }
}
