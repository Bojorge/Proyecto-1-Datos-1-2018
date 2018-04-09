package mapaBitsLCD;

public class ListaDE {
//	necesito un metodo para cambiar el tipo de dato que va a manejar la lista
	
	public class Nodo {
		Object objeto;
		Nodo siguiente,anterior;
	}
	
	private Nodo cabeza;
	
	public ListaDE() {
		cabeza = null;
	}
	
	public int cantidad() {
		int cuantos = 0;
		Nodo actual = cabeza;
		while(actual != null) {
			actual = actual.siguiente;
			cuantos++;
		}
		return cuantos;
	}
	
	public void insertar(int indice, Object x) {
		if(indice <= cantidad()+1) {
			Nodo nuevo = new Nodo();
			nuevo.objeto = x;
			if(indice == 1) {
				nuevo.siguiente = cabeza;
				if(cabeza !=null)
					cabeza.anterior = nuevo;
				cabeza = nuevo;
			}else {
				if(indice == cantidad()+1) {
					Nodo actual = cabeza;
					while(actual.siguiente != null) {
						actual = actual.siguiente;
					}
					actual.siguiente = nuevo;
					nuevo.anterior = actual;
					nuevo.siguiente = null;
					/*
					 * actual.siguiente = nuevo;
					nuevo.anterior = actual;
					nuevo.siguiente = null;
					 */
				}else {
					Nodo actual = cabeza;
					for(int i = 0;i <= indice-1;i ++)
						actual = actual.siguiente;
					Nodo proximo = actual.siguiente;
					actual.siguiente = nuevo;
					nuevo.anterior = actual;
					nuevo.siguiente = proximo;
					proximo.anterior = nuevo;
				}
			}
		}	
	}
	
	public Object getElemento(int indice) {//////////
		if(indice <= cantidad()) {
			Object elemento;
			if(indice == 0) {
				elemento = cabeza.objeto;
			}else {
				Nodo actual;
				actual = cabeza;
				for(int i = 0;i <= indice-1;i ++)
					actual = actual.siguiente;
				elemento = actual.objeto;
			}
			return elemento;
		}else 
			return null;
	
	}

	
	public Object extraer(int indice) {///////
		if(indice <= cantidad()) {
			Object elemento;
			if(indice == 1) {
				elemento = cabeza.objeto;
//				cabeza = cabeza.siguiente;
//				if(cabeza != null)
//					cabeza.anterior = null;
			}else {
				Nodo actual;
				actual = cabeza;
				for(int i = 0;i <= indice-1;i ++)
					actual = actual.siguiente;
				elemento = actual.objeto;
//				Nodo proximo = actual.siguiente;
//				actual.siguiente = proximo.siguiente;
//				Nodo next = proximo.siguiente;
//				if(next != null)
//					next.anterior = actual;
//				elemento = proximo.objeto;
			}
			return elemento;
		}
		else 
			return null;
	}
	
	public void borrar(int indice) {
		if(indice <= cantidad()) {
			if(indice == 1) {
				cabeza = cabeza.siguiente;
				if(cabeza != null)
					cabeza.anterior = null;
			}else {
				Nodo actual = cabeza;
				for(int i = 0;i <= indice-1;i ++)
					actual = actual.siguiente;
				Nodo proximo = actual.siguiente;
				proximo.anterior = actual.anterior;
//				actual.siguiente = proximo;
//				if(proximo != null)
//					proximo.anterior = actual;
			}
		}
	}
	
	public void intercambiar(int indiceA, int indiceB) {
		if(indiceA <= cantidad() && indiceB <= cantidad()) {
			Nodo actualA = cabeza;
			for(int i = 0;i < indiceA;i ++)
				actualA = actualA.siguiente;
			Nodo actualB = cabeza;
			for(int i = 0;i < indiceB;i ++)
				actualB = actualB.siguiente;
			Object aux = actualA.objeto;
			actualA.objeto = actualB.objeto;
			actualB.objeto = aux;
		}
	}
	
	public boolean vacia() {
		if(cabeza == null)
			return true;
		else
			return false;
	}

}





















