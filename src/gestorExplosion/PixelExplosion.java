package gestorExplosion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class PixelExplosion implements TipoDeExplosion{

	private double[] xPos, yPos, xPosVol, yPosVol, angulo, energia;
	
//	apartir de una posicion crea listas con dada cantidad de elementos (pixels)
//	
	public PixelExplosion(double xPos, double yPos) {
		int pixels = 35;
		this.xPos = new double[pixels];
		this.yPos = new double[pixels];
		this.xPosVol = new double[pixels];
		this.yPosVol = new double[pixels];
		this.angulo = new double[pixels];
		this.energia = new double[pixels];
		
		for (int i = 0; i < pixels; i++) {
			this.xPos[i] = xPos;
			this.yPos[i] = yPos;
			
			this.xPosVol[i] = Math.random() * 3; //volumen de la explosion en x
			this.yPosVol[i] = Math.random() * 0.8; //volumen de la explosion en y
			this.energia[i] = Math.random(); //el largo de la trayectoria de un "pedazo" de la explosion
			
			Random anguloAleatorio = new Random();
			angulo[i] = anguloAleatorio.nextInt(100) + 1;
		}
		
	}
	
	@Override
	public void dibujarTipoExplosion(Graphics2D g) {
		for (int i = 0; i < xPos.length; i++) {
			if(energia[i] >= 0.00d) {
				g.setColor(new Color(1.0f, 1.0f, 0f, (float) energia[i]));
				
			} else {
				g.setColor(Color.red);
			}
			g.fillRect((int) xPos[i], (int) yPos[i], 4, 2);
		}
	}

	@Override
	public void actualizarTipoExplosion(double cambio) {
		
		for (int i = 0; i < xPos.length; i++) {
			energia[i] -= 0.01d; //a esta cantidad aleatoria se le resta con cada ciclo
			xPos[i] += xPosVol[i] * Math.cos(angulo[i]); //aumenta la distancia manteniendo un angulo
			yPos[i] += yPosVol[i] * Math.cos(angulo[i]);
		}
	}

	@Override
	public boolean expDestruir() {
		int destruir = 0;
		for (int i = 0; i < xPos.length; i++) {
			if(energia[i] < 0.00d) {
				destruir++;
			} else {
				break;
			}
		}
		
		if(destruir == energia.length) {
			return true;
		}
		
		return false;
	}
}
