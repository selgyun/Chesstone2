package chess;

import java.util.ArrayList;
import java.util.Iterator;

import pieces.Bishop;
import pieces.Knight;
import pieces.Position;
import pieces.Rook;

public class Checker implements ConstDef {

	// kindkiz 작성

	final int BOARD1MAX = 8;
	final int BOARD2MAX = 14;

	public boolean isChecked(Board_1 board, int turn) {
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn) {
					KingX = X;
					KingY = Y;
				}
			}
		}

		// done
		if (turn == WHITE && board.getCatchable(BLACK)[KingX][KingY]) {
			return true;
		} else if (board.getCatchable(WHITE)[KingX][KingY]) {
			return true;
		}

		return false;
	}

	/*
	public boolean isChecked(Board_2 board, int turn) {
		// find king position
		int KingX = 0, KingY = 0;
		 
		for (int X = 0; X < BOARD2MAX; X++) {
			for (int Y = 0; Y < BOARD2MAX; Y++) {
				if(board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn)
				{
					KingX = X;
					KingY = Y;
				}
			}
		}
		// done
		if((turn == WHITE || turn == BLACK) && board.p2_catchable[KingX][KingY])
		{
			return true;
		}
		else if (board.p1_catchable[KingX][KingY]) {
			return true;
		}
		return false;
	}*/

	public boolean isCheckMate(Board_1 board,int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if(board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn)
				{
					KingX = X;
					KingY = Y;
				}
			}
		}
		//done
		if(board.getPiece(KingX, KingY).getMovement(board, new Position(KingX,KingY)).size() > 0)
		{
			return false;
		}
		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if ((turn == WHITE) ? board.getCatchable(WHITE)[X][Y] : board.getCatchable(BLACK)[X][Y])
					return false;
			}
		}
		return true;
	}

	/*
	public boolean isCheckMate(Board_2 board, int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}

		return false;
	}*/

	public boolean isStaleMate(Board_1 board, int turn) {
		// 진짜 말그대로 아무것도 못하죠 상태가 되어야함.
		if (isChecked(board, turn)) {
			return false;
		}
		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if ((turn == WHITE) ? board.getCatchable(WHITE)[X][Y] : board.getCatchable(BLACK)[X][Y])
					return false;
			}
		}
		return true;
	}

	/*
	public boolean isStaleMate(Board_2 board, int turn) {
		if (isChecked(board, turn)) {
			return false;
		}
		for (int X = 0; X < BOARD2MAX; X++) {
			for (int Y = 0; Y < BOARD2MAX; Y++) {
				if ((turn == WHITE || turn == BLACK) ? board.p1_catchable[X][Y] : board.p2_catchable[X][Y])
					return false;
			}
		}
		return true;
	}*/
}
