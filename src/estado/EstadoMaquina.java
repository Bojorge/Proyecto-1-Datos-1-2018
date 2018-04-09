package estado;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import mapaBitsLCD.ListaDE;
import pantallaJuego.PantallaJuego;

public class EstadoMaquina {
	
//	private ArrayList<SuperEstadoMaquina>estados = new ArrayList<SuperEstadoMaquina>();
//	private SuperEstadoMaquina[] estados = new SuperEstadoMaquina[10];
	private ListaDE estados = new ListaDE();
	private Canvas canvasEM;
	private byte seleccionarEstado = 0;
	
	public EstadoMaquina(Canvas canvasEM) {
//		En la siguiente instanciacion se aplica el polimorfismo
		SuperEstadoMaquina juego = new PantallaJuego();
		estados.insertar(1,juego);
		
		this.canvasEM = canvasEM;
	}
	
	public void dibujarEM(Graphics2D g) {
		((SuperEstadoMaquina) estados.getElemento(seleccionarEstado)).dibujarSEM(g);
	}
	
	public void actualizarEM(double cambioEM) {
		((SuperEstadoMaquina) estados.getElemento(seleccionarEstado)).actualizarSEM(cambioEM);
	}
	
	public void setEstado(byte i) {
		for(int r = 0; r < canvasEM.getKeyListeners().length;r++)
			canvasEM.removeKeyListener(canvasEM.getKeyListeners()[r]);
		seleccionarEstado = i;
		((SuperEstadoMaquina) estados.getElemento(seleccionarEstado)).initSEM(canvasEM);
	}

	public byte getEstados() {	//getEstados() 
		return seleccionarEstado;
	}
	
	
}
