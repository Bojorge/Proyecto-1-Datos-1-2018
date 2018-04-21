package inicio;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import estados.MaquinaDeEstados;




public class Facade extends Canvas implements Runnable {
	
	private boolean funcionando = false;
	private Thread hilo;
	public int FPS;
	public static MaquinaDeEstados estado;
	private static final long serialVersionUID = 1L;
//	Se usa los metodos getDefaultToolkit() y getScreenSize() de la clase ToolKit para obtener la medida de la pantalla en la que se ejecuta el juego 
	static Toolkit prevPantalla= Toolkit.getDefaultToolkit();
	static Dimension tamanoPantalla=  prevPantalla.getScreenSize();
	
//	Se declaran campos de clase estaticos para indicar que son pertenecientes a la clase y son compartidos por todos los metodos en este caso para referirse al tamaño de la pantalla y/o ventana
//	Cualquier cambio que se le quiera hacer en cuanto a tamaño de la ventana u objetos que se vean afectados con el cambio de tamaño, se hace desde aqui
	public static int WIDTH = tamanoPantalla.width-15, HEIGHT = tamanoPantalla.height-100;
	
	public static void main(String[] args) {
		Facade mostrar = new Facade();
		JFrame ventana = new JFrame();		
		
		ventana.add(mostrar);
		ventana.pack();
		ventana.setTitle("Invaders");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setVisible(true);
		
		Toolkit icon = Toolkit.getDefaultToolkit();
		Image Icono= icon.getImage("src/imagenes/icono.gif");
		ventana.setIconImage(Icono);
		
		mostrar.iniciar();
	}
	
	

	public Facade() {
		this.setSize(WIDTH, HEIGHT);
		this.setFocusable(true);	
		estado = new MaquinaDeEstados(this);
		estado.setEstado((byte) 0);
		
	}
	
	@Override
	public void run() {
		long temporizador = System.currentTimeMillis();
		long tiempoUltimoBucle = System.nanoTime();
		final int OBJETIVO_FPS = 60;
		final long TIEMPO_OPTIMO = 1000000000 / OBJETIVO_FPS;
		int ventanas = 0;
		this.createBufferStrategy(3);
		BufferStrategy buffStrat = this.getBufferStrategy();
		while (funcionando) {
			long ya = System.nanoTime();
			long actualizarLargo = ya - tiempoUltimoBucle;
			tiempoUltimoBucle = ya;
			double cambio = actualizarLargo / ((double) TIEMPO_OPTIMO);
			ventanas++;
			if (System.currentTimeMillis() - temporizador > 1000) {
				temporizador += 1000;
				FPS = ventanas;
				ventanas = 0;
				System.out.println(FPS);
			}
			System.out.println("lml");

			dibujar(buffStrat); 
			actualizar(cambio);

			try {
				Thread.sleep(((tiempoUltimoBucle - System.nanoTime()) + TIEMPO_OPTIMO) / 1000000);
			} catch (Exception e) {
			}
			;
		}
	}
	
	public synchronized void iniciar() {
		if (funcionando)
			return;
		funcionando = true;
		hilo = new Thread(this);
		hilo.start();
	}

	public synchronized void detener() {
		if (!funcionando)
			return;
		funcionando = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public void dibujar(BufferStrategy buffStrat) { 
		do {
			do {
				Graphics2D g = (Graphics2D) buffStrat.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0, 0, WIDTH +50, HEIGHT + 50);
				estado.dibujarMaqEst(g);
				g.dispose();
			} while (buffStrat.contentsRestored());
			buffStrat.show();
		} while (buffStrat.contentsLost());
	}

	public void actualizar(double cambio) {
		estado.actualizarMaqEst(cambio);
	}

}
