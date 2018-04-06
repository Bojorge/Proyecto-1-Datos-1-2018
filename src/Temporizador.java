

public class Temporizador {
	
	private long tiempoPrevio;
	
	public Temporizador() {
		setTiempoPrevio(System.currentTimeMillis());
	}

	public long getTiempoPrevio() {
		return tiempoPrevio;
	}

	public void setTiempoPrevio(long tiempoPrevio) {
		this.tiempoPrevio = tiempoPrevio;
	}
	
	public void resetTemporizador() {
		tiempoPrevio = System.currentTimeMillis();
	}
	
	public boolean tiempoEvento(int timer) {
		if(System.currentTimeMillis() - getTiempoPrevio() > timer) {
			resetTemporizador();
			return true;
		}
		return false;
	}
	
	public boolean temporizadorListo(int timer) {
		if(System.currentTimeMillis() - getTiempoPrevio() > timer) {
			return true;
		}
		return false;
	}
}
