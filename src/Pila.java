

import java.awt.image.BufferedImage;

public class Pila {
//	clase interna
	class Nodo{
		BufferedImage objeto;
		Nodo siguente;
	}
	
	private Nodo cabeza;
	
//	constructor
	public Pila() {
		cabeza = null;
	}
	
	public void insertar(BufferedImage x) {
		Nodo nuevo;
		nuevo = new Nodo();
		nuevo.objeto = x;
		if(cabeza==null) {
			nuevo.siguente = null;
			cabeza = nuevo;
		}else {
			nuevo.siguente = cabeza;
			cabeza = nuevo;
		}
	}
	
//	revisar este metodo para que sea compatible con lo que se necesita
	public Object eliminar() {
		if(cabeza != null) {
			Object elemento = cabeza.objeto;
			cabeza = cabeza.siguente;
			return elemento;
		}else {
			return null;
		}
	}
	
	public void size() {
		
	}
	
	public boolean vacia() {
		if(cabeza == null)
			return true;
		else
			return false;
	}
	
	public int cantidad() {
		int cuantos = 0;
		Nodo actual = cabeza;
		while(actual != null) {
			actual = actual.siguente;
			cuantos++;
		}
		return cuantos;
	}

}
