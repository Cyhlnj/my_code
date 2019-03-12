package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import listenner.TimeListener;
import panel.AudioPlayer;
import panel.BombPanel;

import panel.FacePanel;
import panel.MenuPanel;


public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int allcount = 10;
	public static int allcol = 9;
	public static int allrow = 9;
	public static int timecount = 0;
	public static int bombCount = allcount;

	public static boolean isStart = false;
	public static boolean isHole = false;
	
	public static ImageIcon imageIcon = new ImageIcon("./image/icon.gif");
	public static Icon iconBlank = new ImageIcon("./image/blank.gif");
	public static Icon bloodIcon = new ImageIcon("./image/blood.gif");
	public static Icon icon0 = new ImageIcon("./image/0.gif");
	public static Icon clickIcon = new ImageIcon("./image/face2.gif");
	public static Icon smileIcon = new ImageIcon("./image/face0.gif");
	public static Icon faultFaceIcon = new ImageIcon("./image/face3.gif");
	public static Icon winFaceIcon = new ImageIcon("./image/face4.gif");
	public static Icon HelloFaceIcon = new ImageIcon("./image/face5.gif");
	public static Icon flagIcon = new ImageIcon("./image/flag.gif");
	public static Icon askIcon = new ImageIcon("./image/ask.gif");
	public static Icon askPressIcon = new ImageIcon("./image/ask1.gif");
	public static Icon downSmileIcon = new ImageIcon("./image/face1.gif");
	public static Icon cool = new ImageIcon("./image/face9.gif");
	public static Icon errorBombIcon = new ImageIcon("./image/error.gif");
	public static Icon blackBombIcon = new ImageIcon("./image/mine.gif");
	public static Icon holeIcon = new ImageIcon("./image/hole.gif");
	public static File file = new File("./image/bg.mp3");
	
	public static Icon[] num = new Icon[10];
	public static Icon[] time = new Icon[11];
	static {

		for (int i = 0; i < num.length; i++) {
			num[i] = new ImageIcon("./image/" + i + ".gif");
		}
		for (int j = 0; j <= num.length; j++) {
			time[j] = new ImageIcon("./image/d" + j + ".gif");
		}

	}
	
	private MenuPanel menuBar = new MenuPanel(this);
	private FacePanel facePanel = new FacePanel(this);
	private BombPanel bombPanel = new BombPanel(this);
	private AudioPlayer bg = new AudioPlayer(file);
	private TimeListener timeListener = new TimeListener(this);
	private Timer timer = new Timer(1000, timeListener);
	

	
	public MainFrame() {
		init();
		this.setIconImage(imageIcon.getImage());
		this.setTitle("扫雷");
		this.setSize(new Dimension(220, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
		bg.run();
		
	}
	
	private void init() {
		this.setJMenuBar(menuBar);
		this.add(facePanel, BorderLayout.NORTH);
		this.add(bombPanel);
		

	}
	
	public void reStartGame() {
		this.remove(facePanel);
		this.remove(bombPanel);

		MainFrame.bombCount = MainFrame.allcount;
		MainFrame.timecount = 0;
		MainFrame.isStart = false;

		facePanel = new FacePanel(this);
		bombPanel = new BombPanel(this);
		this.add(facePanel, BorderLayout.NORTH);
		this.add(bombPanel);
		this.pack();
		this.validate();
		getTimer().stop();

	}
	
	public FacePanel getFaceJPanel() {
		return facePanel;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new MainFrame();
	}

}