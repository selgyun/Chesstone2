package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public abstract class GameFrame extends JFrame implements ConstDef{
	JPanel[][] square;
	JPanel chessBoard, deadPiecesMaster, deadPiecesPanel;
	ImagePanel[][] imgPan;
	ImagePanel[][] corpsePanel;
	Board_Master board;

	Color foreColor = new Color(230, 245, 247);
	Color backColor = new Color(81, 191, 181);

	JPanel playSpectator;
	JTextArea logTextScreen;
	JTextField descriptionText;
	JTextPane turnScreen;
	StyledDocument doc;
	Style textStyle;
	
	Font logFont = new Font("NanumGothic", Font.BOLD, 13);
	Font turnScreenFont = new Font("NanumGothic", Font.BOLD, 15);
	
	public void loadNewFont(String fontDir, float fontSize) {
		try {
			turnScreenFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontDir)).deriveFont(fontSize);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontDir)));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void changeTurnScreen(String turnStr, StyledDocument doc, Style textStyle) {
		try {
			doc.insertString(doc.getLength(), turnStr, textStyle);
		} catch (BadLocationException e) {
		}
	}

	public void addMovelog(JTextArea area, String log) {
		area.append(log + "\n");
		area.setCaretPosition(area.getDocument().getLength());
	}
	
	public void showPopUp(String msg) {
		JOptionPane.showMessageDialog(null, msg, "System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public abstract void change();

}
