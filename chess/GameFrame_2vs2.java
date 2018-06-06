package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import pieces.Position;

public class GameFrame_2vs2 {
	private static final long serialVersionUID = 1L;

	final int width = 840;
	final int height = 840;
	JFrame gameFrame;
	Board_2 board;
	JPanel[][] square = new JPanel[14][14];
	JPanel chessBoard;
	JPanel playSpectator;
	JTextArea logTextScreen;
	JTextPane turnScreen;
	StyledDocument doc;
	ImagePanel[][] imgPan = new ImagePanel[14][14];

	Font logFont = new Font("NanumGothic", Font.BOLD, 12);
	Font turnScreenFont = new Font("NanumGothic", Font.BOLD, 15);

	public void loadNewFont(String fontDir){
		try {
		    turnScreenFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontDir)).deriveFont(24f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontDir)));
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
	}

	
	public void changeTurnScreen(String turnStr, StyledDocument doc, Style textStyle) {
		try {
			doc.insertString(doc.getLength(), "WHITE TeamÀÇ Â÷·Ê!", textStyle);
		} catch (BadLocationException e) {
		}
	}

	Color foreColor = new Color(230,245,247);
	Color backColor = new Color(81,191,181);
	
	public GameFrame_2vs2() {
		gameFrame = new JFrame("Chess - 2vs2");
		gameFrame.setSize(width, height);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setLayout(new BorderLayout());

		playSpectator = new JPanel();
		playSpectator.setLayout(new BoxLayout(playSpectator, BoxLayout.Y_AXIS));

		logTextScreen = new JTextArea(5, 10);
		logTextScreen.setFont(logFont);
		JScrollPane textScrollPane = new JScrollPane(logTextScreen);
		textScrollPane.setPreferredSize(new Dimension(150, 500));
		logTextScreen.append("°ÔÀÓ½ÃÀÛ!!\n"); // ï¿½Ê±ï¿½ ï¿½ï¿½ï¿½ï¿½
		playSpectator.add(textScrollPane);

		turnScreen = new JTextPane();
		turnScreen.setBackground(backColor);
		loadNewFont("fonts\\koverwatch.ttf");
		turnScreen.setFont(turnScreenFont);
		doc = turnScreen.getStyledDocument();
		Style textStyle = turnScreen.addStyle("TextStyle", null);
		StyleConstants.setForeground(textStyle, foreColor);
		turnScreen.setEditable(false);
		turnScreen.setPreferredSize(new Dimension(150, 50));

		changeTurnScreen("WHITE TeamÀÇ Â÷·Ê!", doc, textStyle);

		playSpectator.add(turnScreen);
		gameFrame.add(playSpectator, BorderLayout.EAST);
		playSpectator.setVisible(true);

		JPanel chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(14, 14));

		board = new Board_2();

		boolean painter = true;
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				square[i][j] = new JPanel();
				square[i][j].putClientProperty("column", i);
				square[i][j].putClientProperty("row", j);
				if (!Position.inRange(i, j)) {
					square[i][j].setBackground(Color.GRAY);
					painter = !painter;
				} else if (painter) {
					square[i][j].setBackground(Color.YELLOW);
					square[i][j].setBorder(new LineBorder(Color.YELLOW, 5));
					painter = false;
				} else {
					square[i][j].setBackground(Color.ORANGE);
					square[i][j].setBorder(new LineBorder(Color.ORANGE, 5));
					painter = true;
				}
				// square[i][j].addMouseListener(new MouseEventHandler(board,
				// this));
				chessBoard.add(square[i][j]);
			}
			painter = painter ? false : true;
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

		gameFrame.add(chessBoard, BorderLayout.CENTER);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

	}

	public void change() {
		boolean painter = false;
		if (board.curPiece != null) {
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
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
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
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

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
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

	public void addMovelog(JTextArea area, String log) {
		area.append(log + "\n");
		area.setCaretPosition(area.getDocument().getLength());
	}
}
