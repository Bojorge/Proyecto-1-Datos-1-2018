package powerUP;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import balasJugador.Ametralladora;

public class Laser extends Ametralladora{
	private double xPos, yPos;
	private int width, height;
	
	private Rectangle rayo;
	
private int velocidad;
	
	public int getVelocidadL() {
		return velocidad;
	}

	public void setVelocidadL(int velocidad) {
		this.velocidad = velocidad;
	}

	public Laser(double xPos, double yPos, int width, int height,int velocidadL) {
		
		super(xPos, yPos, width+5, height-2, velocidadL);
		
	}

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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void actualizarRayo(double Bcambio, EscudoBasico escudos) {
		if(rayo == null)
			return;
		
		this.setARMyPos(getARMyPos() - (Bcambio * velocidad));
		rayo.y = (int)this.getARMyPos();
		escudoColision(escudos);
		estaFueraDelLimite();
		
	}
	
	public void dibujarRayo(Graphics2D g) {
		if(rayo == null)
			return;
		
		g.setColor(Color.RED);
		g.fill(rayo);
	}
	
}
