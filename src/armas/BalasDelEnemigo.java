package armas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import inicio.Facade;
import jugador.Jugador;
import powerUp.Escudo;

public class BalasDelEnemigo extends TipoDeArmaEnemigo{

	private Rectangle bala;
	private double balaSpeed = 12.5d;
	private int xPos, yPos;
	
	public BalasDelEnemigo(double xPos, double yPos) {
		bala = new Rectangle((int) xPos, (int) yPos, 1, 3);
		setxPos((int) xPos); 
		setyPos((int) yPos); 
	}
	
	@Override
	public void dibujarArmEn(Graphics2D g) {
		if (bala == null) {
			return;
		}
		
		g.setColor(Color.blue.brighter());
		g.fill(bala);
	}

	@Override
	public void actualizarArmEn(double cambio, Escudo escudo, Jugador jugador) {
		if (bala == null) {
			return;
		}
		
		setyPos((int) (getyPos() + (cambio * balaSpeed))); 
		bala.y = getyPos();
		
		seSalioDelLimite();
		choqueVRSescudo(escudo);
	}

	@Override
	public boolean choque(Rectangle balaVRSJugador) {
		if (bala != null && bala.intersects(balaVRSJugador)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean destruir() {
		return false;
	}

	@Override
	protected void choqueVRSescudo(Escudo escudo) {
		if (bala == null) {
			return;
		}
		
//		for (int w = 0; w < escudo.barrera.size(); w++) {
//			if(bala.intersects(escudo.barrera.get(w))) {
//				escudo.barrera.remove(w);
		for (int w = 0; w < escudo.barrera.cantidad(); w++) {
			if(bala.intersects((Rectangle) escudo.barrera.getElemento(w))) {
				escudo.barrera.borrar(w);
				bala = null; 
				break;
			}
		}
	}

	@Override
	protected void seSalioDelLimite() {
		if(bala != null && bala.y < 0 || bala.y > Facade.HEIGHT || bala.x < 0 || bala.x > Facade.WIDTH){
			bala = null;
		}
	}

	public Rectangle getBala() {
		return bala;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
