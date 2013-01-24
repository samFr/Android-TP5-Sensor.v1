package com.example.android_tp5_sensor;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public final class Ressources {
	
	//les tableau de la map
	public static RectF[][] Map;
	public static int[][] numCaseMap;
	public static ArrayList<RectF> Walls;
	
	//information de player
	public static RectF HitboxPlayer;
	
	public static float posXPlayer, posYPlayer;
	
	public static Bitmap bille;
	public static Bitmap bitmapMap;
	
	//controleur de jeux
	public static boolean startPlay;
	
	
	//initialisation des element
	public static void Inisialized(MainActivity context){
		Map = new RectF[100][60];;
		Walls = new ArrayList<RectF>();
		numCaseMap = new int[100][60];
		posXPlayer = 400;
		posYPlayer = 250;
		
		HitboxPlayer = new RectF(posXPlayer, posYPlayer, posXPlayer+40, posYPlayer+40);
		
		startPlay = false;
		bille = BitmapFactory.decodeResource(context.getResources(),R.drawable.bille_noire, new BitmapFactory.Options());
		bitmapMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.masp_android, new BitmapFactory.Options());
	}
	
	//gestion des collision
	public static boolean Collision(RectF bille){
		for (RectF rect : Walls) {
			if(RectF.intersects(bille,rect))
			{
				boolean collise = CollisionIntern(rect, bille);
				Log.d("Chris", "" + collise);
				return collise;
				//return false;
			}
		}
		Log.d("Chris", "true ");
		return true;
	}
	
	private static boolean CollisionIntern(RectF rectMur, RectF bille){
		RectF intersect = new RectF(rectMur);
		
		if(intersect.intersect(bille)){
			
			/*
			int[] pixels = new int[(int)(intersect.width()*intersect.height())];
			
			Log.d("D", "" + (int)intersect.left + " - "  + (int)intersect.top + " - "  + (((int)intersect.width())-1) + " - "  + (((int)intersect.height())-1));
			Ressources.bitmapMap.getPixels(pixels, 0, 1, (int)intersect.left, (int)intersect.top, ((int)intersect.width())-1, ((int)intersect.height())-1);
			*/
			/*
			for (int couleur : pixels) {
				if(couleur != Color.rgb(243, 187, 90))
					return false;
			}
			*/
			
			for(int i = 0; i < (int)intersect.height(); i++){
				for(int j = 0; j < (int)intersect.width(); j++){
					if(Ressources.bitmapMap.getPixel(j,i) != Color.rgb(243, 187, 90))
						return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean CollisionX(RectF source){
		for (RectF rect : Walls) {
			if(source.intersects(rect.left, rect.right, rect.right, rect.left))
			{
				return false;
			}
			
			/*
			if(source.centerX() < rect.centerX())
			{
				if(source.right>=rect.left)
					return false;
			}else
			{
				if(source.left<=rect.right)
					return false;
			}
			*/
		}
		return true;
	}
	
	public static boolean CollisionY(RectF source){
		for (RectF rect : Walls) {
			if (Math.abs(source.centerX()-rect.centerX())<=(source.width()+rect.width())/2f && 
				Math.abs(source.centerY()-rect.centerY())<=(source.height()+rect.height())/2f)
				  return false;
			else
				  return true;
		}
		return true;
	}
	
	//dessiner la map
	public static void DrawMap(Canvas canvas, Paint paint){
		int sourceX = 0;
		int sourceY = 0;

		for(int i =0; i < 100; i++){
			for(int j = 0; j < 60; j++){
				sourceX = ((Ressources.numCaseMap[i][j] % 28)) * 8;
				sourceY = ((Ressources.numCaseMap[i][j]  / 28)) * 8;
				canvas.drawBitmap(Ressources.bitmapMap, new Rect(sourceX,sourceY,sourceX+8,sourceY+8), Ressources.Map[i][j] , paint);
			}
		}

	}
}
