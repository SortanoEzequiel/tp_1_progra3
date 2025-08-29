package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;

import nanograma.Nanograma;

public class Main {
		public static void main(String[] args) {
			UIManager.put("Button.select", Color.WHITE);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Nanograma window = new Nanograma();
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}

}
