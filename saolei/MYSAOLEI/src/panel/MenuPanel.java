
/**
 * @author cyh
 *
 */
package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.MainFrame;

public class MenuPanel extends JMenuBar {

	private static final long serialVersionUID = 1L;
	JMenu menuGame = new JMenu("游戏(G)");
	JMenu menuHelp = new JMenu("帮助(H)");
	JMenuItem Start = new JMenuItem("开始");
	JMenu Level = new JMenu("难度");
	JMenuItem primary = new JMenuItem("初级");
	JMenuItem middle = new JMenuItem("中级");
	JMenuItem senior = new JMenuItem("高级");
	JMenuItem rule = new JMenuItem("操作说明");
	JMenuItem Hole = new  JMenuItem("无敌开关");
	JMenuItem about = new JMenuItem("关于扫雷");
	JMenuItem exit = new JMenuItem("退出");
	MainFrame mainFrame;
	
	public MenuPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}
	
	private void init() {
		menuGame.setMnemonic('G');
		menuHelp.setMnemonic('H');
		menuGame.add(Start);
		menuGame.addSeparator();
		menuGame.add(Level);
		Level.add(primary);
		Level.addSeparator();
		Level.add(middle);
		Level.addSeparator();
		Level.add(senior);
		menuGame.addSeparator();
		menuGame.add(exit);
		menuHelp.add(rule);
		menuHelp.addSeparator();
		menuHelp.add(Hole);
		menuHelp.addSeparator();
		menuHelp.add(about);
		
		Start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.reStartGame();
			}
		});
		
		primary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.allcount = 10;
				MainFrame.allrow = 9;
				MainFrame.allcol = 9;
				mainFrame.reStartGame();
				
			}
		});
		
		middle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.allcount = 40;
				MainFrame.allrow = 16;
				MainFrame.allcol = 16;
				mainFrame.reStartGame();
			}
		});
		
		senior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.allcount = 99;
				MainFrame.allrow = 16;
				MainFrame.allcol = 30;
				mainFrame.reStartGame();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutPanel(mainFrame);
			}
		});
		
		Hole.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainFrame.isHole == false) {
					MainFrame.isHole = true;
				}else {
					MainFrame.isHole = false;
				}
				
			}
		});
		
		rule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RulePanel(mainFrame);
			}
		});
		
		this.add(menuGame);
		this.add(menuHelp);
		
		
	}
	
	
	
	
	
}