package temporizador;

public class Temporizador {

	private long TiempoPrevio;
	
	public Temporizador(){
		setTiempoPrevio(System.currentTimeMillis());
	}

	public long getTiempoPrevio() {
		return TiempoPrevio;
	}

	public void setTiempoPrevio(long currentTime) {
		this.TiempoPrevio = currentTime;
	}
	
	public void resetTemporizador(){
		TiempoPrevio = System.currentTimeMillis();
	}
	
	public boolean tiempoEvento(int tempo){
		if(System.currentTimeMillis() - getTiempoPrevio() > tempo){
			resetTemporizador();
			return true;
		}
		
		return false;
	}
	
	public boolean temporizadorListo(int timer){
		if(System.currentTimeMillis() - getTiempoPrevio() > timer){
			return true;
		}
		
		return false;
	}
	
	
}
