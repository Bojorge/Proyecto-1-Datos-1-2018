package balasJugador;

import java.awt.Graphics2D;
import java.util.ArrayList;

import powerUP.EscudoBasico;
import powerUP.Laser;
import temporizador.Temporizador;

public class ArmaJugador {
	
	private boolean powerUP;
	
	private Temporizador temporizador;

	public ArrayList<TipoArmaJugador> armas = new ArrayList<TipoArmaJugador>();
	
	public ArmaJugador() {
		temporizador = new Temporizador();
	}
	
	public void dibujarArma(Graphics2D g) {
		for(int i = 0;i< armas.size();i++) {
			armas.get(i).dibujarBala(g);
		}
	}
		
	public void actualizarArma(double Acambio, EscudoBasico escudo) {
		for(int i = 0;i < armas.size();i++) {
			armas.get(i).actualizarBala(Acambio, escudo);
			if(armas.get(i).destruir())
				armas.remove(i);
		}
	}
	
	public void dispararBala(double BxPos,double ByPos,int Bwidth,int Bheight) {
//		hace que los disparos salgan en un mismo intervalo 
//		if(temporizador.tiempoEvento(250))
		
		//		le da posicion y tamaño a la bala y el tipo de arma depensiendo si tiene o no un powerUp
		if(isPowerUPA()) {
			armas.add(new Laser(BxPos + 22,ByPos + 15, Bwidth,Bheight,2));
		}else if(!isPowerUPA()){
			armas.add(new Ametralladora(BxPos + 22,ByPos + 15, Bwidth-3,Bheight-2,40));
		}
	}
	
	public void restablecerArmaJ() {
		armas.clear();
	}

	public boolean isPowerUPA() {
		return powerUP;
	}

	public void setPowerUPA(boolean powerUP) {
		this.powerUP = powerUP;
	}
	
	
}
