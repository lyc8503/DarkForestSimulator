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
		mainControl = new JFrame("Control Panel(�رմ˴��ڽ���������)");
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
		frameDetail = new JFrame("Detail(��\"Your Universe\"����Բ鿴������ϸ��Ϣ)");
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
		JCheckBox gametickLimit = new JCheckBox("����ÿ�����:", true);
		JTextField gametickPerSecond = new JTextField("100");
		JCheckBox frapsLimit = new JCheckBox("����֡��(Recommend):", true);
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
						JOptionPane.showMessageDialog(settings, "���������!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(settings, "���������!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(settings, "���������!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(settings, "���������!\n" + e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
		JButton showframeMap = new JButton("��ʾ��ͼ����");
		JButton showframeDetail = new JButton("��ʾ��ϸ��Ϣ");
		JButton showframeInfo = new JButton("��ʾ����̨");
		JButton help = new JButton("����");
		
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
			JOptionPane.showMessageDialog(frameInfo, "�ڰ�ɭ��ģ���� " + Start.version + " by lyc8503\n�������Ѿ�������һ���µ�����!\nÿһ�������������������(�˿�.�Ƽ�.������)\n�����ڵ����ƴ��ڸ�����Ϸ��/֡������\n���������洰���п�������ķֲ��붯̬\n�����д��ڻ�����Ϸ����ϸ��Ϣ\nҲ���Խ�����������ϵ�����鿴������ϸ��Ϣ\n�رտ��ƴ��ڽ����˳�������浵\nTake it easy and have fun!", "Help", JOptionPane.PLAIN_MESSAGE);
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
						str.append("��Ϸ�������Ϣ������������ʾ :)\n");
						str.append("��ʼʱ��չ���ܽ���,���Ե���ÿ����´�������\n");
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