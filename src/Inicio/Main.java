package Inicio;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import estado.EstadoMaquina;

public class Main extends Canvas implements Runnable{
	
//	Se usa los metodos getDefaultToolkit() y getScreenSize() de la clase ToolKit para obtener la medida de la pantalla en la que se ejecuta el juego 
	static Toolkit prevPantalla= Toolkit.getDefaultToolkit();
	static Dimension tamanoPantalla=  prevPantalla.getScreenSize();
	
	static int alturaPantalla= tamanoPantalla.height-45;
	static int anchoPantalla= tamanoPantalla.width-288;
	
//	Se declaran campos de clase estaticos para indicar que son pertenecientes a la clase y son compartidos por todos los metodos en este caso para referirse al tamaño de la pantalla y/o ventana
//	Cualquier cambio que se le quiera hacer en cuanto a tamaño de la ventana u objetos que se vean afectados con el cambio de tamaño, se hace desde aqui
	public static int WIDTH = anchoPantalla, HEIGHT = alturaPantalla;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static void main(String[] args) {
		Main inicio = new Main();
		JFrame ventana = new JFrame();
		
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setVisible(true);
		
		ventana.add(inicio);
		ventana.pack();
		ventana.setTitle("Invaders");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.setSize(WIDTH, HEIGHT);
		
		Image Icono= prevPantalla.getImage("src/imagenes/iconox.gif");
		ventana.setIconImage(Icono);
		
		
		
		inicio.comenzar();
		
		
	}
	
	public static EstadoMaquina estado;
	

	public Main() {
		setFocusable(true);
		
		estado = new EstadoMaquina(this);
		estado.setEstado((byte) 0);
	}
	
	
	
	private boolean funcionando = false;
	private Thread hilo;
	
	public synchronized void comenzar() {
		if(funcionando)
			return;
		funcionando = true;
		
		hilo = new Thread(this);
		hilo.start();
	}
	
	public synchronized void detener() {
		if(!funcionando)
			return;
		funcionando = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			//catch block
			e.printStackTrace();
		}
	}
	
	public int FPS;
	
	@Override
	public void run() {
//		inicia el tiempo 
		long temporizador = System.currentTimeMillis();
		long ultimoBucle = System.nanoTime();
		final int OBJETIVO_FPS = 60;
		final long TIEMPO_OPTIMO = 1000000000 / OBJETIVO_FPS;
		int ventanas = 0;
		
		this.createBufferStrategy(3);
		BufferStrategy almacena_datos = this.getBufferStrategy();
//		revisa cual es el tiempo
		while(funcionando) {
			long ya = System.nanoTime();
			long actualizarLongitud = ya - ultimoBucle;
			ultimoBucle = ya;
			double cambio = actualizarLongitud / ((double)TIEMPO_OPTIMO);
			
			ventanas++;
			
			if(System.currentTimeMillis() - temporizador > 1000) {
				temporizador += 1000;
				FPS = ventanas;
				ventanas = 0;
				System.out.println(FPS);
			}
			
//			metodos de esta misma clase
			dibujar(almacena_datos);
			actualizar(cambio);
			
			try{
				Thread.sleep(((ultimoBucle - System.nanoTime()) + TIEMPO_OPTIMO) / 1000000);
			}catch(Exception e) {};
			
			System.out.println("esta funcionando");
		}
		
	}
	
	public void dibujar(BufferStrategy almacena_datos) {
		do {
			do {
				Graphics2D g = (Graphics2D) almacena_datos.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				
				estado.dibujarEM(g);
				
				g.dispose();
			}while(almacena_datos.contentsRestored());
			almacena_datos.show();
		}while(almacena_datos.contentsLost());
	}
	
	public void actualizar(double cambio) {
		estado.actualizarEM(cambio);
	}

}
