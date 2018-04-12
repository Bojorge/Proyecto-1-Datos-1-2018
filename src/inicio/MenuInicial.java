package inicio;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import estados.MaquinaDeEstados;
import estados.MaquinaDeEstadosPadre;

public class MenuInicial extends MaquinaDeEstadosPadre implements KeyListener {

	private Font fuenteTitulo = new Font("Times New Roman", Font.PLAIN, 66);
	private Font initFuente = new Font("Algerian", Font.PLAIN, 33);
	
//	texto del titulo
	private String Titulo = "Invaders";
	private String iniciar = "Presione Enter para iniciar";
	
	public MenuInicial(MaquinaDeEstados maquinaDeEstados) {
		super(maquinaDeEstados);
	}

	@Override
	public void actualizarMaqEstPad(double cambio) {

	}

	@Override
	public void dibujarMaqEstPad(Graphics2D g) {
//		le da color y posicion a las letras del titulo
		g.setFont(fuenteTitulo);
//		int anchoTitulo = g.getFontMetrics().stringWidth(Titulo);
		g.setColor(Color.green.brighter());
//		se usa textos iguales de diferente color con una pequeña diferencia en el color
		g.drawString(Titulo, 300, (470)-122);
		g.setColor(Color.blue); 
		g.drawString(Titulo, 300, (470)-125);
		g.setColor(Color.LIGHT_GRAY.darker()); 
		g.drawString(Titulo, 300, (470)-128);
		
		g.setColor(Color.white);
		g.setFont(initFuente);
//		int anchoInicio = g.getFontMetrics().stringWidth(iniciar);
		g.drawString(iniciar, 700, 345);
	}

	@Override
	public void iniciarMaqEstPad(Canvas canvas) {
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getMaquinaDeEstados().setEstado((byte) 1);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
