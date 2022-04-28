package me.lyc8503.DarkForest.GameLoop;

import java.io.Serializable;
import java.util.Random;

public class GameConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	private int mapWidth;
	private int mapHeight;
	private int galaxyNum;
	private long seed;
	public Random RNG;
	
	public GameConfig(int mapWidth, int mapHeight, int galaxyNum, long seed) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.galaxyNum = galaxyNum;
		this.seed = seed;
		RNG = new Random(seed);
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public int getGalaxyNum() {
		return galaxyNum;
	}

	public long getSeed() {
		return seed;
	}
	
	@Override
	public String toString() {
		return "==========\nMapWidth:" + mapWidth + "\nMapHeight:" + mapHeight + "\nGalaxy Number:" + galaxyNum + "\nSeed:" + seed + "\n==========";
	}
}
