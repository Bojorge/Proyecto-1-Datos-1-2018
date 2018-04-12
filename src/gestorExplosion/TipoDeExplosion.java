package gestorExplosion;

import java.awt.Graphics2D;

public interface TipoDeExplosion {

	public void dibujarTipoExplosion(Graphics2D g);
	public void actualizarTipoExplosion(double cambio);
	
	public boolean expDestruir();
}
