package animacion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
//import java.util.ArrayList;

import javax.imageio.ImageIO;

import temporizador.Temporizador;

public class Animacion {

//	private ArrayList<BufferedImage> animaciones = new ArrayList<BufferedImage>();
	private ListaDE animaciones = new ListaDE();
	private byte animacionActual;

	private boolean bucleAnimacion = false;
	private boolean playAnimacion = false;
	private boolean destruirPostAnimacion = false;

	private Temporizador temporizador;
	private int velocidadAnimacion;
	private double xPos, yPos;
	private int ancho, altura;
	private int limite;

	public Animacion(double xPos, double yPos, int filas, int columnas, int velocidadAnimacion, String imgRuta) {
		this.velocidadAnimacion = velocidadAnimacion;
		this.xPos = xPos;
		this.yPos = yPos;
		
		try{
			URL url = this.getClass().getResource(imgRuta);
			BufferedImage imagenJugador = ImageIO.read(url);
			int anchoImagen = imagenJugador.getWidth() / columnas;
			int alturaImagen = imagenJugador.getHeight() / filas;
			for(int y = 0; y < filas; y++) {
				for(int x = 0; x < columnas; x++){
					addAnimacion(imagenJugador
							, 0 + (x * anchoImagen)
							, 0 + (y * alturaImagen)
							, anchoImagen
							, alturaImagen);
				}
			}
					
			
		}catch(IOException e){};

		temporizador = new Temporizador();
//		limite = animaciones.size() - 1;
		limite = animaciones.cantidad() - 1;
	}

	public void dibujarAnimacion(Graphics2D g) {
		if (esDestruidaLaAnimacion())
			return;
	
//		g.drawImage(animaciones.get(animacionActual), (int) getxPos(), (int) getyPos(), ancho, altura, null);
		g.drawImage((Image) animaciones.getElemento(animacionActual), (int) getxPos(), (int) getyPos(), ancho, altura, null);
	}

	public void actualizarAnimacion(double cambio) {
		if (esDestruidaLaAnimacion())
			return;

		if (bucleAnimacion && !playAnimacion)
			bucleAnimacion();
		if (playAnimacion && !bucleAnimacion)
			playAnimacion();
	}

	public void detenerAnimacion() {
		bucleAnimacion = false;
		playAnimacion = false;
	}

	public void resetAnimacion() {
		bucleAnimacion = false;
		playAnimacion = false;
		animacionActual = 0;
	}

	private void bucleAnimacion() {
		if (temporizador.temporizadorListo(velocidadAnimacion) && animacionActual == limite) {
			animacionActual = 0;
			temporizador.resetTemporizador();
		}else if (temporizador.tiempoEvento(velocidadAnimacion) && animacionActual != limite) {
			animacionActual++;
		} 
	}

	private void playAnimacion() {
		if (temporizador.temporizadorListo(velocidadAnimacion) && animacionActual != limite && !esDestruidoPostAnimacion()) {
			playAnimacion = false;
			animacionActual = 0;
		} else if (temporizador.temporizadorListo(velocidadAnimacion) && animacionActual == limite && esDestruidoPostAnimacion()) {
			animaciones = null;
		}else if (temporizador.tiempoEvento(velocidadAnimacion) && animacionActual != limite) {
			animacionActual++;
		}
	}
	
	public byte getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(byte animacionActual) {
		this.animacionActual = animacionActual;
	}

	public boolean estaEjecutandoBucleAnimacion() {
		return bucleAnimacion;
	}

	public void setBucleAnimacion(boolean bucle) {
		this.bucleAnimacion = bucle;
	}

	public boolean esDestruidaLaAnimacion() {
		if (animaciones == null)
			return true;

		return false;
	}

	public void addAnimacion(BufferedImage imagenAnimacion, int xPos, int yPos,
			int anchoAnim, int alturaAnim) {
//		animaciones.add(imagenAnimacion.getSubimage(xPos, yPos, anchoAnim, alturaAnim));
		animaciones.insertar(1,imagenAnimacion.getSubimage(xPos, yPos, anchoAnim, alturaAnim));
//		insertar(indice,imagen) 
	}

	public void setPlayAnimacion(boolean play, boolean destruirPostAnimacion) {
		if(bucleAnimacion) {
			bucleAnimacion = false;
		}
		
		this.playAnimacion = play;
		this.setDestruirPostAnimacion(destruirPostAnimacion);
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean esDestruidoPostAnimacion() {
		return destruirPostAnimacion;
	}

	public void setDestruirPostAnimacion(boolean destruirPostAnimacion) {
		this.destruirPostAnimacion = destruirPostAnimacion;
	}

	public int getAnchoAnimacion() {
		return ancho;
	}

	public void setAnchoAnimacion(int ancho) {
		this.ancho = ancho;
	}

	public int getAlturaAnimacion() {
		return altura;
	}

	public void setAlturaAnimacion(int altura) {
		this.altura = altura;
	}

	public int getVelocidadAnimacion() {
		return velocidadAnimacion;
	}

	public void setVelocidadAnimacion(int velocidadAnimacion) {
		this.velocidadAnimacion = velocidadAnimacion;
	}

	public int getLimiteAnimacion() {
		return limite;
	}

	public void setLimiteAnimacion(int limite) {
		if(limite > 0) {
			this.limite = limite - 1;
		} else {
			this.limite = limite;
		}
	}
	
	public void resetLimiteAnimacion() {
//		limite = animaciones.size() - 1;
		limite = animaciones.cantidad() - 1;
	}

	public boolean isPlayAnimacion() {
		return playAnimacion;
	}
}
