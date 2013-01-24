package com.example.ecouteurs_sensor;

/*avec simulateur
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
*/

//*sans simulateur
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;
//*/

import android.graphics.Rect;
import android.graphics.RectF;

import com.example.android_tp5_sensor.MainActivity;
import com.example.android_tp5_sensor.Ressources;

public class EcouteurGravity implements SensorEventListener {

	MainActivity activity;
	boolean mouveOk = true;
	
	public EcouteurGravity(MainActivity ma)
	{
		this.activity = ma;
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		activity.orientation.setText("TBRL : (" +(int)Ressources.HitboxPlayer.top + " ," + (int)Ressources.HitboxPlayer.bottom + " ," + (int)Ressources.HitboxPlayer.right + " ," + (int)Ressources.HitboxPlayer.left + ") - Center : (" + (int)Ressources.HitboxPlayer.centerX()+", " + (int)Ressources.HitboxPlayer.centerY()+")");
		activity.gravity.setText("Gravity : " + arg0.values[0] + " - " + arg0.values[1] + " - " + arg0.values[2]);
		activity.light.setText("lecture dans wall : " + Ressources.Walls.size());
		
		float x = Ressources.posXPlayer - arg0.values[0];
		float y = Ressources.posYPlayer + arg0.values[1];
		
		
		Ressources.HitboxPlayer = new RectF(x, y, x+40, y+40);
		
		if(Ressources.Collision(Ressources.HitboxPlayer))
		{
			Ressources.posXPlayer = x;
			Ressources.posYPlayer = y;
			activity.accelerometer.setText("No Collision x");
		}else
		{
			activity.accelerometer.setText("Collision x");
		}
		
		Log.d("Chris", Ressources.posXPlayer + " - " + Ressources.posYPlayer);
		activity.renderView.postInvalidate();
		//activity.dessin.updateViewLayout(activity.renderView, null);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
