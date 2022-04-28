package me.lyc8503.DarkForest.GameLoop;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Planet implements Serializable{

	private transient static final long serialVersionUID = 1L;
	int id;
	long locationX;
	long locationY;
	long population;
	double environment;
	double techonology;
	long cooldownTick;
	long attackRadius;
	Vector<Planet> attackList;
	public HashMap<Object, Double> distanceMap;
	
	boolean livingMaxHint = false;
	boolean technologyStart = false;
	
	public Planet(int id, GameConfig config) {
		this.id = id;
		locationX = config.RNG.nextInt(config.getMapWidth());
		locationY = config.RNG.nextInt(config.getMapHeight());
		population = 0;
		environment = config.RNG.nextDouble();
		techonology = 0.0d;
		attackList = new Vector<Planet>();
		distanceMap = new HashMap<Object, Double>();
		
		System.out.println("Planet " + "ID:" + id);
		System.out.println("Location: (" + locationX + " , " + locationY + ") " + "Environment:" + environment);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Planet Info:\n==========\n");
		str.append("ID: " + id + "\nLocation: (" + locationX + " , " + locationY + ")\n" + "Environment: " + environment + "\nTechnology: " + techonology + "\nPopulation: " + population + "\n");
		str.append("==========");
		return str.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getLocationX() {
		return locationX;
	}

	public void setLocationX(long locationX) {
		this.locationX = locationX;
	}

	public long getLocationY() {
		return locationY;
	}

	public void setLocationY(long locationY) {
		this.locationY = locationY;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public double getEnvironment() {
		return environment;
	}

	public void setEnvironment(double environment) {
		this.environment = environment;
	}

	public double getTechonology() {
		return techonology;
	}

	public void setTechonology(double techonology) {
		this.techonology = techonology;
	}
	
}
