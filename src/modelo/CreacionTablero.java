package modelo;

import java.util.Random;

public class CreacionTablero {

	int[][] matrizJuego;
	String dificultad = "";
	
	public CreacionTablero() {
	}
	
	//Metodos principales
	public void generarTablero(String dificultad) {
		tamañoDeTablero(dificultad);
		llenarTablero(matrizJuego);
	}
	
	//Metodo opcional
	public void reiniciarTablero() {
		generarTablero(dificultad);
	}
	
	//---Auxiliares---
	
	private void tamañoDeTablero(String dificultad){
		if(dificultad.equals("facil"))
			//Tamaño 5x5 		
			matrizJuego = new int[5][5];
		
		if(dificultad.equals("medio"))
			//Tamaño 10x10
			matrizJuego = new int[10][10];
		
		if(dificultad.equals("dificil"))
			//Tamaño 15x15 o 20
			matrizJuego = new int[15][15];
	}
	
	private void llenarTablero(int[][] tablero) {
		int tamañoDeTablero = tablero.length;
		for (int fila = 0; fila < tamañoDeTablero; fila++) {
			llenarFila(tablero[fila], tamañoDeTablero);
		}
	}
	
	private void llenarFila(int[] fila, int tamañoDeTablero) {
		int contador = 0;
		while (contador < tamañoDeTablero) {
			if(numeroAleatorio(tamañoDeTablero) % 2 == 0) {
				fila[contador] = 1;
			}
			contador++;
		}
	}
	
	private int numeroAleatorio(int rango) {
		Random aleatorio = new Random();
		int numeroAleatorio = aleatorio.nextInt(rango);
		return numeroAleatorio;
	}

	public int[][] obtenerMatrizJuego() {
		return matrizJuego;
	}
}
