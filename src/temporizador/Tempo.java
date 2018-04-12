package temporizador;

public class Tempo {

	private float contador, tempoObjetivo;
	
	public Tempo(float tempoObjetivo) {
		this.tempoObjetivo = tempoObjetivo;
		this.contador = 0;
	}
	
	public void tick(double cambio) {
		if (contador <= tempoObjetivo) {
			contador += 1 * cambio;
		}
	}
	
	public boolean EventoListo() {
		if (contador >= tempoObjetivo) {
			resetTempo();
			return true;
		}
		return false;
	}
	
	private void resetTempo() {
		contador = 0;
	}
}
