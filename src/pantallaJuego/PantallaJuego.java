package pantallaJuego;

import java.awt.Canvas;
import java.awt.Graphics2D;

import Inicio.Main;
import estado.SuperEstadoMaquina;
import niveles.Nivel;
import powerUP.EscudoBasico;


public class PantallaJuego implements SuperEstadoMaquina{
	
	private Jugador player;
	private EscudoBasico escudo;
	private boolean powerUp;
	
	private int dificultad;
	
	private Nivel nivel;
	
	
	
	public PantallaJuego() {
//		se activan los escudos y el rayo laser dependiendo del valor de verdad
		setPowerUp(true);
		
//		Se aumenta la cantidad de enemigos y varia el tipo de enemigo
		setDificultad(2);
		
		
		
		escudo = new EscudoBasico(isPowerUpPJ());
		
		player = new Jugador(((Main.WIDTH)/2),630,54,46,escudo,isPowerUpPJ()); //Main.HEIGHT
		
		nivel = new Nivel(player,getDificultad());
		
	}
	
	public void nivel() {
//		metodo para avanzar niveles
		}
	
	@Override
	public void actualizarSEM(double cambioSEM) {
		player.actualizarJugador(cambioSEM);
		nivel.actualizarSupNiv(cambioSEM, escudo);
		
	}

	@Override
	public void dibujarSEM(Graphics2D g) {		
		escudo.dibujarEscudo(g);
		
		player.dibujarJugador(g);
		
		nivel.dibujarSupNiv(g);
	}

	@Override
	public void initSEM(Canvas canvas) {
		canvas.addKeyListener(player);
		
	}

	public boolean isPowerUpPJ() {
		return powerUp;
	}

	public void setPowerUp(boolean powerUp) {
		this.powerUp = powerUp;
		
		
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	
	

}
