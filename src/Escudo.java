import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;


public class Escudo {

	public ListaDE barrera = new ListaDE();
	
//	genera la cantidad de escudos
	public Escudo(boolean powerUp) {
		if(powerUp) {
			escudoBasico(((Facade.WIDTH)/2)-350,450);
			escudoBasico(((Facade.WIDTH)/2),400);
			escudoBasico(((Facade.WIDTH)/2)+350,450);
		}
	}
	
	public void escudoBasico(int ESxPos, int ESyPos) {
		int anchoBarrera = 3;
		int xES= 0;
		@SuppressWarnings("unused")
		int yES = 0;
		
//		Le da forma a los escudos
		for(int i = 0;i < 3;i++) {
			if((14 + (i * 2) + anchoBarrera < 22 + anchoBarrera)) {
				fila(14 + (i * 2) + anchoBarrera, ESxPos - (i * 3), ESyPos + (i * 3));
				xES = (i *3) + 3;
			}else {
				fila(22 + anchoBarrera,ESxPos - xES,ESyPos + (i * 3));
				yES = (i * 3);
			}
		}
		
/*
		
//		Lado izquierdo
		for(int i = 0;i < 5;i++) {
			fila(8 + anchoBarrera - i,ESxPos - xES,(ESyPos + yES) + (i * 3));
		}
		
//		Lado derecho
		for(int i = 0;i < 5;i++) {
			fila(8 + anchoBarrera - i,(ESxPos - xES +(14 * 3)) + (i * 3),(ESyPos + yES) + (i * 3));
		}
*/
		
	}
	
		
	public void dibujarEscudo(Graphics2D g) {
		g.setColor(Color.MAGENTA.brighter());
		for(int i = 0;i < barrera.cantidad();i++) {
			g.fill((Shape) barrera.getElemento(i));
		}
	}
	
	public void fila(int filas,int FxPos,int FyPos) {
		for(int i = 0;i < filas;i++) {
			Rectangle ladrillo = new Rectangle(FxPos + (i * 3),FyPos,3,3);
			barrera.insertar(1, ladrillo);;
		}
	}
	
	public void restablecerEscudo() {
		barrera.limpiar();
		
		escudoBasico(((Facade.WIDTH)/2)-350,450);
		escudoBasico(((Facade.WIDTH)/2),400);
		escudoBasico(((Facade.WIDTH)/2)+350,450);
		
	}
}
