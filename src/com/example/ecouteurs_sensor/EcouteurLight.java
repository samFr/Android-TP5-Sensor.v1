package com.example.ecouteurs_sensor;


//*avec simulateur
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
//*/

/*sans simulateur
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
*/

import com.example.android_tp5_sensor.MainActivity;

public class EcouteurLight implements SensorEventListener {

	MainActivity activity;
	
	public EcouteurLight(MainActivity ma)
	{
		this.activity = ma;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		activity.light.setText("Light : " + arg0.values[0]);
	}

}
