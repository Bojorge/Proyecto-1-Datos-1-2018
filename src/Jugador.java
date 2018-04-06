import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Jugador implements KeyListener{
	
	private final int velocidad = 5;
	private BufferedImage jAnimacion;
	private Rectangle rectangulo;
	private double JxPos, JyPos;
	private int Jwidth, Jheight;
//	private Animacion animacion;
	@SuppressWarnings("unused")
	private Escudo escudo;
		
	
	
	private boolean izquierda = false, derecha = false;
	

	
	private boolean powerUP;
		
	
//	metodo constructor
	public Jugador(double JxPos,double JyPos,int Jwidth,int Jheight) {
		this.JxPos = JxPos;
		this.JyPos = JyPos;
		this.Jwidth = Jwidth;
		this.Jheight = Jheight;
		new Animacion((int)JxPos,(int)JyPos,Jwidth,Jheight,"/imagenes/player.png");
		
		rectangulo = new Rectangle((int)JxPos,(int)JyPos,Jwidth,Jheight);
		
	}
	
	public void dibujarJugador(Graphics2D g) {
		g.drawImage(jAnimacion,(int)JxPos,(int)JyPos, Jwidth, Jheight, null);

	}
	
	public void actualizarJugador(double cambioJ) {
		
//		Permite que el movimiento del jugador sea dentro de la ventana
		if(derecha && !izquierda && JxPos < Facade.WIDTH-Jwidth) {
			JxPos += velocidad * cambioJ;
			rectangulo.x = (int)JxPos;
		}if(!derecha && izquierda && JxPos > 10) {
			JxPos -= velocidad * cambioJ;
			rectangulo.x = (int)JxPos;
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
			System.out.println("xxx");
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

