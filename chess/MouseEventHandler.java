package chess;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pieces.Position;

/* by jung */

public class MouseEventHandler implements MouseListener, ConstDef {
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
			if (board.curPiece == null) {
				if (board.getPiece(curPos).getColor() == board.turn) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
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
						gFrame.renewCorpses_1();
						gFrame.turnScreen.setText(board.getStringTurn() + " Turn");
						Checker checker = new Checker(board);
						if (checker.isChecked()) {
							gFrame.addMovelog(gFrame.logTextScreen, board.getStringTurn() + " King Checked!");
							if (checker.isCheckMate()) {
								gFrame.addMovelog(gFrame.logTextScreen, "CheckMate!");
								gFrame.showPopUp("CheckMate");
							}
						} else if (checker.isStaleMate()) {
							gFrame.addMovelog(gFrame.logTextScreen, "StaleMate!");
							gFrame.showPopUp("StaleMate");
						}
					}
				} else if (board.getPiece(curPos).getColor() == board.getTurn()
						&& !board.getPiece(curPos).equals(board.curPiece)) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
				} else {
					board.curPiece = null;
				}
			}
			gFrame.change();
		} else {
			JPanel curSquare = (JPanel) e.getSource();
			int curX = (int) curSquare.getClientProperty("column");
			int curY = (int) curSquare.getClientProperty("row");

			Position curPos = new Position(curX, curY);
			if (board.curPiece == null) {
				if (board.getPiece(curPos).getColor() == board.getTurn()) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
				}
			} else {
				if (board.curPiece.getMovement(board, board.curPiecePos).contains(curPos)) {
					if (board.isIllegalMove(board.curPiecePos, curPos)) {
						gFrame2.addMovelog(gFrame2.logTextScreen, "\nIt is illegal Move!");
					} else {
						gFrame2.addMovelog(gFrame2.logTextScreen,
								"\n"+board.getStringTurn() + " " + board.curPiece.getNameS() + " Moved "
										+ board.getRealPos(board.curPiecePos) + " -> " + board.getRealPos(curPos));
						if (board.getPiece(curPos).getColor() == board.getNextTurn()
								|| board.getPiece(curPos).getColor() == board.getPrevTurn()) {
							gFrame2.addMovelog(gFrame2.logTextScreen, "It took " + board.getPiece(curPos).getColorS() + " "
									+ board.getPiece(curPos).getNameS());
						}
						board.Move(board.curPiecePos, curPos);
						gFrame2.turnScreen.setText(board.getStringTurn() + " Turn");
						Checker checker = new Checker(board);
						if (checker.isChecked()) {
							gFrame2.addMovelog(gFrame2.logTextScreen, board.getStringTurn() + " King Checked!");
							if (checker.isCheckMate()) {
								gFrame2.addMovelog(gFrame2.logTextScreen, "CheckMate!");
								int checked = board.getTurn();
								switch(checked)
								{
								case BLACK:
								case WHITE:
									if(!board.inDanger_1) {
										for(int i = 0; i < 14; i++)
										{
											for(int j = 0; j < 14; j++)
											{
												if(Position.inRange(i, j))
												{
													if(board.getPiece(i, j).getColor() == checked)
														board.setMT(i, j);
												}
											}
										}
										board.inDanger_1 = true;
									}
									else {
										gFrame2.addMovelog(gFrame2.logTextScreen, "Checkmate!");
										gFrame2.showPopUp("Team2 WIN!!");
									}
									break;
									
								case GREEN:
								case RED:
									if(!board.inDanger_2) {
										for(int i = 0; i < 14; i++)
										{
											for(int j = 0; j < 14; j++)
											{
												if(Position.inRange(i, j))
												{
													if(board.getPiece(i, j).getColor() == checked)
														board.setMT(i, j);
												}
											}
										}
										board.inDanger_2 = true;
										
									}
									else {
										gFrame2.addMovelog(gFrame2.logTextScreen, "Checkmate!");
										gFrame2.showPopUp("Team1 WIN!!");
									}
									break;
								}
								
								gFrame2.showPopUp("CheckMate");
							}
						} else if (checker.isStaleMate()) {
							gFrame2.addMovelog(gFrame2.logTextScreen, "StaleMate!");
							gFrame2.showPopUp("Draw!!");
						}
					}
				} else if (board.getPiece(curPos).getColor() == board.getTurn()
						&& !board.getPiece(curPos).equals(board.curPiece)) {
					board.curPiece = board.getPiece(curPos);
					board.curPiecePos = curPos;
				} else {
					board.curPiece = null;
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
