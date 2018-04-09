package niveles;

import java.awt.Graphics2D;

import Inicio.Main;
import mapaBitsLCD.ListaDE;
import pantallaJuego.Jugador;
import powerUP.EscudoBasico;
import tiposDeEnemigo.Jefe;
import tiposDeEnemigo.TipoDeEnemigo;
import tiposDeEnemigo.TipoEnemigoBasico;

public class Nivel implements SuperNivel{
	
private Jefe jefe;
	
	private Jugador jugador;
	private ListaDE enEmigos = new ListaDE();
	
	private int dificultad;
	
	public Nivel(Jugador jugador, int dificultad) {
		this.jugador = jugador;
		setDificultadN(dificultad);
		
		for(int i = 0;i < getDificultadN();i ++) {
			hileras(getDificultadN());
			
		}
		
		
		
		
		
//		Cantidad y posicion de los enemigos que se van ingresando al array. Crea hileras
//		for(int y = 0;y < getDificultadN(); y++) {
			
//			for(int x = 0;x < 10; x++) {
				
//				TipoDeEnemigo ufo = new TipoEnemigoBasico(300 + (x * 50), 10 + (y * 50),25,25,"/imagenes/ufo.png");
//				enEmigos.add(ufo);
//			}
//		}
		
	}
	
//	Cantidad, tamaño, imagen y posicion de los enemigos que se van ingresando al array. Crea hileras
	public void hileras(int dificultad) {
//		String TipoEnemImg;
		
//			TipoEnemImg = "/imagenes/ufo.png";
		jefe = new Jefe((Main.WIDTH)-400, 30,1,2,"/imagenes/ufi.png");
		enEmigos.insertar(1,jefe);

		for(int y = 0;y < getDificultadN(); y++) {
			
			for(int x = 0;x < 10; x++) {
				if(dificultad ==1) {
				TipoDeEnemigo ufo = new TipoEnemigoBasico(300 + (x * 50), 10 + (y * 50),25,25);
				enEmigos.insertar(1,ufo);
				}
				else if(dificultad ==2) {
					TipoDeEnemigo ufo = new TipoEnemigoBasico(300 + (x * 30), 0 + (y * 50),1,2);
					enEmigos.insertar(1,ufo);
				}
			}
		}
	}
	
	
	

	@Override
	public void dibujarSupNiv(Graphics2D g) {
		if(enEmigos == null)
			return;
//		Se dibuja cada enemigo existente en el Array
		for(int i = 0; i < enEmigos.cantidad(); i++) {
			((TipoDeEnemigo) enEmigos.getElemento(i)).dibujarTipoEnem(g);
		}
	}

	@Override
	public void actualizarSupNiv(double SNcambio, EscudoBasico escudo) {
		if(enEmigos == null)
			return;
		
		for(int i = 0; i < enEmigos.cantidad(); i++) {
			((TipoDeEnemigo) enEmigos.getElemento(i)).actualizarTipoEnem(SNcambio, jugador, escudo);
		}
		for(int i = 0; i < enEmigos.cantidad(); i++) {
			((TipoDeEnemigo) enEmigos.getElemento(i)).choque(i, jugador, escudo, enEmigos);
		}
		
		haCambiadoLaDireccion(SNcambio);

	}

	@Override
	public void haCambiadoLaDireccion(double SNcambio) { 
		if(enEmigos == null)
			return;
		
		for(int i = 0; i < enEmigos.cantidad(); i++) {
			if(((TipoDeEnemigo) enEmigos.getElemento(i)).estaFueraDelLimite()) {
				cambiarDireccionDeEnemigos(SNcambio);
			}
		}
	}

	@Override
	public void cambiarDireccionDeEnemigos(double SNcambio) {
		for(int i = 0; i < enEmigos.cantidad(); i++) {
			((TipoDeEnemigo) enEmigos.getElemento(i)).cambiarDireccion(SNcambio);
		}
	}

	@Override
	public boolean seTerminoJuego() {
		return false;
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void reset() {
		
	}



	public int getDificultadN() {
		return dificultad;
	}



	public void setDificultadN(int dificultad) {
		this.dificultad = dificultad;
	}
	
}
