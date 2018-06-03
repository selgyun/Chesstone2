package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import pieces.MT;
import pieces.Position;

public class MouseEventHandler implements MouseListener {
	Board_1 board;
	GameFrame_1vs1 gFrame;

	public MouseEventHandler(Board_1 board, GameFrame_1vs1 gameFrame) {
		this.board = board;
		this.gFrame = gameFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel curSquare = (JPanel) e.getSource();
		int curX = (int) curSquare.getClientProperty("column");
		int curY = (int) curSquare.getClientProperty("row");
		Position curPos = new Position(curX, curY);
		System.out.println(curX + " " + curY);
		if (board.curPiece == null) {
			try {
				if (board.getPiece(curPos).getColor() == board.turn) {
					board.curPiece = board.getPiece(curX, curY);
					board.curPiecePos = curPos;
					gFrame.change();
					System.out.println("Selected");
				}
			} catch (NullPointerException err) {
			}

		} else {
			//System.out.println(moveable.contains(curPos));
			if (board.curPiece.getMovement(board, board.curPiecePos).contains(curPos)) {
				board.Move(board.curPiecePos, curPos);
				gFrame.change();
				System.out.println("Moved");
			} else {
				board.curPiece = null;
				gFrame.change();
				System.out.println("Canceled");
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
