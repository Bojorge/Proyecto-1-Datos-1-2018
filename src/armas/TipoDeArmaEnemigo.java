package armas;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import jugador.Jugador;
import powerUp.Escudo;

public abstract class TipoDeArmaEnemigo {

	public abstract void dibujarArmEn(Graphics2D g);
	public abstract void actualizarArmEn(double delta, Escudo escudo, Jugador jugador);
	
	public abstract boolean choque(Rectangle rect);
	public abstract boolean destruir();
	
	protected abstract void choqueVRSescudo(Escudo escudo);
	protected abstract void seSalioDelLimite();
	
	public abstract int getxPos();
	public abstract int getyPos();
}
