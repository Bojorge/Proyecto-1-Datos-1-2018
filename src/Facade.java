import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Facade extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private boolean ejecutando = false;
	private Thread hilo;
	public int FPS;
	
//	Se usa los metodos getDefaultToolkit() y getScreenSize() de la clase ToolKit para obtener la medida de la pantalla en la que se ejecuta el juego 
	static Toolkit prevPantalla= Toolkit.getDefaultToolkit();
	static Dimension tamanoPantalla=  prevPantalla.getScreenSize();
	
//	Se declaran campos de clase estaticos para indicar que son pertenecientes a la clase y son compartidos por todos los metodos en este caso para referirse al tama�o de la pantalla y/o ventana
//	Cualquier cambio que se le quiera hacer en cuanto a tama�o de la ventana u objetos que se vean afectados con el cambio de tama�o, se hace desde aqui
	public static int WIDTH = tamanoPantalla.width, HEIGHT = tamanoPantalla.height;
	
	public Facade() {
		JFrame ventana = new JFrame();
		ventana.add(this);
		ventana.pack();
		ventana.setTitle("Invaders");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setSize(WIDTH,HEIGHT);
		ventana.setFocusable(true);
		ventana.setVisible(true);
	}
	
	public synchronized void iniciar() {
		if (ejecutando)
			return;

		ejecutando = true;

		hilo = new Thread(this);
		hilo.start();
	}

	public synchronized void detener() {
		if (!ejecutando)
			return;

		ejecutando = false;

		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long temporizador = System.currentTimeMillis();
		long tiempoUltimoBucle = System.nanoTime();
		final int OBJETIVO_FPS = 60;
		final long TIEMPO_OPTIMO = 1000000000 / OBJETIVO_FPS;
		int ventanas = 0;

		this.createBufferStrategy(3);
		BufferStrategy bs = this.getBufferStrategy();
		while (ejecutando) {
			long ya = System.nanoTime();
			long actualizarLargo = ya - tiempoUltimoBucle;
			tiempoUltimoBucle = ya;
			double cambio = actualizarLargo / ((double) TIEMPO_OPTIMO);

			ventanas++;
			System.out.println("funciona");

			if (System.currentTimeMillis() - temporizador > 1000) {
				temporizador += 1000;
				FPS = ventanas;
				ventanas = 0;
				System.out.println(FPS);
			}

			draw(bs);
			actualizar(cambio);

			try {
				Thread.sleep(((tiempoUltimoBucle - System.nanoTime()) + TIEMPO_OPTIMO) / 1000000);
			} catch (Exception e) {
			}
			;
		}
	}

	public void draw(BufferStrategy bs) {
		do {
			do {
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0,0,WIDTH,HEIGHT);
				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public void actualizar(double cambio) {
	}
	
	
}


