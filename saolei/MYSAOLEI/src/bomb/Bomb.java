package bomb;

import java.util.Random;

import javax.swing.JLabel;

import main.MainFrame;

public class Bomb extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isBomb;
	private boolean isExpend;
	private boolean flagTag;
	private int rowx;
	private int coly;
	private int countAround;
	private int rightClickCount;
	
	public Bomb(int x,int y) {
		this.rowx = x;
		this.coly = y;
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	
	public void setBomb(boolean mineTag) {
		this.isBomb = mineTag;
	}
	
	public boolean isExpendtag() {
		return isExpend;//
	}
	
	public void setExpendTag(boolean expendTag) {
		this.isExpend = expendTag;
	}
	
	public boolean isFlagTag() {
		return flagTag;
	}
	
	public void setFlagTag(boolean flagTag) {
		this.flagTag = flagTag;
	}
	
	public int getRowx() {
		return rowx;
	}
	
	public void setRowx(int rowx) {
		this.rowx = rowx;
	}
	public int getColy() {
		return coly;
	}

	public void setColy(int coly) {
		this.coly = coly;
	}

	public int getCountAround() {
		return countAround;
	}

	public void setCountAround(int countAround) {
		this.countAround = countAround;
	}

	public int getRightClickCount() {
		return rightClickCount;
	}

	public void setRightClickCount(int rightClickCount) {
		this.rightClickCount = rightClickCount;
	}

	public static void lay(Bomb[][] label,int row,int col) {
		int count = 0;
		Random random = new Random();
		while (count < MainFrame.allcount) {
			int x = random.nextInt(MainFrame.allrow);
			int y = random.nextInt(MainFrame.allcol);
			if (label[x][y].isBomb() == false && !(x==row&&y==col)) {
				label[x][y].setBomb(true);
				label[x][y].setCountAround(9);
				if (MainFrame.isHole == true) {
					label[x][y].setIcon(MainFrame.holeIcon);
				}
				count++;
			}
		}
		computeBomb(label);
	}
	
	public static void computeBomb(Bomb laber[][]) {
		for (int i = 0; i < laber.length; i++) {
			for (int j = 0; j < laber[i].length; j++) {
				if (laber[i][j].isBomb() == false) {
					int count = 0;
					for(int x = Math.max(0, i-1);x <= Math.min(MainFrame.allrow-1, i+1);x++) {
						for(int y = Math.max(0, j-1);y <= Math.min(MainFrame.allcol-1, j+1);y++) {
							if (laber[x][y].isBomb() == true) {
								count++;
							}
						}
					}
					laber[i][j].setCountAround(count);
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
