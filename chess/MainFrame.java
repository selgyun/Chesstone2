package chess;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class MainFrame{
	final int width = 600;
	final int height = 600;
	JFrame frame;
	
	public MainFrame() {
		frame = new JFrame("Chess");
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);

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
		
		ImageIcon icon = new ImageIcon("./Chess.jpg");
		
		JPanel butpanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(),  0,  0,  null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		StartBut_1vs1.setSize(200, 50);
		StartBut_1vs1.setLocation(200, 300);
		StartBut_1vs1.setBackground(new Color(251, 251, 208));
		StartBut_1vs1.setBorder(new LineBorder(new Color(205, 221, 244), 5));
		
		StartBut_2vs2.setSize(200, 50);
		StartBut_2vs2.setLocation(200, 380);
		StartBut_2vs2.setBackground(new Color(251, 251, 208));
		StartBut_2vs2.setBorder(new LineBorder(new Color(205, 221, 244), 5));
		
		ExitBut.setSize(200, 50);
		ExitBut.setLocation(200,460);
		ExitBut.setBackground(new Color(251, 251, 208));
		ExitBut.setBorder(new LineBorder(new Color(205, 221, 244), 5));
		
		butpanel.setBounds(0, 0, 600, 600);
		
		frame.add(StartBut_1vs1);
		frame.add(StartBut_2vs2);
		frame.add(ExitBut);
		
		frame.add(butpanel);
		
		frame.add(butpanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void showPopUp(String msg) {
		JOptionPane.showMessageDialog(null, msg, "System", JOptionPane.INFORMATION_MESSAGE);
	}
}

