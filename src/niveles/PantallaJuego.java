package niveles;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import armas.GestorBalaEnemigo;
import estados.MaquinaDeEstados;
import estados.MaquinaDeEstadosPadre;
import inicio.Facade;
import jugador.Jugador;
import powerUp.Escudo;
import sonido.Sonido;
import temporizador.Tempo;

public class PantallaJuego extends MaquinaDeEstadosPadre {
	private Sonido sonidoPerdedor;
	private Jugador jugador;
	private Escudo escudo;
	private Nivel nivel;
	private GestorBalaEnemigo gestorBalas;
	
	public static int Puntos = 0;
	
	private Tempo PerdioTimer = new Tempo(180);
	private Tempo GanoTimer = new Tempo(180);
	private Font fuente1 = new Font("AR DESTINE", Font.PLAIN, 48);
	private Font fuente2 = new Font("AR DELANEY", Font.PLAIN, 35);
	

	public PantallaJuego(MaquinaDeEstados MaquinaDeEstados){
		super(MaquinaDeEstados);
		escudo = new Escudo();
		gestorBalas = new GestorBalaEnemigo();
		jugador = new Jugador((Facade.WIDTH/3)-70, Facade.HEIGHT-75, 50, 50, escudo);
		nivel = new Nivel(jugador, gestorBalas);
	}
	
	@Override
	public void actualizarMaqEstPad(double cambio) {
		jugador.actualizarJugador(cambio);
		nivel.actualizarNivPad(cambio, escudo);
		
		if (nivel.haPerdidoElJuego()) {
			PerdioTimer.tick(cambio);
			if (PerdioTimer.EventoListo()) {
				nivel.resetNivPad();
				escudo.reset();
				getMaquinaDeEstados().setEstado((byte) 0);
				Puntos = 0;
			}
		}
		
		if (nivel.haCompletadoElNivel()) {
			GanoTimer.tick(cambio);
			if (GanoTimer.EventoListo()) {
				nivel.resetNivPad();
			}
		}
	}
	
	@Override
	public void dibujarMaqEstPad(Graphics2D g) {
		g.setColor(Color.white);
		g.setFont(fuente2);
		g.drawString("PUNTOS >>> " + Puntos,800, 100);
		
		g.setColor(Color.red);
		g.drawString("VIDAS >>> " + jugador.getVidas(),800, 150);
		
		g.setColor(Color.blue);			
		g.drawString("NIVEL >>> ",800, 200);
		
		g.setColor(Color.green);			
		g.drawString("Siguiente hilera >>> ",800, 250);
				
		escudo.dibujarEscudo(g);
		jugador.dibujarJugador(g);
		nivel.dibujarNivPad(g);
		
		if (nivel.haPerdidoElJuego()) {
			g.setColor(Color.red.darker());
			g.setFont(fuente1);
			String gameOver = "¡HA PERDIDO!";
			sonidoPerdedor = new Sonido("/sonidos/risa.wav");
			sonidoPerdedor.playSonido();
			g.drawString(gameOver,900,400);
		}
		
		if (nivel.haCompletadoElNivel()) {
			g.setColor(Color.orange);
			g.setFont(fuente1);
			String complete = "PROXIMO NIVEL ++";
			g.drawString(complete,870,400);
		}
	}

	@Override
	public void iniciarMaqEstPad(Canvas canvas) {
		canvas.addKeyListener(jugador);
	}

}
