package me.lyc8503.DarkForest.Utilities;

import me.lyc8503.DarkForest.GameLoop.GameConfig;
import me.lyc8503.DarkForest.GameLoop.GameMap;
import me.lyc8503.DarkForest.GameLoop.Planet;

public class MapGenerator {
	public static GameMap generate(GameConfig config) {
		int id = 1;
		System.out.println("Generating...Please Wait!\nGameConfig:");
		GameMap map = new GameMap(config);
		System.out.println(config);
		for(int i = 0; i < config.getGalaxyNum(); i ++) {
			map.addPlanet(new Planet(id ++, config));
		}
		
		
		for(Planet planet: map.planets) {
			for(Planet planet2: map.planets) {
				if(planet != planet2) {
					planet.distanceMap.put(planet2.getId(), Math.sqrt(Math.pow(planet2.getLocationX() - planet.getLocationX(), 2) + Math.pow(planet2.getLocationY() - planet.getLocationY(), 2)));
				}
			}
			System.out.println(planet.distanceMap);
		}
		return map;
	}
}
