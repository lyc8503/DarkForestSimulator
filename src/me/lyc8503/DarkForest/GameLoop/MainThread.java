package me.lyc8503.DarkForest.GameLoop;

import me.lyc8503.DarkForest.GUI.UPSPanelUpdate;

public class MainThread {
	public static Thread thread = new Thread(() -> {
		try {
			while(true) {
				GameTickProcess.process();
				GameTickTimer.tickFinish();
				UPSPanelUpdate.UpdateStatistic ++;
				Thread.sleep((long) GameTickTimer.idleTimeMs);
			}
		} catch (Exception e) {
			//Ignore
			e.printStackTrace();
		}
	});
	
	public static void startThread() {
		GameTickTimer.timerThread.start();
		thread.start();
	}
	
	public static void stopThread() {
		GameTickTimer.timerThread.interrupt();
		thread.interrupt();
	}
}
