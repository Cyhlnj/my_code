package panel;

import java.awt.Color;
import java.awt.GridLayout;
import bomb.Bomb;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import listenner.BombListener;
import main.MainFrame;

public class BombPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Bomb[][] labels = new Bomb[MainFrame.allrow][MainFrame.allcol];
	private BombListener listener;
	private MainFrame mainFrame;
	
	public BombPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new GridLayout(MainFrame.allrow, MainFrame.allcol));
		init();
	}
	
	private void init() {
		listener = new BombListener(labels,mainFrame);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new Bomb(i, j);
				labels[i][j].setIcon(MainFrame.iconBlank);
				labels[i][j].addMouseListener(listener);
				this.add(labels[i][j]);
			}
		}
		Border borderLow = BorderFactory.createLoweredBevelBorder();

		Border borderEmpty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border borderCom1 = BorderFactory.createCompoundBorder(borderEmpty,
				borderLow);

		this.setBorder(borderCom1);
		this.setBackground(Color.LIGHT_GRAY);
	}

	
	
	

	
	
}
