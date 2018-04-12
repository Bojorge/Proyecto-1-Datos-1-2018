package armas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import inicio.Facade;
import powerUp.Escudo;

public class Ametralladora extends TipoArmaJugador{

	private Rectangle bala;
	private final double velocidadBala = 9.5d;
	
	public Ametralladora(double xPos, double yPos, int ancho,int altura){
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setAncho(ancho);
		this.setAncho(altura);
		
		this.bala = new Rectangle((int) getxPos(),(int) getyPos(), getAncho(), getAncho());
	}
	
	@Override
	public void dibujarTipArmJug(Graphics2D g) {
		if(bala == null)
			return;
		
		g.setColor(Color.orange);
		g.fill(bala);
	}

	@Override
	public void actualizarTipArmJug(double cambio, Escudo escudo) {
		if(bala == null)
			return;
		
		this.setyPos(getyPos() - (cambio * velocidadBala));
		bala.y = (int) this.getyPos();
		choqueContraEscudo(escudo);
		estaFueraDelLimite();
	}

	@Override
	public boolean colisionDeBala(Rectangle objeto) {
		if(this.bala == null)
			return false;
		
		if(bala.intersects(objeto)){
			this.bala = null;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean colisionPolig(Polygon polig) {
		return false;
	}

	@Override
	public boolean destruirTipArmJug() {
		if(bala == null)
			return true;
		
		return false;
	}

	@Override
	protected void choqueContraEscudo(Escudo escudo) {
//		for(int i = 0; i < escudo.barrera.size(); i++){
//			if(bala.intersects(escudo.barrera.get(i))){
//				escudo.barrera.remove(i);
		for(int i = 0; i < escudo.barrera.cantidad(); i++){
			if(bala.intersects((Rectangle) escudo.barrera.getElemento(i))){
				escudo.barrera.borrar(i);
				bala = null;
				return;
			}
		}
	}

	@Override
	protected void estaFueraDelLimite() {
		if(this.bala == null)
			return;
		
		if(bala.y < 0 || bala.y > Facade.HEIGHT || bala.x < 0 || bala.x > Facade.WIDTH){
			bala = null;
		}
	}

}
