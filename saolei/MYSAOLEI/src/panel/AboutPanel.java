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

public class AboutPanel extends JDialog{
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	private Box box1;
	private Box box2;
	private Box box3;
	private Box box4;
	private JPanel panelT;
	AboutPanel about = null;
	
	public AboutPanel(MainFrame mainFrame) {
		super(mainFrame);
		about = this;
		this.setTitle("关于扫雷");
		this.add(getPanel());
		this.setSize(new Dimension(200, 150));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	
	private JPanel getPanel() {
		JPanel panel = new JPanel();
		label1 = new JLabel("作者：程宇浩");
		label2 = new JLabel("时间：2018年6月8日");
		label3 = new JLabel("学号：16114449");
		label4 = new JLabel("Java课程设计作业");
		box1 = Box.createHorizontalBox();
		box1.add(label1);
		box2 = Box.createHorizontalBox();
		box2.add(label2);
		box3 = Box.createHorizontalBox();
		box3.add(label3);
		box4 = Box.createHorizontalBox();
		box4.add(label4);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(box1);
		panel.add(box3);
		panel.add(box4);
		panel.add(box2);
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				about.dispose();
			}
		});
		JPanel panel1 = new JPanel();
		panel1.add(button);
		panel.add(panel1);
		Border border = BorderFactory.createEtchedBorder();
		panel.setBorder(border);
		
		panelT = new JPanel(new BorderLayout());
		Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		panelT.add(panel);
		panelT.setBorder(border2);
		return panelT;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
