package balasJugador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import Inicio.Main;
import powerUP.EscudoBasico;

public class Ametralladora extends TipoArmaJugador{

	private Rectangle bala;
	
	private int velocidad;
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Ametralladora(double AMTxPos,double AMTyPos,int AMTwidth,int AMTheight,int velocidad) {
		this.setARMxPos(AMTxPos);
		this.setARMyPos(AMTyPos);
		this.setARMwidth(AMTwidth);
		this.setARMheight(AMTheight);
		setVelocidad(velocidad);
		
		this.bala = new Rectangle((int)getARMxPos(),(int)getARMyPos(),getARMwidth(),getARMheight());
	}
	
	@Override
	public void dibujarBala(Graphics2D g) {
		if(bala == null)
			return;
		
		g.setColor(Color.GREEN);
		g.fill(bala);
		
	}

	@Override
	public void actualizarBala(double Bcambio, EscudoBasico escudos) {
		if(bala == null)
			return;
		
		this.setARMyPos(getARMyPos() - (Bcambio * getVelocidad()));
		bala.y = (int)this.getARMyPos();
		escudoColision(escudos);
		estaFueraDelLimite();
		
	}

	@Override
	public boolean choqueRectangulo(Rectangle rect) {
		if(this.bala == null)
			return false;
		
		if(bala.intersects(rect)) {
			this.bala = null;
			return true;
		}
		return false;
	}

	@Override
	public boolean choquePoligono(Polygon poli) {
		
		return false;
	}

	@Override
	public boolean destruir() {
		if(bala == null)
			return true;
		
		return false;
	}

	@Override
	protected void escudoColision(EscudoBasico escudos) {
		for(int i = 0;i < escudos.barrera.size();i++) {
			if(bala.intersects(escudos.barrera.get(i))) {
				escudos.barrera.remove(i);
				bala = null;
				return;
			}
		}
		
	}

	@Override
	protected void estaFueraDelLimite() {
		if(bala == null)
			return;
		
		if(bala.y < 0 || bala.y > Main.HEIGHT || bala.x < 0 || bala.x > Main.WIDTH) {
			bala = null;
		}
		
	}

}
