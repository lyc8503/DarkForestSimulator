package me.lyc8503.DarkForest.GUI;

import java.util.Vector;

import me.lyc8503.DarkForest.GameLoop.Planet;
import me.lyc8503.DarkForest.Start.Start;

public class FrameDetailUpdate {
	static Thread thread;
	static long mouseLocX;
	static long mouseLocY;
	
	public static void startUpdate() {
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					MainFrame.detailArea.setText(" Û±ÍŒª÷√: " + mouseLocX + " , " + mouseLocY + "\n");
					
					Vector<Planet> showList = new Vector<Planet>();
					for(Planet planet : Start.map.planets) {
						long distance = 999;
						distance = (long) Math.sqrt(Math.pow(Math.abs(mouseLocX - planet.getLocationX()), 2) + Math.pow(Math.abs(mouseLocY - planet.getLocationY()), 2));
						if(distance < 10) {
							showList.addElement(planet);
						}
					}
					
					StringBuilder str = new StringBuilder();
					for(Planet planet : showList) {
						str.append(planet.toString() + "\n\n");
					}
					
					MainFrame.detailArea.setText(MainFrame.detailArea.getText() + "\n" + str.toString());
				}
			}
		});
		
		thread.start();
	}
}
