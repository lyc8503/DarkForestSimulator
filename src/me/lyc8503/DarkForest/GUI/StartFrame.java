package me.lyc8503.DarkForest.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import me.lyc8503.DarkForest.GameLoop.GameConfig;
import me.lyc8503.DarkForest.GameLoop.GameMap;
import me.lyc8503.DarkForest.Start.Start;
import me.lyc8503.DarkForest.Utilities.FileUtilities;
import me.lyc8503.DarkForest.Utilities.MapGenerator;

public class StartFrame {
	public static GameMap result;
	public static boolean waitFlag = true;
	public static GameMap start() {
		JFrame frame = new JFrame("Game Start");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		System.out.println("Game Start!");
		
		JButton openFrom = new JButton("�����е�ͼ�浵");
		JButton newMap = new JButton("�����µĴ浵");
		frame.setLayout(new GridLayout(2, 1));
		frame.getContentPane().add(new JPanel() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics graphics) {
				graphics.setFont(new Font("Times New Roman", Font.BOLD, 30));
				graphics.drawString("Dark Forest Simulator", 5, 50);
				graphics.setFont(new Font("΢���ź�", Font.PLAIN, 12));
				graphics.drawString(Start.version, 235, 75);
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));
		buttonPanel.add(openFrom);
		buttonPanel.add(newMap);
		JButton help = new JButton("����");
		buttonPanel.add(help);
		frame.getContentPane().add(buttonPanel);
		frame.setSize(300, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		
		help.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(frame, "�ڰ�ɭ��ģ��������:\n���������������С˵�����塷\n���ݺڰ�ɭ�ֵķ���,���ģ�������������Ĺ�ϵ\nÿ��������һ���������\n����֮��ụ��Ӱ��\n��������ĵ�ͼ������еĴ浵�Կ�ʼ", "Help", JOptionPane.PLAIN_MESSAGE);
		});
		
		openFrom.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("��ѡ��lycUniverse�ļ�");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.showSaveDialog(frame);
			fileChooser.setFocusable(true);
			File dic = fileChooser.getSelectedFile();
			try {
				result = FileUtilities.load(dic);
				waitFlag = false;
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "��ȡ�ļ�ʱ�������쳣:\n" + e2.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		newMap.addActionListener((ActionEvent e) -> {
			System.out.println("Create new map!");
			
			JDialog dialog = new JDialog(frame, true);
			dialog.setTitle("New Map");

			GridLayout newMapLayout = new GridLayout(5, 2);
			dialog.setLayout(newMapLayout);
			
			JButton exit = new JButton("Cancel");
			exit.addActionListener((ActionEvent event1) -> {
				dialog.dispose();
			});
			JButton confirm = new JButton("Create");
			
			JTextField mapWidth = new JTextField("500");
			JTextField mapHeight = new JTextField("500");
			JTextField galaxyNum = new JTextField("50");
			JTextField mapSeed = new JTextField(String.valueOf(new Random().nextLong()));
			
			dialog.getContentPane().add(new JLabel("Map width:"));
			dialog.getContentPane().add(mapWidth);
			dialog.getContentPane().add(new JLabel("Map Height:"));
			dialog.getContentPane().add(mapHeight);
			dialog.getContentPane().add(new JLabel("Galaxy Number(�������������Ҫ�Ͼ�����ʱ��):"));
			dialog.getContentPane().add(galaxyNum);
			dialog.getContentPane().add(new JLabel("Map Seed(����Ĭ��ֵ):"));
			dialog.getContentPane().add(mapSeed);
			
			confirm.addActionListener((ActionEvent event2) -> {
				try {
					result = MapGenerator.generate(new GameConfig(Integer.parseInt(mapWidth.getText()), Integer.parseInt(mapHeight.getText()), Integer.parseInt(galaxyNum.getText()), Long.parseLong(mapSeed.getText())));
					waitFlag = false;
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(dialog, "Unexpected Expection!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					dialog.dispose();
				}
			});
			dialog.getContentPane().add(confirm);
			dialog.getContentPane().add(exit);
			
			dialog.setSize(new Dimension(600, 200));
			dialog.setResizable(false);
			dialog.setVisible(true);
			
		});
		
		
		while(waitFlag) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		frame.dispose();
		return result;
	}
}
