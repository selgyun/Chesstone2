package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import pieces.Piece;
import pieces.Position;

public class GameFrame_2vs2 extends GameFrame{
	private static final long serialVersionUID = 1L;

	ArrayList<Piece> dead_1 = new ArrayList<Piece>();
	ArrayList<Piece> dead_2 = new ArrayList<Piece>();
	ArrayList<Piece> dead_3 = new ArrayList<Piece>();
	ArrayList<Piece> dead_4 = new ArrayList<Piece>();
	
	final int width = 1000;
	final int height = 900;
	Piece deadPiece = null;

	public GameFrame_2vs2() {
		square = new JPanel[14][14];
		imgPan = new ImagePanel[14][14];
		corpsePanel = new ImagePanel[4][16];
		
		
		setTitle("Chess - 2vs2");
		setSize(width, height);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		deadPiecesMaster = new JPanel();
		deadPiecesMaster.setLayout(new FlowLayout(FlowLayout.LEFT));
		deadPiecesMaster.setSize(20, 20);
		descriptionText = new JTextField(9);
		descriptionText.setHorizontalAlignment(JTextField.CENTER);
		descriptionText.setEditable(false);
		descriptionText.setText("Dead Pieces");

		deadPiecesPanel = new JPanel();
		deadPiecesPanel.setLayout(new GridLayout(0, 32));
		
		for (int i = 0; i < 4; i++) {
			for(int j=0; j < 16; j++) {
				try {
					corpsePanel[i][j] = new ImagePanel();
					corpsePanel[i][j].setImage(null);
					corpsePanel[i][j].setPreferredSize(new Dimension(20, 20));
					deadPiecesPanel.add(corpsePanel[i][j]);
				} catch (NullPointerException e) {
				}
			}
		}
		
		descriptionText.setBackground(new Color(185,237,193));
		descriptionText.setForeground(new Color(32,148,120));
		loadNewFont("fonts\\koverwatch.ttf", 18f);
		descriptionText.setFont(turnScreenFont);
		deadPiecesPanel.setBackground(Color.white);
		deadPiecesMaster.add(descriptionText);
		deadPiecesMaster.add(deadPiecesPanel);

		add(deadPiecesMaster, BorderLayout.SOUTH);
		
		playSpectator = new JPanel();
		playSpectator.setLayout(new BoxLayout(playSpectator, BoxLayout.Y_AXIS));

		logTextScreen = new JTextArea(5, 10);
		logTextScreen.setFont(logFont);
		JScrollPane textScrollPane = new JScrollPane(logTextScreen);
		textScrollPane.setPreferredSize(new Dimension(215, 750));
		logTextScreen.append("Game Start!!\n");
		playSpectator.add(textScrollPane);

		turnScreen = new JTextPane();
		turnScreen.setBackground(backColor);
		loadNewFont("fonts\\koverwatch.ttf", 24f);
		turnScreen.setFont(turnScreenFont);
		doc = turnScreen.getStyledDocument();
		SimpleAttributeSet textAlignCenter = new SimpleAttributeSet();
		StyleConstants.setAlignment(textAlignCenter, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), textAlignCenter, false);
		textStyle = turnScreen.addStyle("TextStyle", null);
		StyleConstants.setForeground(textStyle, foreColor);
		turnScreen.setEditable(false);
		turnScreen.setPreferredSize(new Dimension(150, 50));

		changeTurnScreen("WHITE Turn", doc, textStyle);

		playSpectator.add(turnScreen);
		add(playSpectator, BorderLayout.EAST);
		playSpectator.setVisible(true);
		
		JPanel chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(14, 14));

		board = new Board_2();

		boolean painter = false;
		for (int i = 0; i < 14; i++, painter = painter ? false : true) {
			for (int j = 0; j < 14; j++, painter = painter ? false : true) {
				square[i][j] = new JPanel();
				square[i][j].putClientProperty("column", i);
				square[i][j].putClientProperty("row", j);
				if (!Position.inRange(i, j)) {
					square[i][j].setBackground(Color.GRAY);
					square[i][j].putClientProperty("Useable", false);
				} else {
					if (painter) {
						square[i][j].setBackground(new Color(180, 120, 50));
						square[i][j].setBorder(new LineBorder(new Color(180, 120, 50), 5));
					} else {
						square[i][j].setBackground(new Color(240, 220, 200));
						square[i][j].setBorder(new LineBorder(new Color(240, 220, 200), 5));
					}
					square[i][j].putClientProperty("Useable", true);
					square[i][j].addMouseListener(new MouseEventHandler(board, this));
				}
				chessBoard.add(square[i][j]);
			}
		}

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				try {
					imgPan[i][j] = new ImagePanel();
					imgPan[i][j].setImage(board.getPiece(i, j).getImg());
					imgPan[i][j].setPreferredSize(new Dimension(60, 60));
					square[i][j].add(imgPan[i][j]);

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
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
					if (!Position.inRange(i, j))
						square[i][j].setBackground(Color.GRAY);
					else {
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
					}
					painter = painter ? false : true;
				}
				painter = painter ? false : true;
			}
			square[board.curPiecePos.getX()][board.curPiecePos.getY()]
					.setBorder(new LineBorder(new Color(255, 140, 30), 5));
		} else {
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
					if (!Position.inRange(i, j))
						square[i][j].setBackground(Color.GRAY);
					else {
						if (painter) {
							square[i][j].setBackground(new Color(180, 120, 50));
							square[i][j].setBorder(new LineBorder(new Color(180, 120, 50), 5));
						} else {
							square[i][j].setBackground(new Color(240, 220, 200));
							square[i][j].setBorder(new LineBorder(new Color(240, 220, 200), 5));
						}
					}
					painter = painter ? false : true;
				}
				painter = painter ? false : true;
			}
		}

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				if (!Position.inRange(i, j))
					continue;
				if (board.getPiece(i, j) != null) {
					BufferedImage temp = board.getPiece(i, j).getImg();
					imgPan[i][j].setImage(temp);
				} else {
					imgPan[i][j].setImage(null);
				}
				imgPan[i][j].repaint();
			}
		}

		if (deadPiece != null) {
			if(deadPiece.getColor() == WHITE)
			{
				dead_1.add(deadPiece);
			}
			else if(deadPiece.getColor() == RED)
			{
				dead_2.add(deadPiece);
			}
			else if(deadPiece.getColor() == BLACK)
			{
				dead_3.add(deadPiece);
			}
			else if(deadPiece.getColor() == GREEN)
			{
				dead_4.add(deadPiece);
			}
			deadPiece = null;
		}
		
		for (int i = 0; i < dead_1.size(); i++)
		{
			BufferedImage temp = dead_1.get(i).getImg();
			corpsePanel[0][i].setImage(temp);
			corpsePanel[0][i].repaint();
		}
		for (int i = 0; i < dead_2.size(); i++)
		{
			BufferedImage temp = dead_2.get(i).getImg();
			corpsePanel[1][i].setImage(temp);
			corpsePanel[1][i].repaint();
		}
		for (int i = 0; i < dead_3.size(); i++)
		{
			BufferedImage temp = dead_3.get(i).getImg();
			corpsePanel[2][i].setImage(temp);
			corpsePanel[2][i].repaint();
		}
		for (int i = 0; i < dead_4.size(); i++)
		{
			BufferedImage temp = dead_4.get(i).getImg();
			corpsePanel[3][i].setImage(temp);
			corpsePanel[3][i].repaint();
		}
	}

	public void showPopUp(String msg) {
		JOptionPane.showMessageDialog(null, msg, "System", JOptionPane.INFORMATION_MESSAGE);
	}

}
