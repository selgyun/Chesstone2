package chess;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pieces.Position;

/* by jung */

public class MouseEventHandler implements MouseListener {
	Color tempCol = new Color(0, 0, 0);
	LineBorder tempBord = new LineBorder(tempCol, 5);
	Board_Master board;
	GameFrame_1vs1 gFrame;
	GameFrame_2vs2 gFrame2;
	ImagePanel[][] CorpsePanel = new ImagePanel[4][16];

	public MouseEventHandler(Board_Master board, GameFrame_1vs1 gameFrame) {
		this.board = board;
		this.gFrame = gameFrame;
	}

	public MouseEventHandler(Board_Master board, GameFrame_2vs2 gameFrame) {
		this.board = board;
		this.gFrame2 = gameFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gFrame != null) {
			JPanel curSquare = (JPanel) e.getSource();
			int curX = (int) curSquare.getClientProperty("column");
			int curY = (int) curSquare.getClientProperty("row");
			Position curPos = new Position(curX, curY);
			System.out.println(curX + " " + curY);
			if (board.curPiece == null) {
				if (board.getPiece(curPos).getColor() == board.turn) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
					System.out.println("Selected");
				}
			} else {
				if (board.curPiece.getMovement((Board_1) board, board.curPiecePos).contains(curPos)) {
					if (board.isIllegalMove(board.curPiecePos, curPos)) {
						gFrame.addMovelog(gFrame.logTextScreen, "\nIt is illegal Move!");
					} else {
						gFrame.addMovelog(gFrame.logTextScreen, "\n"+board.getStringTurn() + " " + board.curPiece.getNameS()
								+ " Moved " + board.getRealPos(board.curPiecePos) + " -> " + board.getRealPos(curPos));
						if (board.getPiece(curPos).getColor() == board.getNextTurn()
								|| board.getPiece(curPos).getColor() == board.getPrevTurn()) {
							gFrame.addMovelog(gFrame.logTextScreen, "It took " + board.getPiece(curPos).getColorS() + " "
									+ board.getPiece(curPos).getNameS());
						}
						board.Move(board.curPiecePos, curPos);
						gFrame.turnScreen.setText(board.getStringTurn() + " Turn");
						System.out.println("Moved");
						Checker checker = new Checker(board);
						if (checker.isChecked()) {
							gFrame.addMovelog(gFrame.logTextScreen, board.getStringTurn() + " King Checked!");
							System.out.println("Check");
							if (checker.isCheckMate()) {
								gFrame.addMovelog(gFrame.logTextScreen, "CheckMate!");
								gFrame.showPopUp("CheckMate");
								System.out.println("Checkmate");
							}
						} else if (checker.isStaleMate()) {
							gFrame.addMovelog(gFrame.logTextScreen, "StaleMate!");
							gFrame.showPopUp("StaleMate");
							System.out.println("StaleMate");
						}
					}
				} else if (board.getPiece(curPos).getColor() == board.getTurn()
						&& !board.getPiece(curPos).equals(board.curPiece)) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
					System.out.println("Selected");
				} else {
					board.curPiece = null;
					System.out.println("Canceled");
				}
			}
			gFrame.change();
		} else {
			JPanel curSquare = (JPanel) e.getSource();
			int curX = (int) curSquare.getClientProperty("column");
			int curY = (int) curSquare.getClientProperty("row");

			Position curPos = new Position(curX, curY);
			System.out.println(curX + " " + curY);
			if (board.curPiece == null) {
				if (board.getPiece(curPos).getColor() == board.getTurn()) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
					System.out.println("Selected");
				}
			} else {
				if (board.curPiece.getMovement(board, board.curPiecePos).contains(curPos)) {
					if (board.isIllegalMove(board.curPiecePos, curPos)) {
						gFrame2.addMovelog(gFrame2.logTextScreen, "\nIt is illegal Move!");
					} else {
						gFrame2.addMovelog(gFrame2.logTextScreen,
								"\n"+board.getStringTurn() + " " + board.curPiece.getNameS() + " Moved "
										+ board.getRealPos(board.curPiecePos) + " -> " + board.getRealPos(curPos));
						board.Move(board.curPiecePos, curPos);
						gFrame2.turnScreen.setText(board.getStringTurn() + " Turn");
						System.out.println("Moved");
						Checker checker = new Checker(board);
						if (checker.isChecked()) {
							gFrame2.addMovelog(gFrame2.logTextScreen, board.getStringTurn() + " King Checked!");
							System.out.println("Check");
							if (checker.isCheckMate()) {
								gFrame2.addMovelog(gFrame2.logTextScreen, "CheckMate!");
								gFrame2.showPopUp("CheckMate");
								System.out.println("Checkmate");
							}
						} else if (checker.isStaleMate()) {
							gFrame2.addMovelog(gFrame2.logTextScreen, "StaleMate!");
							gFrame2.showPopUp("StaleMate");
							System.out.println("StaleMate");
						}
					}
				} else if (board.getPiece(curPos).getColor() == board.getTurn()
						&& !board.getPiece(curPos).equals(board.curPiece)) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
					System.out.println("Selected");
				} else {
					board.curPiece = null;
					System.out.println("Canceled");
				}

			}
			gFrame2.change();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		 * JPanel curSquare = (JPanel) e.getSource(); tempCol =
		 * curSquare.getBackground(); tempBord = (LineBorder) curSquare.getBorder();
		 * curSquare.setBackground(new Color(255, 178, 245));
		 */
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		 * JPanel curSquare = (JPanel) e.getSource(); curSquare.setBackground(tempCol);
		 * curSquare.setBorder(tempBord); tempCol = null;
		 */
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
