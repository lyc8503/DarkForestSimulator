package me.lyc8503.DarkForest.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import me.lyc8503.DarkForest.GameLoop.GameTickTimer;
import me.lyc8503.DarkForest.GameLoop.Planet;
import me.lyc8503.DarkForest.Start.Start;
import me.lyc8503.DarkForest.GUI.FrameMapUpdate;

public class MainFrame {
	public static JFrame mainControl;
	public static JFrame frameDetail;
	public static JFrame frameMap;
	public static JFrame frameInfo;
	public static Vector<String> msgVec;
	
	public static JPanel mapPanel;
	public static JScrollPane scrollPane;
	public static JLabel FPSLabel;
	public static JLabel UPSLabel;
	public static JPanel FPS_UPSPanel;
	public static JTextArea detailArea;
	public static void start() {
		msgVec = new Vector<String>();
		mainControl = new JFrame("Control Panel(关闭此窗口将结束程序)");
		mainControl.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				Start.exit();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		mainControl.setResizable(false);
		frameDetail = new JFrame("Detail(在\"Your Universe\"点击以查看星球详细信息)");
		frameDetail.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frameDetail.setResizable(false);
		frameMap = new JFrame("Your Universe");
		frameMap.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frameInfo = new JFrame("Console");
		frameInfo.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frameInfo.setResizable(false);
		
		
		//MainControl Part
		JTabbedPane tabbedPaneMainControl = new JTabbedPane();
		JPanel settings = new JPanel();
		JCheckBox gametickLimit = new JCheckBox("限制每秒更新:", true);
		JTextField gametickPerSecond = new JTextField("100");
		JCheckBox frapsLimit = new JCheckBox("限制帧数(Recommend):", true);
		JTextField FPS = new JTextField("5");
		
		frapsLimit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!frapsLimit.isSelected()) {
					FrameMapUpdate.FPSLimit = -1;
				}else {
					try {
						FrameMapUpdate.FPSLimit = Integer.parseInt(FPS.getText());
					}catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(settings, "错误的输入!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		FPS.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				if(!frapsLimit.isSelected()) {
					FrameMapUpdate.FPSLimit = -1;
				}else {
					try {
						FrameMapUpdate.FPSLimit = Integer.parseInt(FPS.getText());
					}catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(settings, "错误的输入!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		gametickPerSecond.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				if(!gametickLimit.isSelected()) {
					GameTickTimer.expectUPS = -1;
				}else {
					try {
						GameTickTimer.expectUPS = Integer.parseInt(gametickPerSecond.getText());
					}catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(settings, "错误的输入!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		gametickLimit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gametickLimit.isSelected()) {
					GameTickTimer.expectUPS = -1;
				}else {
					try {
						GameTickTimer.expectUPS = Integer.parseInt(gametickPerSecond.getText());
					}catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(settings, "错误的输入!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		settings.setLayout(new GridLayout(2, 2));
		settings.add(gametickLimit);
		settings.add(gametickPerSecond);
		settings.add(frapsLimit);
		settings.add(FPS);
		tabbedPaneMainControl.addTab("Settings", settings);

		
		JPanel display = new JPanel();
		JButton showframeMap = new JButton("显示地图窗口");
		JButton showframeDetail = new JButton("显示详细信息");
		JButton showframeInfo = new JButton("显示控制台");
		JButton help = new JButton("帮助");
		
		display.setLayout(new GridLayout(4, 1));
		display.add(showframeMap);
		showframeMap.addActionListener((ActionEvent e) -> {
			frameMap.setVisible(true);
		});
		
		display.add(showframeDetail);
		showframeDetail.addActionListener((ActionEvent e) -> {
			frameDetail.setVisible(true);
		});
		
		display.add(showframeInfo);
		showframeInfo.addActionListener((ActionEvent e) -> {
			frameInfo.setVisible(true);
		});
		
		display.add(help);
		help.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(frameInfo, "黑暗森林模拟器 " + Start.version + " by lyc8503\n现在你已经创建了一个新的宇宙!\n每一个星球由数个参数组成(人口.科技.环境等)\n可以在弹控制窗口更改游戏刻/帧数设置\n可以在宇宙窗口中看到星球的分布与动态\n命令行窗口会有游戏的详细信息\n也可以将鼠标在星球上点击来查看星球详细信息\n关闭控制窗口将会退出并保存存档\nTake it easy and have fun!", "Help", JOptionPane.PLAIN_MESSAGE);
		});
		
		tabbedPaneMainControl.addTab("Display", display);
		
		
		FPS_UPSPanel = new JPanel();
		FPS_UPSPanel.setLayout(new GridLayout(2, 1));
		FPSLabel = new JLabel();
		UPSLabel = new JLabel();
		FPS_UPSPanel.add(FPSLabel);
		FPS_UPSPanel.add(UPSLabel);
		tabbedPaneMainControl.add("FPS & UPS Static", FPS_UPSPanel);
		UPSPanelUpdate.startThread();
		
		mainControl.getContentPane().add(tabbedPaneMainControl);
		mainControl.setSize(400, 200);
		mainControl.setVisible(true);
		
		//FrameMap
		mapPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics graphics) {
				if(graphics != null) {
					graphics.drawLine((int) FrameDetailUpdate.mouseLocX - 5, (int) FrameDetailUpdate.mouseLocY, (int) FrameDetailUpdate.mouseLocX + 5, (int) FrameDetailUpdate.mouseLocY);
					graphics.drawLine((int) FrameDetailUpdate.mouseLocX, (int) FrameDetailUpdate.mouseLocY - 5, (int) FrameDetailUpdate.mouseLocX, (int) FrameDetailUpdate.mouseLocY + 5);
					for(Planet planet: Start.map.planets) {
						graphics.drawOval((int) planet.getLocationX(), (int) planet.getLocationY(), 5, 5);
						if (planet.getPopulation() > 0) {
							graphics.fillOval((int) planet.getLocationX(), (int) planet.getLocationY(), 5, 5);
						}
					}
					
					graphics.finalize();
				}
			}
			
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(Start.map.config.getMapWidth(), Start.map.config.getMapHeight());
			}
		};
		
		mapPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				FrameDetailUpdate.mouseLocX = e.getX();
				FrameDetailUpdate.mouseLocY = e.getY();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

		});
		 
		scrollPane = new JScrollPane(mapPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frameMap.getContentPane().add(scrollPane);
		frameMap.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension size = frameMap.getSize();
				size.height += 20;
				size.width += 20;
				scrollPane.setSize(size);
				try {
					scrollPane.update(scrollPane.getGraphics());
				}catch (Exception e2) {
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		frameMap.getContentPane().add(scrollPane);
		frameMap.setSize(520, 520);
		frameMap.setLocation(500,0);
		FrameMapUpdate.startUpdate();
		frameMap.setVisible(true);
		
		//Fame Info
		frameInfo.setSize(500, 300);
		frameInfo.setLocation(0, 500);
		JTextArea msgField = new JTextArea();
		msgField.setEditable(false);
		msgField.setSize(500, 300);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
						StringBuilder str = new StringBuilder();
						str.append("游戏的相关信息将会在这里显示 :)\n");
						str.append("开始时发展可能较慢,可以调节每秒更新次数加速\n");
						for(String msg : msgVec) {
							str.append(msg + "\n");
						}
						msgField.setText(str.toString());
						if(msgVec.size() > 100) {
							msgVec.remove(0);
						}
					}catch (Exception e) {
						//Ignore
					}
				}
			}
		}).start();
		frameInfo.getContentPane().add(new JScrollPane(msgField));
		frameInfo.setVisible(true);
		
		detailArea = new JTextArea();
		frameDetail.add(new JScrollPane(detailArea));
		frameDetail.setVisible(true);
		frameDetail.setSize(500, 300);
		frameDetail.setResizable(false);
		frameDetail.setLocation(0, 200);
		FrameDetailUpdate.startUpdate();
	}
	
	public static void addMessage(String msg) {
			msgVec.add(msg);
	}
}