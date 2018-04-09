package mapaBitsLCD;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import temporizador.Temporizador;


public class Animacion {
	
	private ListaDE animaciones = new ListaDE();
	private byte animacionActual;
	
	private boolean bucleAS = false;
	private boolean jugar = false;
	private boolean destruirDespuesDeLaAnimacion = false;
	
	private Temporizador timer;
	private int velocidadAnimacion;
	private double ASxPos, ASyPos;
	private int ASwidth, ASheight;
	
	private int limiteBalEn;
	
	public Animacion(double ASxPos,double ASyPos,int ASfilas,int AScolumnas,int velocidadAnimacion, String imgRuta) {
		this.velocidadAnimacion = velocidadAnimacion;
		this.ASxPos = ASxPos;
		this.ASyPos = ASyPos;
		
		try {
			URL url = this.getClass().getResource(imgRuta);
			BufferedImage jAnimacion = ImageIO.read(url);
			int AnimAncho = jAnimacion.getWidth() / AScolumnas;
			int AnimLargo = jAnimacion.getHeight() / ASfilas;
			for(int y = 0;y < ASfilas;y++) {
//				le da el efecto de movimiento a los enemigos y el tamaño
				for(int x = 0;x < AScolumnas; x++) {
					addAnimacion(jAnimacion
							, 0 + (x * AnimAncho)
							, 0 + (y * AnimLargo)
							, AnimAncho
							, AnimLargo);  
				}
			} 
			
		
		}catch(IOException e) {};
		
		timer = new Temporizador();
		
		limiteBalEn = animaciones.cantidad() - 1;
	}
	
	public void dibujarAS(Graphics2D g) {
		if(seDestruyeAnimacion())
			return;
		
		g.drawImage((Image)animaciones.getElemento(animacionActual), (int)getASxPos(), (int)getASyPos(),ASwidth,ASheight, null);
	}
	
	public void actualizarAS(double cambioAS) {
		if(seDestruyeAnimacion())
			return;
		
		if(bucleAS && !jugar)
			bucleAnimacion();
		if(jugar && !bucleAS)
			juegoAnimacion();
	}
	
	public void detenerAnimacion() {
		bucleAS = false;
		jugar = false;
	}
	
	public void resetMapaBits() {
		bucleAS = false;
		jugar = false;
		animacionActual = 0;
	}
	
	private void bucleAnimacion() {
		if(timer.temporizadorListo(velocidadAnimacion) && animacionActual == limiteBalEn) {
			animacionActual = 0;
			timer.resetTemporizador();
		}else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual != limiteBalEn) {
			animacionActual++;
		} 
		
	}
	
	private void juegoAnimacion() {
		if(timer.temporizadorListo(velocidadAnimacion) && animacionActual != limiteBalEn && !esDestruidoDespuesDeLaAnimacion()) {
			jugar = false;
			animacionActual = 0;
		} else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual == limiteBalEn && esDestruidoDespuesDeLaAnimacion()) {
			animaciones = null;
		}else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual != limiteBalEn) {
			animacionActual++;
		}
	}
	
	
	
	public byte getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(byte animacionActual) {
		this.animacionActual = animacionActual;
	}

	public boolean isBucleAS() {
		return bucleAS;
	}

	public void setBucleAS(boolean bucleAS) {
		this.bucleAS = bucleAS;
	}

	public boolean seDestruyeAnimacion() {
		if(animaciones == null)
			return true;
		
		return false;
	}
	
	public boolean esDestruidoDespuesDeLaAnimacion() {
		return destruirDespuesDeLaAnimacion;
	}
	
	public void setDestruidoDespuesDeLaAnimacion(boolean destruirDespuesDeLaAnimacion) {
		this.destruirDespuesDeLaAnimacion = destruirDespuesDeLaAnimacion;
	}
	
	public void addAnimacion(BufferedImage spriteMap, int AxPos, int AyPos, int ancho, int alto) {
		animaciones.insertar(1,spriteMap.getSubimage(AxPos, AyPos, ancho, alto));
	}
	
	public void AnimacionJugador(boolean jugar,boolean destruirDespuesDeLaAnimacion) {
		if(bucleAS) {
			bucleAS = false;
		}
		
		this.jugar = jugar;
		this.setDestruidoDespuesDeLaAnimacion(destruirDespuesDeLaAnimacion);
		
	}

	public double getASxPos() {
		return ASxPos;
	}

	public void setASxPos(double aSxPos) {
		ASxPos = aSxPos;
	}

	public double getASyPos() {
		return ASyPos;
	}

	public void setASyPos(double ASyPos) {
		this.ASyPos = ASyPos;
	}

	public int getASwidth() {
		return ASwidth;
	}

	public void setASwidth(int aSwidth) {
		ASwidth = aSwidth;
	}

	public int getASheight() {
		return ASheight;
	}

	public void setASheight(int aSheight) {
		ASheight = aSheight;
	}

	public int getVelocidadAnimacion() {
		return velocidadAnimacion;
	}

	public void setVelocidadAnimacion(int velocidadAnimacion) {
		this.velocidadAnimacion = velocidadAnimacion;
	}

	public int getLimiteBalEn() {
		return limiteBalEn;
	}

	public void setLimiteBalEn(int limiteBalEn) {
		if(limiteBalEn > 0) {
			this.limiteBalEn = limiteBalEn -1;
		}else {
			this.limiteBalEn = limiteBalEn;
		}
	
	}
	
	public void resetLimiteBalEn() {
		limiteBalEn = animaciones.cantidad() - 1;
	}

	public boolean isJugar() {
		return jugar;
	}

	public void setJugar(boolean jugar) {
		this.jugar = jugar;
	}
}
