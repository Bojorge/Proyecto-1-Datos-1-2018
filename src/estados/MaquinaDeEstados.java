package estados;

import java.awt.Canvas;
import java.awt.Graphics2D;
//import java.util.ArrayList;

import animacion.ListaDE;
import inicio.MenuInicial;
import niveles.PantallaJuego;

public class MaquinaDeEstados {

//	private ArrayList<MaquinaDeEstadosPadre> estados = new ArrayList<MaquinaDeEstadosPadre>();
	private ListaDE estados = new ListaDE();
	private Canvas canvas;
	private byte selectEstado = 0;
	
	public MaquinaDeEstados(Canvas canvas){
		MaquinaDeEstadosPadre juego = new PantallaJuego(this);
		MaquinaDeEstadosPadre menu = new MenuInicial(this);
//		estados.add(menu);
//		estados.add(juego);
		estados.insertar(1,juego);
		estados.insertar(1,menu);
		
		this.canvas = canvas;
	}
	
	public void dibujarMaqEst(Graphics2D g){
//		estados.get(selectEstado).dibujarMaqEstPad(g);
		((MaquinaDeEstadosPadre) estados.getElemento(selectEstado)).dibujarMaqEstPad(g);
	}
	
	public void actualizarMaqEst(double cambio){
//		estados.get(selectEstado).actualizarMaqEstPad(cambio);
		((MaquinaDeEstadosPadre) estados.getElemento(selectEstado)).actualizarMaqEstPad(cambio);
	}
	
	public void setEstado(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		selectEstado = i;
//		estados.get(selectEstado).iniciarMaqEstPad(canvas);
		((MaquinaDeEstadosPadre) estados.getElemento(selectEstado)).iniciarMaqEstPad(canvas);
	}

	public byte getEstado() {
		return selectEstado;
	}
}
