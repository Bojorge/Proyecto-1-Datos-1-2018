package enemigos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import animacion.Animacion;
//import animacion.ListaDE;
import armas.BalasDelEnemigo;
import armas.GestorBalaEnemigo;
//import armas.TipoArmaJugador;
import inicio.Facade;
import jugador.Jugador;
import niveles.PantallaJuego;
import powerUp.Escudo;
import sonido.Sonido;
import temporizador.Temporizador;

public class EnemigoBasico extends TipoDeEnemigo{

	private double velocidadEnemigo = 1.0d; 
	
	private Rectangle espacioDeLosEnemigos;
	private Animacion animacionEnemigo;
	
	private int intervaloDeTiros;
	private Temporizador reguladorTiempoDeTiros;
	
	private Sonido sonidoDeExplosion;
	
	public EnemigoBasico(double xPos, double yPos, int filas, int columnas, GestorBalaEnemigo gestorBalaEnemigo){
		super(gestorBalaEnemigo);
		
		animacionEnemigo = new Animacion(xPos, yPos, filas, columnas, 300, "/imagenes/Invaders.png");
		animacionEnemigo.setAnchoAnimacion(35);
		animacionEnemigo.setAlturaAnimacion(35);
		animacionEnemigo.setLimiteAnimacion(2);
		
		this.setRect(new Rectangle((int) animacionEnemigo.getxPos(), (int) animacionEnemigo.getyPos(), animacionEnemigo.getAnchoAnimacion(), animacionEnemigo.getAlturaAnimacion()));
		animacionEnemigo.setBucleAnimacion(true);
		
		reguladorTiempoDeTiros = new Temporizador();
		intervaloDeTiros = new Random().nextInt(11000);
		
		sonidoDeExplosion = new Sonido("/sonidos/explosion.wav");
	}
	
	@Override
	public void dibujarTipEn(Graphics2D g) {
		animacionEnemigo.dibujarAnimacion(g);
	}

	@Override
	public void actualizarTipEn(double cambio, Jugador jugador, Escudo escudo) {
		animacionEnemigo.actualizarAnimacion(cambio);
		
		animacionEnemigo.setxPos(animacionEnemigo.getxPos() - (cambio * velocidadEnemigo));
		this.getEspacioDeLosEnemigos().x = (int) animacionEnemigo.getxPos();
		
		if (reguladorTiempoDeTiros.tiempoEvento(intervaloDeTiros)) {
			getGestorBala().addBalaEnem(new BalasDelEnemigo(getEspacioDeLosEnemigos().x, getEspacioDeLosEnemigos().y));
			intervaloDeTiros = new Random().nextInt(12000);
		}
	}

	@Override
	public void cambiarDireccion(double cambio) {
		velocidadEnemigo *= -1.15d;
		animacionEnemigo.setxPos(animacionEnemigo.getxPos() - (cambio * velocidadEnemigo));
		this.getEspacioDeLosEnemigos().x = (int) animacionEnemigo.getxPos();
	
		animacionEnemigo.setyPos(animacionEnemigo.getyPos() + (cambio * 15));
		this.getEspacioDeLosEnemigos().y = (int) animacionEnemigo.getyPos();
	}

	@Override
	public boolean escenaMortal() {
		if(!animacionEnemigo.isPlayAnimacion())
			return false;
		
		if(animacionEnemigo.esDestruidaLaAnimacion()) {
			if (!sonidoDeExplosion.estaSonando()) {
				sonidoDeExplosion.playSonido();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ArrayList<TipoDeEnemigo> enemigos) {
//	public boolean colisionBalaVRSenem(int i, Jugador jugador, Escudo escudo, ListaDE enemigos) {
		if(animacionEnemigo.isPlayAnimacion()) {
			if(enemigos.get(i).escenaMortal()) {
				enemigos.remove(i);
//			if(((TipoDeEnemigo) enemigos.getElemento(i)).escenaMortal()) {
//				enemigos.borrar(i);
			}
			return false;
		}
		
		for(int w = 0; w < jugador.armaJugador.armax.size(); w++) {
			if(enemigos != null && jugador.armaJugador.armax.get(w).colisionDeBala(((EnemigoBasico) enemigos.get(i)).getEspacioDeLosEnemigos())) {
//		for(int w = 0; w < jugador.armaJugador.armax.cantidad(); w++) {	
//			if(enemigos != null && (jugador.armaJugador.armax.getElemento(w)).colisionDeBala(((EnemigoBasico) enemigos.getElemento(i)).getEspacioDeLosEnemigos())) {
				animacionEnemigo.resetLimiteAnimacion();
				animacionEnemigo.setVelocidadAnimacion(120);
				animacionEnemigo.setPlayAnimacion(true, true);
				PantallaJuego.Puntos += 5;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean salioDelLimite() {
		if(espacioDeLosEnemigos.x > 5 && espacioDeLosEnemigos.x < (Facade.WIDTH/2)+75)
			return false;
		return true;
	}

	public Rectangle getEspacioDeLosEnemigos() {
		return espacioDeLosEnemigos;
	}

	public void setRect(Rectangle espacioDeLosEnemigos) {
		this.espacioDeLosEnemigos = espacioDeLosEnemigos;
	}

}
