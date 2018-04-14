package powerUp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.Shape;
//import java.util.ArrayList;

import animacion.ListaDE;

public class Escudo { 

//	public ArrayList<Rectangle> barrera = new ArrayList<Rectangle>();
	public ListaDE barrera = new ListaDE();
	
	public Escudo(){
		Escudox(175, 500);
		Escudox(375, 450);
		Escudox(575, 500);
		
	}
	
	public void dibujarEscudo(Graphics2D g){
		g.setColor(Color.GREEN.darker().darker());
//		for(int i = 0; i < barrera.size(); i++){
//			g.fill(barrera.get(i));
		for(int i = 0; i < barrera.cantidad(); i++){
			g.fill((Rectangle) barrera.getElemento(i));
		}
	}
	
	public void Escudox(int xPos, int yPos){
		
		int anchoBarrera = 3;
		@SuppressWarnings("unused")
		int xES= 0;
		
//		Le da forma a los escudos
		for(int i = 0;i < 3;i++) {
			if((14 + (i * 2) + anchoBarrera < 22 + anchoBarrera)) {
				fila(14 + (i * 2) + anchoBarrera, xPos - (i * 3), yPos + (i * 3));
				xES = (i *3) + 3;
			}
		}
	}

	public void fila(int filas, int xPos, int yPos){
		for(int i = 0; i < filas; i++){
			Rectangle ladrillo = new Rectangle(xPos + (i * 3), yPos, 3, 3);
//			barrera.add(ladrillo);
			barrera.insertar(1,ladrillo);
		}
	}
	
	public void reset(){
//		barrera.clear();
		barrera.borrarTodo();
		
		Escudox(175, 500);
		Escudox(375, 450);
		Escudox(575, 500);
	}
}
