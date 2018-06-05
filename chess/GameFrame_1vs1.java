package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.LineBorder;

import pieces.Position;

public class GameFrame_1vs1 {

	final int width = 740;
	final int height = 640;
	JFrame gameFrame;
	Board_1 board;
	JPanel[][] square = new JPanel[8][8];
	JPanel chessBoard;
	ImagePanel[][] imgPan = new ImagePanel[8][8];
	JPanel playSpectator;
	JTextArea logTextScreen;

	Font myfont = new Font("NanumGothic", Font.BOLD, 12);
	public void addMovelog(JTextArea area, String log){
		area.append(log + "\n");
		area.setCaretPosition(area.getDocument().getLength());
	}
	
	public GameFrame_1vs1() {
		gameFrame = new JFrame("Chess - 1vs1");
		gameFrame.setSize(width, height);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setLayout(new BorderLayout());

		playSpectator = new JPanel();
		playSpectator.setLayout(new GridLayout(1, 2));
		
		logTextScreen = new JTextArea(5, 10);
		logTextScreen.setFont(myfont);
		JScrollPane textScrollPane = new JScrollPane(logTextScreen);
		logTextScreen.append("WHITE Team의 차례!"); //초기 서순
		playSpectator.add(textScrollPane);

		gameFrame.add(playSpectator, BorderLayout.EAST);
		playSpectator.setVisible(true);

		JPanel chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(8, 8));

		board = new Board_1();

		// drawBoard();
		boolean painter = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				square[i][j] = new JPanel();
				square[i][j].putClientProperty("column", i);
				square[i][j].putClientProperty("row", j);
				if (painter) {
					square[i][j].setBackground(new Color(180, 120, 50));
					square[i][j].setBorder(new LineBorder(new Color(180, 120, 50), 5));
					painter = false;
				} else {
					square[i][j].setBackground(new Color(240, 220, 200));
					square[i][j].setBorder(new LineBorder(new Color(240, 220, 200), 5));
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
					square[i][j].add(imgPan[i][j], BorderLayout.CENTER);
				} catch (NullPointerException err) {
				}
			}
		}
		gameFrame.add(chessBoard, BorderLayout.CENTER);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

	}

	public void change() {
		boolean painter = false;
		if (board.curPiece != null) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (painter) {
						square[i][j].setBackground(new Color(180, 120, 50));
						square[i][j].setBorder(new LineBorder(new Color(180, 120, 50), 5));
					} else {
						square[i][j].setBackground(new Color(240, 220, 200));
						square[i][j].setBorder(new LineBorder(new Color(240, 220, 200), 5));
					}
					if (board.curPiece.getMovement(board, board.curPiecePos).contains(new Position(i, j))) {
						square[i][j].setBackground(new Color(255, 140, 30));
					}
					painter = painter ? false : true;
				}
				painter = painter ? false : true;
			}
			square[board.curPiecePos.getX()][board.curPiecePos.getY()]
					.setBorder(new LineBorder(new Color(255, 140, 30), 5));
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (painter) {
						square[i][j].setBackground(new Color(180, 120, 50));
						square[i][j].setBorder(new LineBorder(new Color(180, 120, 50), 5));
					} else {
						square[i][j].setBackground(new Color(240, 220, 200));
						square[i][j].setBorder(new LineBorder(new Color(240, 220, 200), 5));
					}
					painter = painter ? false : true;
				}
				painter = painter ? false : true;
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getPiece(i, j) != null) {
					BufferedImage temp = board.getPiece(i, j).getImg();
					imgPan[i][j].setImage(temp);
				} else {
					imgPan[i][j].setImage(null);
				}
				imgPan[i][j].repaint();
			}
		}

	}

	public void showPopUp(String msg) {
		JOptionPane.showMessageDialog(null, msg, "System", JOptionPane.INFORMATION_MESSAGE);
	}
}
