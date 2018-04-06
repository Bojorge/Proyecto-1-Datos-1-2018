

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class MaquinaEstadosSuper {

	private MaquinaDeEstados maquinaEstados;
	
	public MaquinaEstadosSuper(MaquinaDeEstados maquinaEstados) {
		this.maquinaEstados = maquinaEstados;
	}
	
	public abstract void actualizarMESup(double delta);
	public abstract void dibujarMESup(Graphics2D g);
	public abstract void inicializarMESup(Canvas canvas);
	
	public MaquinaDeEstados getMaquinaDeEstadose() {
		return maquinaEstados;
	}
}
