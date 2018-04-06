import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class Animacion {
	
	private ListaDE animaciones = new ListaDE(); // Lista doblemente enlazada
	private byte animacionActual;
	private boolean bucleAS = false;
	private boolean jugar = false;
	private boolean destruirDespuesDeLaAnimacion = false;
	private Temporizador timer;
	private int velocidadAnimacion;
	private int AnimxPos, AnimyPos;
	private int AnimAncho, AnimAltura;
	private int limiteBalEn;
	
//	constructor: recibe posicion, tamaño e imagen para construir al jugador y enemigos
	public Animacion(int AnimxPos,int AnimyPos,int Filas,int Columnas, String imgRuta) {
		
		this.AnimxPos = AnimxPos;
		this.AnimyPos = AnimyPos;
		this.AnimAncho = Filas;
		this.AnimAltura = Columnas;
		
		try {
			URL url = this.getClass().getResource(imgRuta);
			BufferedImage jAnimacion = ImageIO.read(url);
			int AnimAncho = jAnimacion.getWidth(); 
			int AnimLargo = jAnimacion.getHeight();
			addAnimacion(jAnimacion,AnimAncho,AnimLargo,AnimAncho,AnimLargo);
		}catch(IOException e) {};
		
		timer = new Temporizador();

	}
	
//	Si la lista NO esta vacia ( seDestruyeAnimacion() ) entonces toma "animacionActual" como indice en la lista para dibujarlo
	public void dibujarAS(Graphics2D g) {
		if(seDestruyeAnimacion())
			return;
		g.drawImage((Image)animaciones.getElemento(animacionActual), (int)getASxPos(), (int)getASyPos(),AnimAncho,AnimAltura, null);
	}
	
//	Mantiene actualizada la animacion
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
	
//	Restaura la animacion
	public void resetMapaBits() {
		bucleAS = false;
		jugar = false;
		animacionActual = 0;
	}
	
	private void bucleAnimacion() {
		if(timer.temporizadorListo(velocidadAnimacion) && animacionActual != animaciones.cantidad()-1) {
			animacionActual = 0;
			timer.resetTemporizador();
		}else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual == animaciones.cantidad()-1) {
			animacionActual++;
		} 	
	}
	
	private void juegoAnimacion() {
		if(timer.temporizadorListo(velocidadAnimacion) && animacionActual != animaciones.cantidad()-1 && !esDestruidoDespuesDeLaAnimacion()) {//!esDestruidoDespuesDeLaAnimacion()
			jugar = false;
			animacionActual = 0;
		} else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual == animaciones.cantidad()-1 && esDestruidoDespuesDeLaAnimacion()) {//esDestruidoDespuesDeLaAnimacion()
			animaciones = null;
		}else if(timer.tiempoEvento(velocidadAnimacion) && animacionActual != animaciones.cantidad()-1) {
			animacionActual++;
		}
	}
	
	public void AnimacionJugador(boolean jugar,boolean destruirDespuesDeLaAnimacion) {
		if(bucleAS) {
			bucleAS = false;
		}
		this.jugar = jugar;
		this.setDestruidoDespuesDeLaAnimacion(destruirDespuesDeLaAnimacion);	
	}
	
//	Es verdadero si la lista esta vacia
	public boolean seDestruyeAnimacion() {
		if(animaciones==null)
			return true;
		
		return false;
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
	
	public boolean esDestruidoDespuesDeLaAnimacion() {
		return destruirDespuesDeLaAnimacion;
	}
	
	public void setDestruidoDespuesDeLaAnimacion(boolean destruirDespuesDeLaAnimacion) {
		this.destruirDespuesDeLaAnimacion = destruirDespuesDeLaAnimacion;
	}
	
	public void addAnimacion(BufferedImage spriteMap, int AxPos, int AyPos, int ancho, int alto) {
		animaciones.insertar(1,spriteMap.getSubimage(AxPos, AyPos, ancho, alto));
	}
	
//	Restaura el limite bala-enemigo (han dejado de estar en la misma pocision)
	public void resetLimiteBalEn() {
		limiteBalEn = animaciones.cantidad() - 1;
	}
	
//	SETTER´s  /  GETTER´s
	
	public double getASxPos() {
		return AnimxPos;
	}

	public void setASxPos(int aSxPos) {
		AnimxPos = aSxPos;
	}

	public double getASyPos() {
		return AnimyPos;
	}

	public void setASyPos(int ASyPos) {
		this.AnimyPos = ASyPos;
	}

	public int getASwidth() {
		return AnimAncho;
	}

	public void setASwidth(int aSwidth) {
		AnimAncho = aSwidth;
	}

	public int getASheight() {
		return AnimAltura;
	}

	public void setASheight(int aSheight) {
		AnimAltura = aSheight;
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

	public boolean isJugar() {
		return jugar;
	}

	public void setJugar(boolean jugar) {
		this.jugar = jugar;
	}
}

