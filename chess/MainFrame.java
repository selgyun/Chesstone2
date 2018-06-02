package chess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame{
	final int width = 600;
	final int height = 600;
	JFrame frame;
	
	public MainFrame() {
		frame = new JFrame("Chess");
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		
		//frame.setLayout(new BorderLayout(10, 20));
		
		JButton StartBut_1vs1 = new JButton("1vs1");
		JButton StartBut_2vs2 = new JButton("2vs2");
		JButton ExitBut = new JButton("Exit");
		
		StartBut_1vs1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new GameFrame_1vs1();
				frame.setVisible(false);
			}
		});
		StartBut_2vs2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new GameFrame_2vs2();
				frame.setVisible(false);
			}
		});
		ExitBut.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JPanel butpanel = new JPanel();
		butpanel.add(StartBut_1vs1);
		butpanel.add(StartBut_2vs2);
		butpanel.add(ExitBut);
		
		butpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		frame.add(butpanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void showPopUp(String msg) {
		JOptionPane.showMessageDialog(null, msg, "System", JOptionPane.INFORMATION_MESSAGE);
	}
}

