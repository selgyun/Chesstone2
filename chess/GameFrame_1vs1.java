package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame_1vs1 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final int width = 640;
	final int height = 640;
	JFrame gameFrame;
	Board_1 board;
	JPanel[][] square = new JPanel[8][8];
	JPanel chessBoard;
	ImagePanel[][] imgPan = new ImagePanel[8][8];
	
	
	public GameFrame_1vs1() {
		gameFrame = new JFrame("Chess - 1vs1");
		gameFrame.setSize(width, height);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setLayout(new BorderLayout());

		JPanel chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(8, 8));

		board = new Board_1();

		boolean painter = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				square[i][j] = new JPanel();
				if (painter) {
					square[i][j].setBackground(Color.ORANGE);
					painter = false;
				} else {
					square[i][j].setBackground(Color.YELLOW);
					painter = true;
				}
				square[i][j].addMouseListener(new MouseEventHandler(board, this));
				chessBoard.add(square[i][j]);
			}
			painter = painter ? false : true;
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					imgPan[i][j] = new ImagePanel();
					imgPan[i][j].setImage(board.getPiece(i, j).getImg());
					imgPan[i][j].setPreferredSize(new Dimension(80, 80));
					square[i][j].add(imgPan[i][j]);
				} catch (NullPointerException err) {
				}
			}
		}
		
		gameFrame.add(chessBoard, BorderLayout.CENTER);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
}
