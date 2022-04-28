package me.lyc8503.DarkForest.GUI;

public class UPSPanelUpdate {
	
	public static Thread thread;
	public static long UpdateStatistic = 0;
	public static long FrameStatistic = 0;
	public static void startThread() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(1000);
						MainFrame.UPSLabel.setText("Update per Second: " + String.valueOf(UpdateStatistic));
						UpdateStatistic = 0;
						MainFrame.FPSLabel.setText("Frame per Second: " + String.valueOf(FrameStatistic));
						FrameStatistic = 0;
//						MainFrame.FPS_UPSPanel.update(MainFrame.FPS_UPSPanel.getGraphics());
//						System.out.println(UpdateStatistic);
					}
				} catch (Exception e) {
					//Ignore
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
