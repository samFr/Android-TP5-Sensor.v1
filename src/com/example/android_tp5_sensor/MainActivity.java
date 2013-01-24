package com.example.android_tp5_sensor;

import java.util.Random;


/*avec un simulateur android
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;
*/

//*avec un vrais android
import android.hardware.Sensor;           // sans Simulator
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
//*/


import com.example.ecouteurs_sensor.EcouteurAccelerometer;
import com.example.ecouteurs_sensor.EcouteurGravity;
import com.example.ecouteurs_sensor.EcouteurLight;
import com.example.ecouteurs_sensor.EcouteurMagnetic;
import com.example.ecouteurs_sensor.EcouteurOrientation;
import com.example.level.Level1;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;




public class MainActivity extends Activity {

	//les variable
	public TextView accelerometer = null,
					magnetic = null,
					orientation = null,
					light = null,
					gravity = null;
	
	public Button bouton = null;
	public Button stop = null;
	
    
    //le niveau
    Level1 level1;
    
	/*/le sensor
	SensorManagerSimulator sensorManager = null;
	Sensor sensor = null;
	*/
	//*sensor avec un vrais android
	SensorManager sensorManager = null;
	Sensor sensor = null;
	//*/
	
	EcouteurAccelerometer ecouteurAccelerometer = null;
	EcouteurMagnetic ecouteurrMagnetic = null;
	EcouteurGravity ecouteurGravity = null;
	EcouteurOrientation ecouteurOrientation = null;
	EcouteurLight  ecouteurLight = null;
	
	public RenderView renderView = null;
	public LinearLayout dessin = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Ressources.Inisialized(this);
		
		dessin = (LinearLayout)findViewById(R.id.dessin);
		renderView = new RenderView(this);
		dessin.addView(renderView);
		
		
		//les éléments
		this.accelerometer = (TextView)findViewById(R.id.aceelerometer);
		this.magnetic = (TextView)findViewById(R.id.magnetic);
		this.orientation = (TextView)findViewById(R.id.orientation);
		this.light = (TextView)findViewById(R.id.light);
		this.gravity = (TextView)findViewById(R.id.gravity);
		
		
		this.bouton = (Button)findViewById(R.id.bouton);
		this.stop = (Button)findViewById(R.id.stop);
		
		//initialisation de sensor
		//sensorManager = SensorManagerSimulator.getSystemService(this, Context.SENSOR_SERVICE); //avec simulator     
		sensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);      // sans Simulator 
		
		//les écouteurs
		ecouteurAccelerometer = new EcouteurAccelerometer(this);
		ecouteurrMagnetic = new EcouteurMagnetic(this);
		ecouteurLight = new EcouteurLight(this);
		ecouteurOrientation = new EcouteurOrientation(this);
		ecouteurGravity = new EcouteurGravity(this);
		
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
        StrictMode.setThreadPolicy(policy); 

		//action sur le bouton
		this.bouton.setOnClickListener(boutonClick);
		this.bouton.setOnClickListener(stopClick);
	}

	//laction de bouton
	private OnClickListener boutonClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//le sensor

			level1 = new Level1(MainActivity.this, (float)dessin.getWidth(), (float)dessin.getHeight());
			Ressources.startPlay = true;
			renderView.postInvalidate();
			
			//sensorManager.connectSimulator(); //désactiver pour une vrai android

			/*/ecouteur les sensor
			sensorManager.registerListener(ecouteurAccelerometer, sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
			sensorManager.registerListener(ecouteurrMagnetic, sensorManager.getDefaultSensor(sensor.TYPE_MAGNETIC_FIELD), sensorManager.SENSOR_DELAY_GAME);
			sensorManager.registerListener(ecouteurLight, sensorManager.getDefaultSensor(sensor.TYPE_LIGHT), sensorManager.SENSOR_DELAY_GAME);
			sensorManager.registerListener(ecouteurOrientation, sensorManager.getDefaultSensor(sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_GAME);
			*/
			sensorManager.registerListener(ecouteurGravity, sensorManager.getDefaultSensor(sensor.TYPE_GRAVITY), sensorManager.SENSOR_DELAY_GAME);
			
		}
	};
	
	
	//laction de bouton
		private OnClickListener stopClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Ressources.startPlay = false;
			}
		};
	
	
	
    // Création d'une classe interne RenderView pour gérer un affichage simple permettant 
    // de montrer que nous occupons bien tout l'écran
    public class RenderView extends View {

        public RenderView(Context context) {
            super(context);
        }

        // Dessinons sur la totalité de l'écran
        protected void onDraw(Canvas canvas) {
        	if(Ressources.startPlay)
        	{
        		canvas.drawRGB(200, 200, 220);
                Paint paint = new Paint();
                Paint paintParoit = new Paint();
                paint.setAntiAlias(true);
                paintParoit.setAntiAlias(true);

        		canvas.drawBitmap(Ressources.bitmapMap, 0, 0, paintParoit);
                //canvas.drawBitmap(Ressources.bitmapMap, new Rect(0,0,400,800), new Rect(0,0,dessin.getWidth(),dessin.getHeight()), paintParoit);
                //Ressources.DrawMap(canvas, paintParoit);
                
                // Affecter une couleur de manière aléatoire
                paint.setARGB(255, 0, 0, 50);
                paintParoit.setARGB(255,125,125,0);
                
                // Définir l'épaisseur du segment
                paint.setStrokeWidth (2);
                paintParoit.setStrokeWidth (2);
                
                //dessiner les mure
                
                for (RectF rect : Ressources.Walls) {
                	canvas.drawRect(rect, paintParoit);
    			}
    			
               
                canvas.drawRect(Ressources.HitboxPlayer, paintParoit);

                canvas.drawBitmap(Ressources.bille, new Rect(0,0,128,128), Ressources.HitboxPlayer, paintParoit);
                
                
                // Puis dessiner le cyerle dans le cavenas
                //canvas.drawCircle(Ressources.posXPlayer, Ressources.posYPlayer, 30, paint);
                   
        	}
            
        }

    }

}
