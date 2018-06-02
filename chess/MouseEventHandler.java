package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

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
		ArrayList<Position> moveable;
		int curX = (int) curSquare.getClientProperty("column");
		int curY = (int) curSquare.getClientProperty("row");
		Position curPos = new Position(curX, curY);
		System.out.println(curX + " " + curY);
		if (board.curPiece == null) {
			if (board.getPiece(curX, curY) != null) {
				if (board.getPiece(curX, curY).getColor() == board.turn) {
					board.curPiece = board.getPiece(curX, curY);
					board.curPiecePos = curPos;
					System.out.println("Selected");
				}
			}
		} else {
			board.curPiece.getMovement(board, board.curPiecePos);
			moveable = board.curPiece.getMovement(board, board.curPiecePos);
			System.out.println(moveable);
			if (moveable.contains(curPos)) {
				board.Move(board.curPiecePos, curPos);
				System.out.println("Moved");
			} else {
				board.curPiece = null;
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
