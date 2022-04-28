package me.lyc8503.DarkForest.GameLoop;

public class GameTickTimer {
	public static float UPS = 0;
	public static int tick;
	public static float idleTimeMs = 20;
	public static int expectUPS = 100;
	
	public static Thread timerThread = new Thread(() -> {
		try {
			while(true) {
				Thread.sleep(100);
				UPS = (float) (tick / 0.1);
				tick = 0;
//				idleTimeMs = 0;
				if(expectUPS < 0) {
					idleTimeMs = 0;
				}else {
					if(UPS > expectUPS) {
						idleTimeMs += 3;
					}else {
						if(idleTimeMs - 4 > 0) {
							idleTimeMs -= 4;
						}
					}
				}
//				System.out.println(UPS);
			}
		} catch (Exception e) {
			//Ignore
			e.printStackTrace();
		}
	});
	
	public static void tickFinish() {
		tick ++;
	}
}
