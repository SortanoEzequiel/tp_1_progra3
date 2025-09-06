package controller;

import modelo.CreacionTablero;
import modelo.Tablero;

public class ControladorTablero {
	private Tablero tablero;
	
	public ControladorTablero() {
		CreacionTablero creador = new CreacionTablero();
		creador.generarTablero("facil");
		int[][] matrizJuego = creador.obtenerMatrizJuego();
		this.tablero = new Tablero(matrizJuego);
	}
	
	public Tablero generarTablero() {
		// TODO Apéndice de método generado automáticamente
		return tablero;
	}
}
