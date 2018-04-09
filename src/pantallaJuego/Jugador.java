package pantallaJuego;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Inicio.Main;
import balasJugador.ArmaJugador;
import powerUP.EscudoBasico;



public class Jugador implements KeyListener{
	
	private final double velocidad = 5.0d;
	
	private BufferedImage jAnimacion;
	private Rectangle rectangulo;
	private double JxPos, JyPos;
	private int Jwidth, Jheight;
	
	private EscudoBasico escudo;
	
	
	private boolean izquierda = false, derecha = false, disparo = false;
	
	public ArmaJugador armaJugador;
	
	private boolean powerUP;
		
	
//	metodo constructor
	public Jugador(double JxPos, double JyPos, int Jwidth, int Jheight,EscudoBasico escudo,boolean powerUp) {
		this.JxPos = JxPos;
		this.JyPos = JyPos;
		this.Jwidth = Jwidth;
		this.Jheight = Jheight;
		setPowerUpJ(powerUp);
		
		rectangulo = new Rectangle((int)JxPos,(int)JyPos + 25,Jwidth,Jheight - 25);
		
		try {
			URL url = this.getClass().getResource("/imagenes/Player.png");
			jAnimacion = ImageIO.read(url);
		}catch(IOException e) {};
		
		this.escudo = escudo;
		
		
		armaJugador = new ArmaJugador();
		armaJugador.setPowerUPA(isPowerUpJ());
	}
	
	public void dibujarJugador(Graphics2D g) {
		g.drawImage(jAnimacion, (int)JxPos, (int)JyPos, Jwidth, Jheight, null);
		armaJugador.dibujarArma(g);
	}
	
	public void actualizarJugador(double cambioJ) {
		
//		Permite que el movimiento del jugador sea dentro de la ventana
		if(derecha && !izquierda && JxPos < Main.WIDTH-Jwidth-26) {
			JxPos += velocidad * cambioJ;
			rectangulo.x = (int)JxPos;
		}if(!derecha && izquierda && JxPos > 10) {
			JxPos -= velocidad * cambioJ;
			rectangulo.x = (int)JxPos;
		}
		
		armaJugador.actualizarArma(cambioJ, escudo);
		
		if(disparo) {
			armaJugador.dispararBala(JxPos, JyPos, 5, 5);
		}
	}
	
	public boolean isPowerUpJ() {
		return powerUP;
	}

	public void setPowerUpJ(boolean powerUp) {
		powerUP = powerUp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		cuando las teclas son precionadas se realiza el movimiento
		int tecla = e.getKeyCode();
		if(tecla == KeyEvent.VK_C || tecla == KeyEvent.VK_RIGHT) {
			derecha = true;
		}else if(tecla == KeyEvent.VK_X || tecla == KeyEvent.VK_LEFT) {
			izquierda = true;
		}
		if(tecla == KeyEvent.VK_SPACE || tecla == KeyEvent.VK_Z) {
			disparo = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		cuando se sueltan las teclas se detiene el movimiento
		int tecla = e.getKeyCode();
		if(tecla == KeyEvent.VK_C || tecla == KeyEvent.VK_RIGHT) {
			derecha = false;
		}else if(tecla == KeyEvent.VK_X || tecla == KeyEvent.VK_LEFT) {
			izquierda = false;
		}
		if(tecla == KeyEvent.VK_SPACE || tecla == KeyEvent.VK_Z) {
			disparo = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

}
