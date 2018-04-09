package tiposDeEnemigo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import Inicio.Main;
import mapaBitsLCD.Animacion;
import mapaBitsLCD.ListaDE;
import pantallaJuego.Jugador;
import powerUP.EscudoBasico;


public class TipoEnemigoBasico implements TipoDeEnemigo{
	private double velocidad = 0.4d;
	
	private Rectangle rect;
	private Animacion animacionEnemigo;
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public TipoEnemigoBasico(double TEBxPos,double TEByPos,int TEBfilas,int TEBcolumnas) { //,String ImgRuta) {
		animacionEnemigo = new Animacion(TEBxPos,TEByPos,TEBfilas,TEBcolumnas,150,"/imagenes/ufo.png");
		animacionEnemigo.setASwidth(25);
		animacionEnemigo.setASheight(25);
		animacionEnemigo.setLimiteBalEn(2);
		
		this.setRect(new Rectangle((int)animacionEnemigo.getASxPos(),(int)animacionEnemigo.getASyPos(),animacionEnemigo.getASwidth(),animacionEnemigo.getASheight()));
		animacionEnemigo.setBucleAS(true);
	}
	
	@Override
	public void dibujarTipoEnem(Graphics2D g) {
		animacionEnemigo.dibujarAS(g);
	}

	@Override
	public void actualizarTipoEnem(double TEcambio, Jugador jugador, EscudoBasico escudo) {
		animacionEnemigo.actualizarAS(TEcambio);
		
//		movimiento del enemigo
		animacionEnemigo.setASxPos(animacionEnemigo.getASxPos() - (TEcambio * velocidad));
		this.getRect().x = (int)animacionEnemigo.getASxPos();
	}

//	movimiento de los enemigos
	@Override
	public void cambiarDireccion(double TEcambio) {
		velocidad *= -1.2d;
		
//		indica cuando moverse en el eje x
		animacionEnemigo.setASxPos(animacionEnemigo.getASxPos() - (TEcambio * velocidad));
		this.getRect().x = (int)animacionEnemigo.getASxPos();
		
//		indica cuando moverse en el eje y despues de chocar con el limite
		animacionEnemigo.setASyPos(animacionEnemigo.getASyPos() + (TEcambio * 7));
		this.getRect().y = (int)animacionEnemigo.getASyPos();
	}

	@Override
	public boolean escenaMortal() {
		
		if(!animacionEnemigo.isJugar())
			return false;
		

		
		if(animacionEnemigo.seDestruyeAnimacion()) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean choque(int i, Jugador jugador, EscudoBasico escudo, ListaDE enemigo) {
		
		if(animacionEnemigo.isJugar()) {
			if(((TipoDeEnemigo) enemigo.extraer(i)).escenaMortal()) {
				enemigo.borrar(i);
			}
			return false;
		}
		
//		Hace que los disparos lleguen hasta que choquen con el primer enemigo
//		choqueRectangulo() esta en la clase Ametralladora
		
		for(int s = 0;s < jugador.armaJugador.armas.size();s++) {
			if(enemigo != null && jugador.armaJugador.armas.get(s).choqueRectangulo(((TipoEnemigoBasico)enemigo.getElemento(i)).getRect())) {
				animacionEnemigo.resetLimiteBalEn();
				animacionEnemigo.AnimacionJugador(true, true); 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean estaFueraDelLimite() {
		if(rect.x > 0 && rect.x < Main.WIDTH - rect.width-20)
			return false;
		return true;
	}

}
