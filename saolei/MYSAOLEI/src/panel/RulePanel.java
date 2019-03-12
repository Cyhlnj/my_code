package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.MainFrame;

public class RulePanel extends JDialog{
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private Box box1;
	private Box box2;
	private Box box3;
	private JPanel panelT;
	RulePanel rule = null;
	
	public RulePanel(MainFrame mainFrame) {
		super(mainFrame);
		rule = this;
		this.setTitle("操作说明");
		this.add(getPanel());
		this.setSize(new Dimension(200, 245));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	
	private JPanel getPanel() {
		JPanel panel = new JPanel();
		label1 = new JLabel("<html>一、左键单击雷区单元:<br>展开当前雷块</html>");
		label2 = new JLabel("<html>二、右键单击雷区单元:<br>单击一次标旗，两次标为问号，三次还原初始空白雷块</html>");
		label3 = new JLabel("<html>三、左右键同时点击雷区单元:<br>如果该雷区为空白区域，则界面无反应;如果该雷区下有显示数字，且周围区域内地雷已探完，则打开安全的雷区</html>");
		box1 = Box.createHorizontalBox();
		box1.add(label1);
		box2 = Box.createHorizontalBox();
		box2.add(label2);
		box3 = Box.createHorizontalBox();
		box3.add(label3);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rule.dispose();
			}
		});
		JPanel panel2 = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(box1);
		panel.add(box2);
		panel.add(box3);
		panel2.add(button);
		panel.add(panel2);
		
		Border border = BorderFactory.createEtchedBorder();
		panel.setBorder(border);
		
		panelT = new JPanel(new BorderLayout());
		Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		panelT.add(panel);
		panelT.setBorder(border2);
		return panelT;
	}
	
	
}
