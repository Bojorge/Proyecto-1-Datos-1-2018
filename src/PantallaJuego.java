

import java.awt.Canvas;
import java.awt.Graphics2D;


public class PantallaJuego extends MaquinaEstadosSuper {
	
	private Jugador jugador;
	private boolean powerUp;
	
	public boolean isPowerUp() {
		return powerUp;
	}

	public void setPowerUp(boolean powerUp) {
		this.powerUp = powerUp;
	}

	
	public PantallaJuego(MaquinaDeEstados stateMachine){
		super(stateMachine);
		jugador = new Jugador((double)Facade.WIDTH/2,(double)Facade.HEIGHT-75,50,50);
		
	}
	
	@Override
	public void actualizarMESup(double delta) {
		jugador.actualizarJugador(delta); 
		
		
	}
	
	@Override
	public void dibujarMESup(Graphics2D g) {
		jugador.dibujarJugador(g);
		
	}

	@Override
	public void inicializarMESup(Canvas canvas) {
		canvas.addKeyListener(jugador);
	}

}
