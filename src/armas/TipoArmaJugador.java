package armas;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import powerUp.Escudo;

public abstract class TipoArmaJugador {

	protected double xPos, yPos;
	protected int ancho, altura;
	
	public abstract void dibujarTipArmJug(Graphics2D g);
	public abstract void actualizarTipArmJug(double cambio, Escudo escudo);
	public abstract boolean colisionDeBala(Rectangle rect);
	public abstract boolean destruirTipArmJug();
	
	protected abstract void choqueContraEscudo(Escudo escudo);
	protected abstract void estaFueraDelLimite();
	
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int width) {
		this.ancho = width;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	
}
