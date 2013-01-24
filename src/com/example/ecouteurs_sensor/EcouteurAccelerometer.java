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

public class EcouteurAccelerometer implements SensorEventListener {

	MainActivity activity;
	
	public EcouteurAccelerometer(MainActivity ma)
	{
		this.activity = ma;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		activity.accelerometer.setText(" Accelerometer : " + arg0.values[0] + " -  " + arg0.values[1] + "  - " + arg0.values[2]);
	}

}
