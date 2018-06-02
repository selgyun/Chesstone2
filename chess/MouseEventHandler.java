package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import pieces.Position;

public class MouseEventHandler implements MouseListener {
	Board_1 board;
	GameFrame_1vs1 gameFrame1;

	public MouseEventHandler(Board_1 board, GameFrame_1vs1 gameFrame) {
		this.board = board;
		this.gameFrame1 = gameFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel curSquare = (JPanel) e.getSource();
		int curY = (int) curSquare.getClientProperty("column");
		int curX = (int) curSquare.getClientProperty("row");
		Position curPos = new Position(curX, curY);
		System.out.println(curX+" "+curY+"Clicked");
		if (board.curPiece == null) {
			if (board.getPiece(curX, curY).getColor() == board.turn) {
				board.curPiece = board.getPiece(curX, curY);
				board.curPiecePos = curPos;
			}
		} else {
			ArrayList<Position> moveable = board.getPiece(curX, curY).getMovement(board, board.curPiecePos);
			if (moveable.contains(curPos)) {
				board.Move(board.curPiecePos, curPos);
			} else {
				board.curPiece = null;
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
