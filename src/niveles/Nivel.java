package niveles;

import java.awt.Graphics2D;
import java.util.ArrayList;

//import animacion.ListaDE;
import armas.GestorBalaEnemigo;
import enemigos.EnemigoBasico;
import enemigos.TipoDeEnemigo;
import jugador.Jugador;
import powerUp.Escudo;
import sonido.Sonido;

public class Nivel implements NivelPadre{

	private Jugador jugador;
	private ArrayList<TipoDeEnemigo> enemigos = new ArrayList<TipoDeEnemigo>();
//	private ListaDE enemigos = new ListaDE();
	private GestorBalaEnemigo gestorBalaEnemigo;
	
	private Sonido beep, boop;
	private boolean beepboop;
	
	public Nivel(Jugador jugador, GestorBalaEnemigo gestorBalaEnemigo){
		this.jugador = jugador;
		this.gestorBalaEnemigo = gestorBalaEnemigo;
		addEnemigos();
		
		beep = new Sonido("/sonidos/beep.wav");
		boop = new Sonido("/sonidos/boop.wav");
	}
	
	@Override
	public void dibujarNivPad(Graphics2D g) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++){
//		for(int i = 0; i < enemigos.cantidad(); i++){
			enemigos.get(i).dibujarTipEn(g);
//			((TipoDeEnemigo) enemigos.getElemento(i)).dibujarTipEn(g);
		}
		gestorBalaEnemigo.dibujarBalaEnem(g);
	}

	@Override
	public void actualizarNivPad(double cambio, Escudo escudo) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).actualizarTipEn(cambio, jugador, escudo);
//		for(int i = 0; i < enemigos.cantidad(); i++){
//			((TipoDeEnemigo) enemigos.getElemento(i)).actualizarTipEn(cambio, jugador, escudo);
		}
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).colisionBalaVRSenem(i, jugador, escudo, enemigos);
//		for(int i = 0; i < enemigos.cantidad(); i++){
//			((TipoDeEnemigo) enemigos.getElemento(i)).colisionBalaVRSenem(i, jugador, escudo, enemigos);
		}
		haCambiadoLaDireccion(cambio);
		gestorBalaEnemigo.actualizarBalaEnem(cambio, escudo, jugador);
	}

	@Override
	public void haCambiadoLaDireccion(double cambio) {
		if(enemigos == null)
			return;
		
		for(int i = 0; i < enemigos.size(); i++){
			if(enemigos.get(i).salioDelLimite()){
//		for(int i = 0; i < enemigos.cantidad(); i++){
//			if(((TipoDeEnemigo) enemigos.getElemento(i)).salioDelLimite()){
				cambiaDireccionDeTodosLosEnemigos(cambio);
			}
		}
	}

	@Override
	public void cambiaDireccionDeTodosLosEnemigos(double cambio) {
		for(int i = 0; i < enemigos.size(); i++){
			enemigos.get(i).cambiarDireccion(cambio);
//		for(int i = 0; i < enemigos.cantidad(); i++){
//			((TipoDeEnemigo) enemigos.getElemento(i)).cambiarDireccion(cambio);
		}
		if (beepboop) {
			beepboop = false;
			boop.playSonido();
		} else {
			beepboop = true;
			beep.playSonido();
		}
	}

	@Override
	public boolean haPerdidoElJuego() {
		return jugador.getVidas() <= 0;
	}

	@Override
	public void destruirNivPad() {
		
	}

	@Override
	public void resetNivPad() {
		jugador.resetJugador();
		enemigos.clear();
//		enemigos.borrarTodo();
		addEnemigos();
		
		gestorBalaEnemigo.resetGBE();
	}
	
	public void addEnemigos() {
			for(int x = 0; x < 11; x++){
				TipoDeEnemigo e = new EnemigoBasico(150 + (x * 60), 27, 1 , 2, gestorBalaEnemigo);
				enemigos.add(e);
//				enemigos.insertar(1,e);
			}
	}

	@Override
	public boolean haCompletadoElNivel() {
		return enemigos.isEmpty();
//		return enemigos.vaciaEsta();
	}
}
