package niveles;

import java.awt.Graphics2D;

import powerUp.Escudo;

public interface NivelPadre {
	
	boolean haPerdidoElJuego();
	boolean haCompletadoElNivel();
	
	void dibujarNivPad(Graphics2D g);
	void actualizarNivPad(double cambio, Escudo escudo);
	void haCambiadoLaDireccion(double cambioDireccion);
	void cambiaDireccionDeTodosLosEnemigos(double cambioDireccion);
	
	void destruirNivPad();
	void resetNivPad();
}
