package balasJugador;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import powerUP.EscudoBasico;

public abstract class TipoArmaJugador {

	protected double ARMxPos, ARMyPos;
	protected int ARMwidth, ARMheight;
	
	public abstract void dibujarBala(Graphics2D g);
	public abstract void actualizarBala(double Bcambio, EscudoBasico escudos);
	public abstract boolean choqueRectangulo(Rectangle rect);
	public abstract boolean choquePoligono(Polygon poli);
	public abstract boolean destruir();
	
	protected abstract void escudoColision(EscudoBasico escudos);
	protected abstract void estaFueraDelLimite();
	
	
	public double getARMxPos() {
		return ARMxPos;
	}
	public void setARMxPos(double aRMxPos) {
		ARMxPos = aRMxPos;
	}
	public double getARMyPos() {
		return ARMyPos;
	}
	public void setARMyPos(double aRMyPos) {
		ARMyPos = aRMyPos;
	}
	public int getARMwidth() {
		return ARMwidth;
	}
	public void setARMwidth(int aRMwidth) {
		ARMwidth = aRMwidth;
	}
	public int getARMheight() {
		return ARMheight;
	}
	public void setARMheight(int aRMheight) {
		ARMheight = aRMheight;
	}
	
	
}
