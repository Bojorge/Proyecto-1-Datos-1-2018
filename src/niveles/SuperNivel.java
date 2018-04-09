package niveles;

import java.awt.Graphics2D;

import powerUP.EscudoBasico;

public interface SuperNivel {
	
	void dibujarSupNiv(Graphics2D g);
	void actualizarSupNiv(double SNcambio,EscudoBasico escudo);
	void haCambiadoLaDireccion(double SNcambio);
	void cambiarDireccionDeEnemigos(double SNcambio);
	
	boolean seTerminoJuego();
	
	void destroy();
	void reset();
}
