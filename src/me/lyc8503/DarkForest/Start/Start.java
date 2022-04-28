package me.lyc8503.DarkForest.Start;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import me.lyc8503.DarkForest.GUI.MainFrame;
import me.lyc8503.DarkForest.GUI.StartFrame;
import me.lyc8503.DarkForest.GameLoop.GameMap;
import me.lyc8503.DarkForest.GameLoop.MainThread;
import me.lyc8503.DarkForest.Utilities.FileUtilities;

public class Start {
	public static final String version = "Alpha 0.1";
	public static GameMap map;
	
	public static void main(String args[]) {
		System.out.println("DarkForestSimulator by lyc8503");
		JOptionPane.showMessageDialog(null, "�ڰ�ɭ��ģ���� By lyc8503\nBug Report : lyc8503@gmail\nVersion: " + version, "DarkForestSimulator - Welcome", JOptionPane.PLAIN_MESSAGE);
		map = StartFrame.start();
		System.out.println(map);
		System.out.println("Done!");
		MainFrame.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainThread.startThread();
	}
	
	
	public static void exit() {
		System.out.println("Stopping...");
		MainThread.stopThread();
		MainFrame.frameDetail.dispose();
		MainFrame.frameInfo.dispose();
		MainFrame.frameMap.dispose();
		MainFrame.mainControl.dispose();
		boolean saveSuccess = true;
		String output = null;
		do {
			saveSuccess = true;
			int i = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��ͼ�浵?", "Stopping...", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("��ѡ�񱣴�Ŀ¼");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showSaveDialog(null);
				File dic = fileChooser.getSelectedFile();
				try {
					output = FileUtilities.save(dic);
				} catch (Exception e) {
					saveSuccess = false;
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "�����ļ�ʱ�������쳣:\n" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				System.exit(0);
			}
		}while(!saveSuccess);
		JOptionPane.showMessageDialog(null, "�ļ��Ѿ�������:\n" + output, "Success", JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}
