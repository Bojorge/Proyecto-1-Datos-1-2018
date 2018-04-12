package inicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import estados.MaquinaDeEstados;
import jugador.Jugador;
import niveles.Nivel;
import niveles.PantallaJuego;
import powerUp.Escudo;

import java.io.*;

class Estadisticas extends PantallaJuego{
	static MaquinaDeEstados estado;
	private Jugador jugador;
	private Escudo escudo;
	private Nivel nivel;
	public Estadisticas(){
		super(estado);
		
		
		JFrame ventana=new JFrame();
		ventana.setTitle("Estadisticas");
		ventana.setBounds(1063,0,315,723);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		
		Toolkit icon= Toolkit.getDefaultToolkit();
		Image Icono= icon.getImage("src/imagenes/icono.gif");
		ventana.setIconImage(Icono);
		Fondo elfondo = new Fondo();
		ventana.add(elfondo);
		
	}
	
	public void dibujarEst(Graphics2D g) {
		g.setColor(Color.white);
		g.setFont(new Font("AR DELANEY",Font.PLAIN,35));
		g.drawString("PUNTOS: " + Puntos,800, 100);
		
		g.setColor(Color.red);
		g.drawString("VIDAS: " + jugador.getVidas(),800, 150);
		
		g.setColor(Color.blue);			
		g.drawString("NIVEL: ",800, 200);
				
		escudo.dibujarEscudo(g);
		jugador.dibujarJugador(g);
		nivel.dibujarNivPad(g);
	}
}



class Fondo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		File imagenEspacio = new File("src/imagenes/fondo.gif");
		
		//el metodo read lanza una excepcion y hay que hacer el codigo que prevea alguna excepcion
		try{
		imagen=ImageIO.read(imagenEspacio);
		}
		catch(IOException x){
			System.out.println("la imagen no se encuentra");
		}
		g.drawImage(imagen, 0, 0, null);
		
	}
	private Image imagen;
}

