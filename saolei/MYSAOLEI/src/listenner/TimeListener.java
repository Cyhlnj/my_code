/**
 * 
 */
/**
 * @author cyh
 *
 */
package listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;

public class TimeListener implements ActionListener{
	MainFrame mainFrame;
	
	public  TimeListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.timecount++;
		if (MainFrame.timecount>999) {
			MainFrame.timecount = 999;
		}
		mainFrame.getFaceJPanel().setTime(MainFrame.timecount);
	}
	
}