package armas;

import java.awt.Graphics2D;
//import java.util.ArrayList;
//import java.util.List;

import animacion.ListaDE;
import gestorExplosion.GestorExplosion;
import jugador.Jugador;
import powerUp.Escudo;

public class GestorBalaEnemigo {
	
//	private List<TipoDeArmaEnemigo> tiposDeArma = new ArrayList<>();
	private ListaDE tiposDeArma = new ListaDE();
	
	public void addBalaEnem(TipoDeArmaEnemigo tipoDeArma) {
//		this.tiposDeArma.add(tipoDeArma);
		this.tiposDeArma.insertar(1,tipoDeArma);
	}

	public void dibujarBalaEnem(Graphics2D g) {
//		for (TipoDeArmaEnemigo tipoDeArma : tiposDeArma) {
		for (int i=0;i<tiposDeArma.cantidad();i++) {
			((TipoDeArmaEnemigo) tiposDeArma.getElemento(i)).dibujarArmEn(g);
		}
	}
	
	public void actualizarBalaEnem(double cambio, Escudo escudo, Jugador jugador) {
//		for (int i = 0; i < tiposDeArma.size(); i++) {
//			tiposDeArma.get(i).actualizarArmEn(cambio, escudo, jugador);
//			if (tiposDeArma.get(i).choque(jugador.getRect())) {
//				GestorExplosion.crearPixelExplosion(tiposDeArma.get(i).getxPos(), tiposDeArma.get(i).getyPos());
//				tiposDeArma.remove(i);
		for (int i = 0; i < tiposDeArma.cantidad(); i++) {
			((TipoDeArmaEnemigo) tiposDeArma.getElemento(i)).actualizarArmEn(cambio, escudo, jugador);
			if (((TipoDeArmaEnemigo) tiposDeArma.getElemento(i)).choque(jugador.getRect())) {
				GestorExplosion.crearPixelExplosion(((TipoDeArmaEnemigo) tiposDeArma.getElemento(i)).getxPos(), ((TipoDeArmaEnemigo) tiposDeArma.getElemento(i)).getyPos());
				tiposDeArma.borrar(i);
				jugador.JugadorHerido();
			}
		}
	}

	public void resetGBE() {
//		tiposDeArma.clear();
		tiposDeArma.borrarTodo();
	}
	
}
