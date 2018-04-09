package tiposDeEnemigo;

import java.awt.Graphics2D;

import mapaBitsLCD.ListaDE;
import pantallaJuego.Jugador;
import powerUP.EscudoBasico;

public interface TipoDeEnemigo {
	
	void dibujarTipoEnem(Graphics2D g);
	void actualizarTipoEnem(double TEcambio,Jugador jugador,EscudoBasico escudo);
	void cambiarDireccion(double TEcambio);
	
	boolean escenaMortal();
	boolean choque(int i,Jugador jugador,EscudoBasico escudo,ListaDE enEmigos);
	boolean estaFueraDelLimite();
}
