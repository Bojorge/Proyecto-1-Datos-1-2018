package Inicio;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;

class Estadisticas extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Estadisticas(){
		setTitle("Estadisticas");
		setBounds(1063,0,315,723);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit prevPantalla= Toolkit.getDefaultToolkit();
		Image Icono= prevPantalla.getImage("src/imagenes/Player.gif");
		setIconImage(Icono);
		
		Fondo elfondo = new Fondo();
		add(elfondo);
		
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

