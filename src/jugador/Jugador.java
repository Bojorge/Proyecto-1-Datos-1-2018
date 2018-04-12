package jugador;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//import armas.Ametralladora;
import armas.ArmasJugador;
import inicio.Facade;
import powerUp.Escudo;

public class Jugador implements KeyListener{
	
	private final double velocidadJug = 5.0d;
	private int vida;
	
	private BufferedImage AnimacionJug;
	private Rectangle rect;
	private double xPos, yPos, startXPos, startYPos;
	private int ancho, altura;
	private Escudo escudo;
	
	private boolean izquierda = false, derecha = false, disparo = false;
	
	public ArmasJugador armaJugador;

	
	public Jugador(double xPos, double yPos, int ancho, int altura, Escudo escudo){
		this.xPos = xPos;
		this.yPos = yPos;
		this.startXPos = xPos;
		this.startYPos = yPos;
		this.ancho = ancho;
		this.altura = altura;
		this.vida = 2;
		
		rect = new Rectangle((int) xPos,(int) yPos+25, ancho, altura-25);
		
		try{
			URL url = this.getClass().getResource("/imagenes/Player.png");
			AnimacionJug = ImageIO.read(url);
		}catch(IOException e){};
		
		this.escudo = escudo;
		armaJugador = new ArmasJugador();
		
	}
	
	public void dibujarJugador(Graphics2D g){
		g.drawImage(AnimacionJug,(int) xPos,(int) yPos, ancho, altura, null);
		armaJugador.dibujarArmJug(g);
	}
	
	public void actualizarJugador(double delta){
		if(derecha && !izquierda && xPos < (Facade.WIDTH/2)+75){
			xPos += velocidadJug * delta;
			rect.x = (int) xPos;
		}if(!derecha && izquierda && xPos > 10){
			xPos -= velocidadJug * delta;
			rect.x = (int) xPos;
		}
		
		armaJugador.actualizarArmJug(delta, escudo);
		
		if(disparo){
			armaJugador.tiros(xPos, yPos, 5, 5);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			derecha = true;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			izquierda = true;
		}
		
		if (key == KeyEvent.VK_SPACE){
			disparo = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			derecha = false;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			izquierda = false;
		}
		
		if (key == KeyEvent.VK_SPACE){
			disparo = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
	
	public void JugadorHerido() {
		setVidas(getVidas()-1);
	}
	
	public int getVidas() {
		return vida;
	}
	
	public void setVidas(int vida) {
		this.vida = vida;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void resetJugador() {
		vida = 2;
		izquierda = false;
		derecha = false;
		disparo = false;
		
		xPos = startXPos;
		yPos = startYPos;
		rect.x = (int) xPos;
		rect.y = (int) yPos+25;
		armaJugador.resetArmJug();
	}
}
