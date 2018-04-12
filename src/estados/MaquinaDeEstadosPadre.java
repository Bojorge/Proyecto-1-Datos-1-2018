package estados;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class MaquinaDeEstadosPadre {

	private MaquinaDeEstados maquinaDeEstados;
	
	public MaquinaDeEstadosPadre(MaquinaDeEstados maquinaDeEstados) {
		this.maquinaDeEstados = maquinaDeEstados;
	}
	
	public abstract void actualizarMaqEstPad(double cambio);
	public abstract void dibujarMaqEstPad(Graphics2D g);
	public abstract void iniciarMaqEstPad(Canvas canvas);
	
	public MaquinaDeEstados getMaquinaDeEstados() {
		return maquinaDeEstados;
	}
}
