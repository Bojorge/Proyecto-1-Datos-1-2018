package armas;

import java.awt.Graphics2D;
import java.util.ArrayList;

//import animacion.ListaDE;
import gestorExplosion.GestorExplosion;
import powerUp.Escudo;
import sonido.Sonido;
import temporizador.Temporizador;

public class ArmasJugador {

	public ArrayList<TipoArmaJugador> armax = new ArrayList<TipoArmaJugador>();
//	public ListaDE armax = new ListaDE();
	private Temporizador temporizador;
	private GestorExplosion GestorExplosion;
	private Sonido sonidoDisparo;
	
	public ArmasJugador(){
		GestorExplosion = new GestorExplosion();
		temporizador = new Temporizador();
		sonidoDisparo = new Sonido("/sonidos/shoot.wav");
	}
	
	public void dibujarArmJug(Graphics2D g){
		
		GestorExplosion.dibujarExplosion(g);
		for(int i = 0; i < armax.size(); i++){
			armax.get(i).dibujarTipArmJug(g);
//		for(int i = 0; i < armax.cantidad(); i++){
//			((TipoArmaJugador) armax.getElemento(i)).dibujarTipArmJug(g);
		}
	}
	
	@SuppressWarnings("static-access")
	public void actualizarArmJug(double cambio, Escudo escudo){
		
		GestorExplosion.actualizarExplosion(cambio);
		for(int i = 0; i < armax.size(); i++){
			armax.get(i).actualizarTipArmJug(cambio, escudo);
//		for(int i = 0; i < armax.cantidad(); i++){
//			((TipoArmaJugador) armax.getElemento(i)).actualizarTipArmJug(cambio, escudo);
			if(armax.get(i).destruirTipArmJug()) {
				GestorExplosion.crearPixelExplosion(armax.get(i).getxPos(), armax.get(i).getyPos());
				armax.remove(i);
//			if(((TipoArmaJugador) armax.getElemento(i)).destruirTipArmJug()) {
//				GestorExplosion.crearPixelExplosion(( (TipoArmaJugador) armax.getElemento(i)).getxPos(), ((TipoArmaJugador) armax.getElemento(i)).getyPos());
//				armax.borrar(i);
			}
		}
	}
	
	public void tiros(double xPos, double yPos, int ancho, int altura){
		if(temporizador.tiempoEvento(150)) {
			if (sonidoDisparo.estaSonando()) {
				sonidoDisparo.detenerSonido();
			}
			sonidoDisparo.playSonido();
			armax.add(new Ametralladora(xPos + 22, yPos + 15, ancho, altura));
//			armax.insertar(1,new Ametralladora(xPos + 22, yPos + 15, ancho, altura));
		}
	}

	public void resetArmJug() {
		armax.clear();
//		armax.borrarTodo();
	}
}
