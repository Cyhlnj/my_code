package listenner;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import main.MainFrame;
import bomb.Bomb;

public class BombListener implements MouseListener{
	Bomb[][] mineLabel;
	MainFrame mainFrame;
	private boolean isDoublePress = false;
	
	public BombListener(Bomb[][] mineLabel,MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.mineLabel = mineLabel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Bomb mineLabel = (Bomb) e.getSource();

		int row = mineLabel.getRowx();
		int col = mineLabel.getColy();

		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK
				+ InputEvent.BUTTON3_DOWN_MASK) {
			isDoublePress = true;
			doublePress(row, col);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLabel.isFlagTag() == false) {
			if (mineLabel.isExpendtag() == false) {
				mineLabel.setIcon(MainFrame.icon0);

			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(MainFrame.clickIcon);
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK
				&& mineLabel.isExpendtag() == false) {
			if (mineLabel.getRightClickCount() == 0) {
				mineLabel.setIcon(MainFrame.flagIcon);
				mineLabel.setRightClickCount(1);
				mineLabel.setFlagTag(true);
				MainFrame.bombCount--;
				mainFrame.getFaceJPanel().setNumber(MainFrame.bombCount);

			} else if (mineLabel.getRightClickCount() == 1) {
				mineLabel.setIcon(MainFrame.askIcon);
				mineLabel.setRightClickCount(2);
				mineLabel.setFlagTag(false);
				MainFrame.bombCount++;
				mainFrame.getFaceJPanel().setNumber(MainFrame.bombCount);

			} else {
				mineLabel.setIcon(MainFrame.iconBlank);
				mineLabel.setRightClickCount(0);
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		Bomb mineLabel = (Bomb) e.getSource();
		int row = mineLabel.getRowx();
		int col = mineLabel.getColy();
		if (isDoublePress) {
			isDoublePress = false;
			if (mineLabel.isExpendtag() == false
					&& mineLabel.isFlagTag() == false) {
				backIcon(row, col);
			} else {

				boolean isEquals = isEquals(row, col);
				if (isEquals) {
					doubleExpend(row, col);
				} else {
					backIcon(row, col);

				}

			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(MainFrame.smileIcon);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLabel.isFlagTag() == false) {
			if (MainFrame.isStart == false) {
				Bomb.lay(this.mineLabel, row, col);

				MainFrame.isStart = true;

			}
			mainFrame.getTimer().start();

			if (mineLabel.isBomb() == true) {

				bombAction(row, col);

				mineLabel.setIcon(MainFrame.bloodIcon);
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(MainFrame.faultFaceIcon);
			} else {
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(MainFrame.smileIcon);
				expand(row, col);

			}

		}

		isWin();
	}
	
	public void  bombAction(int row,int col) {
		for (int i = 0; i < mineLabel.length; i++) {
			for (int j = 0; j < mineLabel[i].length; j++) {
				if (mineLabel[i][j].isBomb()) {
					if (mineLabel[i][j].isFlagTag() == false) {
						mineLabel[i][j].setIcon(MainFrame.blackBombIcon);
					}
				}else {
						if (mineLabel[i][j].isFlagTag()) 
							mineLabel[i][j].setIcon(MainFrame.errorBombIcon);
				}
			}
		}
		mainFrame.getTimer().stop();
		for (int i = 0; i < mineLabel.length; i++) {
			for (int j = 0; j < mineLabel[i].length; j++) {
				mineLabel[i][j].removeMouseListener(this);
			}
		}
	}
	
	private void expand(int x,int y) {//展开地雷块
		int count  = mineLabel[x][y].getCountAround();
		if (mineLabel[x][y].isExpendtag() == false && mineLabel[x][y].isFlagTag() == false) {
			if (count == 0) {
				mineLabel[x][y].setIcon(MainFrame.num[count]);
				mineLabel[x][y].setExpendTag(true);
				for(int i = Math.max(0, x-1);i<=Math.min(mineLabel.length-1, x+1);i++) {
					for(int j = Math.max(0, y-1);j <= Math.min(mineLabel[x].length-1, y+1);j++) {
						expand(i, j);
					}
				}
			}else {
				mineLabel[x][y].setIcon(MainFrame.num[count]);
				mineLabel[x][y].setExpendTag(true);
			}
		}
	}
	
	private void backIcon(int i,int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(MainFrame.allrow - 1,i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(MainFrame.allcol - 1, j + 1); y++) {
				if (mineLabel[x][y].isFlagTag() == false && mineLabel[x][y].isExpendtag() == false) {
					int rightClickCount = mineLabel[x][y].getRightClickCount();
					if (rightClickCount == 2) {
						mineLabel[x][y].setIcon(MainFrame.askIcon);
					} else {
						mineLabel[x][y].setIcon(MainFrame.iconBlank);

					}
				}
			}
		}
	}
	
	private boolean isEquals(int i,int j) {
		int count = mineLabel[i][j].getCountAround();
		int flagCount = 0;
		for (int x = Math.max(0, i - 1); x <= Math.min(MainFrame.allrow - 1,i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(MainFrame.allcol - 1, j + 1); y++) {
				if (mineLabel[x][y].isFlagTag()) {
					flagCount++;
				}
			}
		}
		if (count == flagCount) {
			return true;
		}
		return false;
	}
	
	private void doublePress(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(MainFrame.allrow - 1,i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(MainFrame.allcol - 1, j + 1); y++) {
				if (mineLabel[x][y].isExpendtag() == false && mineLabel[x][y].isFlagTag() == false) {
					int rightClickCount = mineLabel[x][y].getRightClickCount();
					if (rightClickCount == 1) {
						mineLabel[x][y].setIcon(MainFrame.askPressIcon);

					} else {
						mineLabel[x][y].setIcon(MainFrame.icon0);

					}
				}
			}
		}
	}
	
	private void doubleExpend(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(MainFrame.allrow - 1,i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(MainFrame.allcol - 1, j + 1); y++) {
				if (mineLabel[x][y].isBomb()) {
					if (mineLabel[x][y].isFlagTag() == false) {
						bombAction(x, y);

					}
				} else {

					if (mineLabel[x][y].isFlagTag() == false) {
						expand(x, y);
					}

				}

			}
		}

	}
	
	private void isWin() {
		int needCount = MainFrame.allrow*MainFrame.allcol-MainFrame.allcount;
		int expendCount = 0;
		for (int i = 0; i < mineLabel.length; i++) {
			for (int j = 0; j < mineLabel[i].length; j++) {
				if (mineLabel[i][j].isExpendtag()) {
					expendCount++;
				}
			}
		}
		
		if (needCount == expendCount) {
			for (int i = 0; i < mineLabel.length; i++) {
				for (int j = 0; j < mineLabel[i].length; j++) {
					if (mineLabel[i][j].isBomb()
							&& mineLabel[i][j].isFlagTag() == false) {
						mineLabel[i][j].setIcon(MainFrame.flagIcon);
						mineLabel[i][j].setFlagTag(true);
					}

				}
			}
			mainFrame.getFaceJPanel().setNumber(0);
			mainFrame.getTimer().stop();
			for (int i = 0; i < mineLabel.length; i++) {
				for (int j = 0; j < mineLabel[i].length; j++) {
					mineLabel[i][j].removeMouseListener(this);
				}
			}
			mainFrame.getFaceJPanel().getLabelFace().setIcon(MainFrame.winFaceIcon);
			JOptionPane.showMessageDialog(mainFrame, "恭喜你成功了！");
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		Bomb mineLabel = (Bomb) e.getSource();
//		mineLabel.setIcon(MainFrame.HelloFaceIcon);
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		Bomb mineLabel = (Bomb) e.getSource();
//		mineLabel.setIcon(MainFrame.iconBlank);
	}
	
}
