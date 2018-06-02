package chess;

import javax.swing.JFrame;

public class GameFrame_2vs2 {
	final int width = 600;
	final int height = 600;
	JFrame gameFrame;
	
	public GameFrame_2vs2(){
		gameFrame = new JFrame("Chess - 2vs2");
		gameFrame.setSize(width, height);
		gameFrame.setLocationRelativeTo(null);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
}
