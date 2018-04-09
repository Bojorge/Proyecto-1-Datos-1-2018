package powerUP;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Inicio.Main;

public class EscudoBasico {
	
	public ArrayList<Rectangle>barrera = new ArrayList<Rectangle>();
	
//	genera la cantidad de escudos
	public EscudoBasico(boolean powerUp) {
		if(powerUp) {
			escudoBasico(((Main.WIDTH)/2)-350,450);
			escudoBasico(((Main.WIDTH)/2),400);
			escudoBasico(((Main.WIDTH)/2)+350,450);
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
	}
	
		
	public void dibujarEscudo(Graphics2D g) {
		g.setColor(Color.MAGENTA.brighter());
		for(int i = 0;i < barrera.size();i++) {
			g.fill(barrera.get(i));
		}
	}
	
	public void fila(int filas,int FxPos,int FyPos) {
		for(int i = 0;i < filas;i++) {
			Rectangle ladrillo = new Rectangle(FxPos + (i * 3),FyPos,3,3);
			barrera.add(ladrillo);
		}
	}
	
	public void restablecerEscudo() {
		barrera.clear();
		
		escudoBasico(((Main.WIDTH)/2)-350,450);
		escudoBasico(((Main.WIDTH)/2),400);
		escudoBasico(((Main.WIDTH)/2)+350,450);
		
	}
}
