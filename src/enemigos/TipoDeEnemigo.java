package enemigos;

import java.awt.Graphics2D;
import java.util.ArrayList;

//import animacion.ListaDE;
import armas.GestorBalaEnemigo;
import jugador.Jugador;
import powerUp.Escudo;

public abstract class TipoDeEnemigo {
	
	private GestorBalaEnemigo gestorBalaEnem;
	
	public TipoDeEnemigo(GestorBalaEnemigo gestorBala) {
		this.gestorBalaEnem = gestorBala;
	}

	public abstract void dibujarTipEn(Graphics2D g);
	public abstract void actualizarTipEn(double cambio, Jugador jugador, Escudo escudo);
	public abstract void cambiarDireccion(double cambio);
	
	public abstract boolean escenaMortal();
	public abstract boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ArrayList<TipoDeEnemigo> enemigos);
//	public abstract boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ListaDE enemigos);
	public abstract boolean salioDelLimite();
	
	public GestorBalaEnemigo getGestorBala() {
		return gestorBalaEnem;
	}
}
