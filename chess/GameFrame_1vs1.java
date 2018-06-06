package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import pieces.Position;

public class GameFrame_1vs1 extends GameFrame {

	private static final long serialVersionUID = 1L;
	final int width = 860;
	final int height = 720;

	public void renewCorpses_1() {
		for (int i = 0; i < 2; i++) {
			for(int j=0; j<16; j++) {
				try {
					corpsePanel[i][j].setImage(board.deadPieces.get(i).getImg());
					corpsePanel[i][j].setPreferredSize(new Dimension(20, 20));
					deadPiecesPanel.add(corpsePanel[i][j], BorderLayout.CENTER);
				} catch (NullPointerException e) {

				}
			}
		}
	}

	public GameFrame_1vs1() {
		square = new JPanel[8][8];
		imgPan = new ImagePanel[8][8];
		corpsePanel = new ImagePanel[2][16];

		setTitle("Chess - 1vs1");
		setSize(width, height);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		deadPiecesMaster = new JPanel();
		deadPiecesMaster.setLayout(new FlowLayout(FlowLayout.LEFT));
		deadPiecesMaster.setSize(800, 100);
		descriptionText = new JTextField(10);
		descriptionText.setText("죽은 말들 표시");
		descriptionText.setVisible(true);

		deadPiecesPanel = new JPanel();
		deadPiecesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		deadPiecesPanel.setVisible(true);

		deadPiecesMaster.add(descriptionText);
		deadPiecesMaster.add(deadPiecesPanel);
		deadPiecesMaster.setVisible(true);

		add(deadPiecesMaster, BorderLayout.SOUTH);

		playSpectator = new JPanel();
		playSpectator.setLayout(new BoxLayout(playSpectator, BoxLayout.Y_AXIS));

		logTextScreen = new JTextArea(20, 15);
		logTextScreen.setFont(logFont);
		logTextScreen.setEditable(false);
		JScrollPane textScrollPane = new JScrollPane(logTextScreen);
		textScrollPane.setPreferredSize(new Dimension(200, 600));
		logTextScreen.append("게임 시작!!\n"); // 초기 서순
		playSpectator.add(textScrollPane);

		turnScreen = new JTextPane();
		turnScreen.setBackground(backColor);
		loadNewFont("fonts\\koverwatch.ttf");
		turnScreen.setFont(turnScreenFont);
		doc = turnScreen.getStyledDocument();
		SimpleAttributeSet textAlignCenter = new SimpleAttributeSet();
		StyleConstants.setAlignment(textAlignCenter, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), textAlignCenter, false);
		Style textStyle = turnScreen.addStyle("TextStyle", null);
		StyleConstants.setForeground(textStyle, foreColor);
		turnScreen.setEditable(false);
		turnScreen.setPreferredSize(new Dimension(150, 50));

		changeTurnScreen("WHITE Team의 차례!", doc, textStyle);

		playSpectator.add(turnScreen);
		add(playSpectator, BorderLayout.EAST);
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
		add(chessBoard, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

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

}
