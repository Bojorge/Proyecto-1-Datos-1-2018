package estado;

import java.awt.Canvas;
import java.awt.Graphics2D;

//Esta interface se usa como un array en la clase EstadoMaquina
//Se relaciona con cambios y con mostrar algún grafico en pantalla
public interface SuperEstadoMaquina {
	
	public void actualizarSEM(double cambioSEM);
	public void dibujarSEM(Graphics2D g);
	public void initSEM(Canvas canvas);

}
