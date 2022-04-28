package me.lyc8503.DarkForest.GameLoop;

import java.util.Random;

import me.lyc8503.DarkForest.GUI.MainFrame;
import me.lyc8503.DarkForest.Start.Start;

public class GameTickProcess {
	private static final double liveChance = 0.000001;
	private static final long populationIncreaseTick = 1000;
	private static final double populationLimit = 0.3;
	private static final double populationPercent = 0.03;
	private static final double maxPopulation = 1000000000.0;
	private static final double disasterChance = 0.003;
	private static final long techonologyIncreaseTick = 100;
	private static final long populationToTechnology = 1000;
	private static final double technologyPercent = 0.00000000001;
	private static final double technologyRapidDevelopmentPercent = 0.00003;
	private static final double attackChance = 0.000001;
	private static final double selfDamageChance = 0.3;
	private static final long attackCooldown = 100000;
	
	private static double technologyFuction(long population) {
		return 1 / Math.pow(population, 0.3);
	}
	
	public static void process() {
		Start.map.totalTick ++;
		Random random = new Random();
		
		// Living Start
		for(Planet planet:Start.map.planets) {
			if(planet.cooldownTick > 0) {
				planet.cooldownTick --;
			}
			if(random.nextDouble() < planet.environment * liveChance && planet.population == 0 && planet.cooldownTick == 0) {
				System.out.println("[Population]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 出现了生命");
				MainFrame.addMessage("[Population]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 出现了生命");
				planet.population += 10;
			}
		}
		
		
		if(random.nextDouble() < 1.0d / populationIncreaseTick) {
			for(Planet planet:Start.map.planets) {
				// Population Increase
				if(planet.getPopulation() > 0) {
					if(!planet.livingMaxHint) {
						if((long) (planet.getPopulation() / 2) * populationPercent * planet.getEnvironment() < 1) {
							planet.setPopulation(planet.getPopulation() + 1);
						}else if((long) (planet.getPopulation() / 2) * populationPercent * planet.getEnvironment() > maxPopulation * 0.001){
							planet.setPopulation((long) (planet.getPopulation() + maxPopulation * 0.001));
						}else {
							planet.setPopulation((long) (planet.getPopulation() +  (planet.getPopulation() / 2) * populationPercent * planet.getEnvironment()));
						}
					}else {
						planet.setPopulation((long) (planet.getPopulation() + maxPopulation * Math.pow(populationLimit, 5) * planet.getEnvironment() * random.nextDouble() * random.nextDouble()));
					}
					
					if(planet.getPopulation() > maxPopulation * populationLimit * planet.getEnvironment()) {
						if(!planet.livingMaxHint) {
							planet.livingMaxHint = true;
							System.out.println("[Population]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 人口达到上限,增长缓慢");
							MainFrame.addMessage("[Population]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 人口达到上限,增长缓慢");
						}
					}
				}
				
				// Population Decrease
				if(planet.getPopulation() > maxPopulation * populationLimit) {
					if(random.nextDouble() < disasterChance) {
						System.out.println("[Population & Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 发生了灾难");
						MainFrame.addMessage("[Population & Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 发生了灾难");
						planet.setPopulation((long) (planet.getPopulation() * random.nextDouble() * populationLimit * populationLimit));
						planet.setTechonology(planet.getTechonology() * 0.85);
					}
				}
			}
		}
		

		// Technology Development
		if(random.nextDouble() < 1.0d / techonologyIncreaseTick) {
			for(Planet planet : Start.map.planets) {
				if(planet.getPopulation() > populationToTechnology && !planet.technologyStart) {
					planet.technologyStart = true;
					System.out.println("[Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 开始发展科技");
					MainFrame.addMessage("[Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 开始发展科技");
				}
				
				if(planet.technologyStart) {
					planet.setTechonology(planet.getTechonology() + planet.getPopulation() * technologyPercent * technologyFuction(planet.getPopulation()));
				}
				
				if(planet.technologyStart && random.nextDouble() < technologyRapidDevelopmentPercent) {
					System.out.println("[Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 科技快速发展");
					MainFrame.addMessage("[Technology]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 科技快速发展");
					planet.setTechonology(planet.getTechonology() + 2.5 * technologyFuction(planet.getPopulation()));
				}
				
				if(planet.getTechonology() > 1.0) {
					planet.setTechonology(1.0);
				}
			}
		}
		
		
		// Attack
		for (Planet planet : Start.map.planets) {
			planet.attackRadius = (long) (planet.getTechonology() * Start.map.size);
		}
		
		for(Planet planet : Start.map.planets) {
			for(Planet target : Start.map.planets) {
				if(planet != target) {
					if(planet.distanceMap.get(target.getId()) < planet.attackRadius && (!planet.attackList.contains(target))) {
						if(Start.map.config.RNG.nextDouble() < attackChance) {
							planet.attackList.addElement(target);
							System.out.println("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球发现");
							MainFrame.addMessage("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球发现");
						}
					}
					if(planet.distanceMap.get(target.getId()) > planet.attackRadius && planet.attackList.contains(target)) {
						planet.attackList.remove(target);
					}
				}
				
			}
		}
		
		for(Planet planet : Start.map.planets) {
			for(Planet target : planet.attackList) {
				if(target.techonology > planet.techonology + 0.2) {
					// Too high to attack
				}else if(Math.abs(target.techonology - planet.getTechonology()) <= 0.2) {
					if(Start.map.config.RNG.nextDouble() < selfDamageChance) {
						if(planet.getTechonology() > target.getTechonology()) {
							planet.setPopulation((long) (planet.getPopulation() * 0.3));
							planet.setTechonology(planet.getTechonology() * 0.7);
							target.setPopulation(0);
							target.setTechonology(0.0d);
							target.cooldownTick += attackCooldown;
							System.out.println("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
							MainFrame.addMessage("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
							System.out.println("[Attack]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 因攻击ID为" + planet.getId() + "的星球自身受到伤害");
							MainFrame.addMessage("[Attack]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 因攻击ID为" + planet.getId() + "的星球自身受到伤害");
						}else {
							planet.setPopulation(0);
							planet.setTechonology(0.0d);
							planet.cooldownTick += attackCooldown;
							target.setPopulation(0);
							target.setTechonology(0.0d);
							target.cooldownTick += attackCooldown;
							System.out.println("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
							MainFrame.addMessage("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
							System.out.println("[Attack]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 因攻击ID为" + planet.getId() + "的星球自身受到毁灭性伤害");
							MainFrame.addMessage("[Attack]ID为 " + planet.id + " 的星球在游戏刻 " + Start.map.totalTick + " 因攻击ID为" + planet.getId() + "的星球自身受到毁灭性伤害");
						}
					}else {
						target.setPopulation(0);
						target.setTechonology(0.0d);
						target.cooldownTick += attackCooldown;
						System.out.println("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
						MainFrame.addMessage("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
					}
				}else {
					target.setPopulation(0);
					target.setTechonology(0.0d);
					target.cooldownTick += attackCooldown * 2;
					System.out.println("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
					MainFrame.addMessage("[Attack]ID为 " + target.id + " 的星球在游戏刻 " + Start.map.totalTick + " 被ID为" + planet.getId() + "的星球攻击");
				}
			}
		}
	}
}
