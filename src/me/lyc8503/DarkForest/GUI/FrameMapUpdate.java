package me.lyc8503.DarkForest.GUI;

import javax.swing.SwingUtilities;

public class FrameMapUpdate {
	static Thread thread;
	static int FPSLimit = 5;
	
	public static void startUpdate() {
		thread = new Thread(() -> {
			try {
				while(true) {
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							MainFrame.frameMap.update(MainFrame.frameMap.getGraphics());
						}
					});
					if(FPSLimit > 0) {
						Thread.sleep(1000 / FPSLimit);
					}else {
						Thread.sleep(1);
					}
					UPSPanelUpdate.FrameStatistic ++;
//					System.out.println("TEST");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}
}
