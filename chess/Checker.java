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
	}

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
		// done
		
		// king을 둘러싼 8칸이 모두 아군이거나 catchable 이면 왕은 이동이 불가하다.
		for (int i = -1; i <= 1; i++) 
		{
			for (int j = -1; j <= 1; j++) 
			{
				if (!(i == 0 && j == 0)) 
				{
					if (((0 <= KingX + i) && (KingX + i < BOARD1MAX)) && ((0 <= KingY + i) && KingY + i < BOARD1MAX))
					{
						if ((board.getPiece(KingX + i, KingY + j) == null && (turn == WHITE) ? (!board.getCatchable(BLACK)[KingX + i][KingY + j]) : (!board.getCatchable(WHITE)[KingX + i][KingY + j])))
						{
							// 상대가 공격 불가능한 지역이 있으면
							return false;
						}
						else if (board.getPiece(KingX + i, KingY + j).getColor() != turn) {
							// 적군이면
							return false;
						}
					}
				}
			}
		}
		// 예외처리 : king의 공격 경로를 차단할 수 있는가?
		int count = 0; //왕을 공격할 수 있는 기물이 2개가 되는 순간 checkMate!
		//isChecked가 true 였기 때문에 암살자는 무조건 한명나옴. 
		int assassinX = 0,assassinY = 0;
		
		Knight kingCloneKn = new Knight(10);
		Rook kingCloneRo = new Rook(10);
		Bishop kingCloneBi = new Bishop(10);
		//공격당할 수 있는 위치에 공격할 수 있는 기물이 있나 체크 만약 두개 이상이면 바로 return true.
		Iterator<Position> knitr = kingCloneKn.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;knitr.hasNext();)
		{
			if(board.getPiece(knitr.next().getX(), knitr.next().getY()) != null && (board.getPiece(knitr.next().getX(), knitr.next().getY()).getColor() != turn && board.getPiece(knitr.next().getX(), knitr.next().getY()).getName() == KNIGHT))
			{
				count++;
				assassinX = knitr.next().getX();
				assassinY = knitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		Iterator<Position> Roitr = kingCloneRo.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;Roitr.hasNext();)
		{
			if(board.getPiece(Roitr.next().getX(), Roitr.next().getY()) != null && (board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getColor() != turn && (board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getName() == ROOK || board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getName() == QUEEN)))
			{
				count++;
				assassinX = Roitr.next().getX();
				assassinY = Roitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		Iterator<Position> Biitr = kingCloneBi.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;Biitr.hasNext();)
		{
			if(board.getPiece(Biitr.next().getX(), Biitr.next().getY()) != null && (board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getColor() != turn && (board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getName() == BISHOP || board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getName() == QUEEN)))
			{
				count++;
				assassinX = Biitr.next().getX();
				assassinY = Biitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		
		//공격 방어가 가능한지 체크
		if(turn == WHITE && board.getCatchable(WHITE)[assassinX][assassinY])
		{
			return false;
		}
		else if (board.getCatchable(BLACK)[assassinX][assassinY])
		{
			return false;
		}
		//공격 방어가 안된다면 checkMate!!
		
		return true;
	}

	public boolean isCheckMate(Board_2 board, int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}

		return false;
	}

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
	}
}
