package vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Tablero;
public class VistaTablero {
	private Tablero tablero;

    public VistaTablero() {
    	this.tablero = new Tablero();
    }
    public JFrame generarTablero () {
    	
    	return this.tablero.generarTablero();
    }
    
    public void agregarPistas(JFrame frame) {
    	tablero.agregarPistas(frame);
    }
    public JButton comprobarResultadosButton() {
    	return tablero.comprobarResultadosButton();
    }
 }

