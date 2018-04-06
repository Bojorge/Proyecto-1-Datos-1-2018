

import java.awt.Canvas;
import java.awt.Graphics2D;


public class MaquinaDeEstados {

	private ListaDE estados = new ListaDE();
	private Canvas canvas;
	private byte selectEstado = 0;
	
	public MaquinaDeEstados(Canvas canvas){
		MaquinaEstadosSuper game = new PantallaJuego(this);
//		SuperStateMachine menu = new MenuScreen(this);
//		states.add(menu);
		estados.insertar(1,game);
		
		this.canvas = canvas;
	}
	
	public void draw(Graphics2D g){
		((MaquinaEstadosSuper)estados.extraer(selectEstado)).dibujarMESup(g);
	}
	
	public void update(double delta){
		((MaquinaEstadosSuper)estados.extraer(selectEstado)).actualizarMESup(delta);
	}
	
	public void setState(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		selectEstado = i;
		((MaquinaEstadosSuper)estados.extraer(selectEstado)).inicializarMESup(canvas);
	}

	public byte getEstados() {
		return selectEstado;
	}
}
