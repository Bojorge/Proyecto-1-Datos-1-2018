package gestorExplosion;

import java.awt.Graphics2D;
//import java.util.ArrayList;

import animacion.ListaDE;

public class GestorExplosion {

//	private static ArrayList<TipoDeExplosion> explosiones = new ArrayList<TipoDeExplosion>();
	private static ListaDE explosiones = new ListaDE();
	
	public void actualizarExplosion(double cambio) {
//		for (int i = 0; i < explosiones.size(); i++) {
//			explosiones.get(i).actualizarTipoExplosion(cambio);
//			if(explosiones.get(i).expDestruir()) {
//				explosiones.remove(i);
		for (int i = 0; i < explosiones.cantidad(); i++) {
			((TipoDeExplosion) explosiones.getElemento(i)).actualizarTipoExplosion(cambio);
			if(((TipoDeExplosion) explosiones.getElemento(i)).expDestruir()) {
				explosiones.borrar(i);
			}
		}
	}
	
	public static void crearPixelExplosion(double xPos, double yPos) {
		TipoDeExplosion exp = new PixelExplosion(xPos, yPos);
//		explosiones.add(exp);
		explosiones.insertar(1,exp);
	}
	
	public void dibujarExplosion (Graphics2D g) {
//		for (int i = 0; i < explosiones.size(); i++) {
//			explosiones.get(i).dibujarTipoExplosion(g);
		for (int i = 0; i < explosiones.cantidad(); i++) {
			((TipoDeExplosion) explosiones.getElemento(i)).dibujarTipoExplosion(g);
		}
	}
}
