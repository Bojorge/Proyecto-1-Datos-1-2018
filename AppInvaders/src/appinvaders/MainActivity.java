package appinvaders;

import android.app.Activity;
import android.app.AliasActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import appinvaders2.R;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class MainActivity2 extends AliasActivity{ //AppCompatActivity

//		esta variable permite acceder al sensor del dispositivo
		SensorManager sensorManager;
		
//		esto va a representar al sensor
		Sensor sensor;
		
//		se crea un evento que avisa cuando se mueve el dispositivo
		SensorEventListener sensorEventListener;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
//			se crea una instancia
			sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
			sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			
//			se verifica si el dispositivo cuenta con ese tipo de sensor
			if(sensor==null)
				finish();
			
			sensorEventListener=new SensorEventListener() {

				@Override
				public void onSensorChanged(SensorEvent event) {
					// TODO Auto-generated method stub
//					se obtienen de un array los ejes de posicion y movimiento x, y, z
					float x=event.values[0];
					float y=event.values[1];
					float z=event.values[2];
					
//					si se mueve a la izquierda
					if(x<-5) {
						
					}
//					si se mueve hacia la derecha
					else if(x<5) {
						
					}
						
				}

				@Override
				public void onAccuracyChanged(Sensor sensor, int accuracy) {
					// TODO Auto-generated method stub
					
				}
		
			};
		}
		
//		dentro de este metodo se inicia el evento
		private void start() {
			sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
		}
		
//		se detiene el evento
		private void stop() {
			sensorManager.unregisterListener(sensorEventListener);
		}
	}
}
