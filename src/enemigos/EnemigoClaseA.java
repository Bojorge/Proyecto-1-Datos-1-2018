package enemigos;

import java.util.ArrayList;

import armas.GestorBalaEnemigo;

public class EnemigoClaseA extends EnemigoBasico{
	
	private GestorBalaEnemigo gestorBalaEnemigo;
	private ArrayList<TipoDeEnemigo> enemigosA = new ArrayList<TipoDeEnemigo>();
	
	public EnemigoClaseA(double xPos, double yPos, int filas, int columnas, GestorBalaEnemigo gestorBalaEnemigo) {
		super(xPos,yPos,filas,columnas,gestorBalaEnemigo);
		
		
			}

	
}
