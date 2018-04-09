

public class ListaCirc {
	class Nodo{
		Object elemento;
		Nodo anterior, siguiente;
	}
	private Nodo head;
	
	public ListaCirc() {
		head=null;
	}
	
	public void insertarPrimero(Object x) {
		Nodo nuevo=new Nodo();
		nuevo.elemento=x;
		if(head==null) {
			nuevo.siguiente=nuevo;
			nuevo.anterior=nuevo;
			head=nuevo;
		}else {
			Nodo ultimo=head.anterior;
			nuevo.siguiente=head;
			nuevo.anterior=ultimo;
			head.anterior=nuevo;
			ultimo.siguiente=nuevo;
			head=nuevo;
		}
	}
	
	public void insertarUltimo(Object x) {
		Nodo nuevo=new Nodo();
		nuevo.elemento=x;
		if(head==null) {
			nuevo.siguiente=nuevo;
			nuevo.anterior=nuevo;
			head=nuevo;
		}else {
			Nodo ultimo=head.anterior;
			nuevo.siguiente=head;
			nuevo.anterior=ultimo;
			head.anterior=nuevo;
		}
	}
	
	public boolean vacia() {
		if(head==null)
			return true;
		else
			return false;
	}
	
	public Object obtener() { //no está terminado
			return head.elemento;
		}
}
