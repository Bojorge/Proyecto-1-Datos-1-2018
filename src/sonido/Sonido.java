package sonido;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sonido implements LineListener {

	private Clip sonidoClip;
	
	public Sonido(String ruta) {
		try {
			URL url = getClass().getResource(ruta);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new Info(Clip.class, audioFormat);
			sonidoClip = (Clip) AudioSystem.getLine(info);
			sonidoClip.open(audioInputStream);
			sonidoClip.addLineListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(LineEvent event) {
		if (event.getType().equals(LineEvent.Type.STOP)) {
			sonidoClip.setFramePosition(1);
		}
	}
	
	public void detenerSonido() {
		sonidoClip.stop();
		sonidoClip.setFramePosition(1);
	}
	
	public void playSonido() {
		sonidoClip.start();
	}
	
	public void bucleSonido() {
		sonidoClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public boolean estaSonando() {
		return sonidoClip.isRunning();
	}
}
