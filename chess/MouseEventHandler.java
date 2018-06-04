package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import pieces.Position;

/* by jung */

public class MouseEventHandler implements MouseListener {
	Board_Master board;
	GameFrame_1vs1 gFrame;

	public MouseEventHandler(Board_Master board, GameFrame_1vs1 gameFrame) {
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
			if (board.getPiece(curPos).getColor() == board.turn) {
				board.curPiece = board.getPiece(curPos);
				board.curPiecePos = curPos;
				gFrame.change();
				System.out.println("Selected");
				for (int i = 0; i < 8; i++, System.out.println()) {
					for (int j = 0; j < 8; j++) {
						System.out.print(((Board_1) board).getCatchable(board.turn)[i][j] + " ");
					}
				}
			}
		} else {
			if (board.curPiece.getMovement((Board_1) board, board.curPiecePos).contains(curPos)) {
				if (board.isIllegalMove(board.curPiecePos, curPos)) {
					System.out.println("Illegal Move");
				} else {
					board.Move(board.curPiecePos, curPos);
					gFrame.change();
					System.out.println("Moved");

					Checker checker = new Checker();
					if (checker.isChecked((Board_1) board, board.getTurn())) {
						System.out.println("Check");
						if (checker.isCheckMate((Board_1) board, board.getTurn())) {
							gFrame.showPopUp("CheckMate");
							System.out.println("Checkmate");
						}
					} else if (checker.isStaleMate((Board_1) board, board.getTurn())) {
						gFrame.showPopUp("StaleMate");
						System.out.println("StaleMate");
					}
				}
			} else if (board.getPiece(curPos).getColor() == board.getTurn()
					&& !board.getPiece(curPos).equals(board.curPiece)) {
				board.curPiece = board.getPiece(curPos);
				board.curPiecePos = curPos;
				gFrame.change();
				System.out.println("Selected");
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
