package chess;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
		gFrame.addMovelog(gFrame.logTextScreen, curX + " " + curY);
		System.out.println(curX + " " + curY);
		if (board.curPiece == null) {
			if (board.getPiece(curPos).getColor() == board.turn) {
				board.curPiece = board.getPiece(curPos);
				board.curPiecePos = curPos;
				gFrame.change();
				gFrame.addMovelog(gFrame.logTextScreen, "Selected\n");
				System.out.println("Selected");
			}
		} else {
			if (board.curPiece.getMovement((Board_1) board, board.curPiecePos).contains(curPos)) {
				if (board.isIllegalMove(board.curPiecePos, curPos)) {
					gFrame.addMovelog(gFrame.logTextScreen, "Illegal Move!!\n");
					System.out.println("Illegal Move");
				} else {
					board.Move(board.curPiecePos, curPos);
					gFrame.change();
					gFrame.addMovelog(gFrame.logTextScreen, "Moved\n");
					System.out.println("Moved");

					Checker checker = new Checker();
					if (checker.isChecked((Board_1) board)) {
						gFrame.addMovelog(gFrame.logTextScreen, "Check!");
						System.out.println("Check");
						if (checker.isCheckMate((Board_1) board)) {
							gFrame.addMovelog(gFrame.logTextScreen, "CheckMate!");
							gFrame.showPopUp("CheckMate");
							System.out.println("Checkmate");
						}
					} else if (checker.isStaleMate((Board_1) board)) {
						gFrame.addMovelog(gFrame.logTextScreen, "StaleMate!");
						gFrame.showPopUp("StaleMate");
						System.out.println("StaleMate");
					}
				}
			} else if (board.getPiece(curPos).getColor() == board.getTurn()
					&& !board.getPiece(curPos).equals(board.curPiece)) {
				board.curPiece = board.getPiece(curPos);
				board.curPiecePos = curPos;
				gFrame.change();
				gFrame.addMovelog(gFrame.logTextScreen, "Selected\n");
				System.out.println("Selected");
			} else {
				board.curPiece = null;
				gFrame.change();
				gFrame.addMovelog(gFrame.logTextScreen, "Canceled\n");
				System.out.println("Canceled");
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JPanel curSquare = (JPanel) e.getSource();
		curSquare.setBackground(new Color(255,178,245));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JPanel curSquare = (JPanel) e.getSource();
		int curX = (int) curSquare.getClientProperty("column");
		int curY = (int) curSquare.getClientProperty("row");
		
		if ((curX + curY) % 2 == 1) {
			curSquare.setBackground(new Color(180, 120, 50));
			curSquare.setBorder(new LineBorder(new Color(180, 120, 50), 5));

		} else {
			curSquare.setBackground(new Color(240, 220, 200));
			curSquare.setBorder(new LineBorder(new Color(240, 220, 200), 5));
		}
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
