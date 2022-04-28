package me.lyc8503.DarkForest.GameLoop;

import java.io.Serializable;
import java.util.Vector;

public class GameMap implements Serializable{
	private static final long serialVersionUID = 1L;
	public Vector<Planet> planets;
	public GameConfig config;
	public long totalTick;
	public long size;
	
	public GameMap(GameConfig config) {
		planets = new Vector<Planet>();
		this.config = config;
		this.size = (long) Math.sqrt(config.getMapWidth() * config.getMapWidth() + config.getMapHeight() * config.getMapHeight());
	}
	
	public void addPlanet(Planet planet) {
		planets.addElement(planet);
	}
	
	
}
