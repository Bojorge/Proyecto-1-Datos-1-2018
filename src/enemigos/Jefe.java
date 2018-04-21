package enemigos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import animacion.Animacion;
import armas.BalasDelEnemigo;
import armas.GestorBalaEnemigo;
import inicio.Facade;
import jugador.Jugador;
import niveles.PantallaJuego;
import powerUp.Escudo;
import sonido.Sonido;
import temporizador.Temporizador;

public class Jefe extends TipoDeEnemigo {
	
private double velocidadJefe = 1.0d; 
	
	private Rectangle espacioDelJefe;
	private Animacion animacionJefe;
	
	private int intervaloDeTiros;
	private Temporizador reguladorTiempoDeTiros;
	private Sonido sonidoDeExplosion;
	private Sonido beep;
	private String imgJefe = "/imagenes/jefe.png";
	public Jefe(double xPos, double yPos, int filas, int columnas, GestorBalaEnemigo gestorBalaEnemigo) {
		super(gestorBalaEnemigo);
		animacionJefe = new Animacion(xPos, yPos, filas, columnas, 300, imgJefe);
		animacionJefe.setAnchoAnimacion(35);
		animacionJefe.setAlturaAnimacion(35);
		animacionJefe.setLimiteAnimacion(1);
		
		this.setRect(new Rectangle((int) animacionJefe.getxPos(), (int) animacionJefe.getyPos(), animacionJefe.getAnchoAnimacion(), animacionJefe.getAlturaAnimacion()));
		animacionJefe.setBucleAnimacion(true);
		
		reguladorTiempoDeTiros = new Temporizador();
		intervaloDeTiros = new Random().nextInt(100);
		
		sonidoDeExplosion = new Sonido("/sonidos/explosion.wav");
		beep = new Sonido("/sonidos/beep.wav");
	}
	
	@Override
	public void dibujarTipEn(Graphics2D g) {
		animacionJefe.dibujarAnimacion(g);
	}

	@Override
	public void actualizarTipEn(double cambio, Jugador jugador, Escudo escudo) {
		animacionJefe.actualizarAnimacion(cambio);
		
		animacionJefe.setxPos(animacionJefe.getxPos() - (cambio * velocidadJefe));
		this.getEspacioDelJefe().x = (int) animacionJefe.getxPos();
		
		if (reguladorTiempoDeTiros.tiempoEvento(intervaloDeTiros)) {
			getGestorBala().addBalaEnem(new BalasDelEnemigo(getEspacioDelJefe().x, getEspacioDelJefe().y));
			intervaloDeTiros = new Random().nextInt(100);
		}
	}

	@Override
	public void cambiarDireccion(double cambio) {
		velocidadJefe *= -1.15d;
		animacionJefe.setxPos(animacionJefe.getxPos() - (cambio * velocidadJefe));
		this.getEspacioDelJefe().x = (int) animacionJefe.getxPos();
	
		animacionJefe.setyPos(animacionJefe.getyPos() + (cambio * 15));
		this.getEspacioDelJefe().y = (int) animacionJefe.getyPos();
	}

	@Override
	public boolean escenaMortal() {
		if(!animacionJefe.isPlayAnimacion())
			return false;
		
		if(animacionJefe.esDestruidaLaAnimacion()) {
			if (!sonidoDeExplosion.estaSonando()) {
				sonidoDeExplosion.playSonido();
				for(int i=0;i<2;i++)
					beep.playSonido();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ArrayList<TipoDeEnemigo> jefe) {
//	public boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ListaDE enemigos) {
		if(animacionJefe.isPlayAnimacion()) {
			if(jefe.get(i).escenaMortal()) {
				jefe.remove(i);
//			if(((TipoDeEnemigo) enemigos.getElemento(i)).escenaMortal()) {
//				enemigos.borrar(i);
			}
			return false;
		}
		
		for(int w = 0; w < jugador.armaJugador.armax.size(); w++) {
			if(jefe != null && jugador.armaJugador.armax.get(w).colisionDeBala(((Jefe) jefe.get(i)).getEspacioDelJefe())) {
//		for(int w = 0; w < jugador.armaJugador.armax.cantidad(); w++) {	
//			if(enemigos != null && (jugador.armaJugador.armax.getElemento(w)).colisionDeBala(((EnemigoBasico) enemigos.getElemento(i)).getEspacioDeLosEnemigos())) {
				animacionJefe.resetLimiteAnimacion();
				animacionJefe.setVelocidadAnimacion(120);
				animacionJefe.setPlayAnimacion(true, true);
				PantallaJuego.Puntos += 20;
				return true;
			}
		}
		
		return false; 
	}

	@Override
	public boolean salioDelLimite() {
		if(espacioDelJefe.x > 5 && espacioDelJefe.x < (Facade.WIDTH/2)+75)
			return false;
		return true;
	}

	public Rectangle getEspacioDelJefe() {
		return espacioDelJefe;
	}

	public void setRect(Rectangle espacioDeLosEnemigos) {
		this.espacioDelJefe = espacioDeLosEnemigos;
	}


	
}