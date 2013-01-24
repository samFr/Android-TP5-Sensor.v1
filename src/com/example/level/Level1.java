package com.example.level;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.android_tp5_sensor.MainActivity;
import com.example.android_tp5_sensor.Ressources;

public class Level1 {
	private float width;
	private float heigth;
	private MainActivity context;
	
	public Level1(MainActivity contextIn, float widthIn, float heightIn){
		this.width = widthIn;
		this.heigth = heightIn;
		this.context = contextIn;
		
		//charger des donnes
		this.ChargementMap();
		this.ChargementWalls();
	}
	
	
	//chargement de la map
	public void ChargementMap()
	{
		float pasX = this.width/60f; 
        float pasY = this.heigth/100f;
        float posX = 0;
        float posY = 0;
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("level/level1/map.txt")));	

			String line;
			int k = 0;
			while ((line = br.readLine()) != null)
			{
				String[] split = line.split(",");
				for (int i = 0; i < 100; i++)
				{
					for(int j = 0; j < 60; j++){
						Ressources.Map[i][j] = new RectF(posX, posY, posX+pasX, posY+pasY);
						Ressources.numCaseMap[i][j] = Integer.parseInt(split[k]);
						posX += pasX;
		                k++;
	                }
	                //initialiser les position
	                posX = 0;
	                posY += pasY;
				}
			}
			br.close(); 
		}
		catch (Exception e)
		{
			// Let the user know what went wrong.
			
		}
	}
	
	//chargement walls
	public void ChargementWalls()
    {
        float pasX = this.width/60f; 
        float pasY = this.heigth/100f;
        float posX = 0;
        float posY = 0;
        
        try
        {
      	  	BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("level/level1/collision.txt")));	
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(",");
                for (String s : split)
                {
                    if (s.equals("0"))
                    {
                        //Road.Add(new Rectangle(posX, posY, 32, 32));
                    }
                    else
                    {
                    	Ressources.Walls.add(new RectF(posX, posY, posX+pasX, posY+pasY));
                    }
                    
                    posX += pasX;
                }
                //initialiser les position
                posX = 0;
                posY += pasY;
            }
  			br.close(); 
        }
        catch (Exception e)
        {
            // Let the user know what went wrong.
        }
    }
	
}
