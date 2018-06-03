package chess;

import java.util.ArrayList;
import java.util.Iterator;

import pieces.Knight;
import pieces.Position;
import pieces.Queen;

public class Checker implements ConstDef{
	
	//kindkiz 작성
	
	final int BOARD1MAX = 8;
	final int BOARD2MAX = 14;
	
	public boolean isChecked(Board_1 board,int turn) {
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
		if(turn == WHITE && board.p2_catchable[KingX][KingY])
		{
			return true;
		}
		else if (board.p1_catchable[KingX][KingY]) {
			return true;
		}

		return false;
	}

	public boolean isChecked(Board_2 board,int turn) {
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
		
		// 1. king을 둘러싼 8칸이 모두 아군이거나 catchable 이면 checkMate
		for (int i = -1; i <= 1; i++) 
		{
			for (int j = -1; j <= 1; j++) 
			{
				if (!(i == 0 && j == 0)) 
				{
					if (((0 <= KingX + i) && (KingX + i < BOARD1MAX)) && ((0 <= KingY + i) && KingY + i < BOARD1MAX))
					{
						if ((board.getPiece(KingX + i, KingY + j) == null) && (turn == WHITE) ? (!board.p2_catchable[KingX + i][KingY + j]) : (!board.p1_catchable[KingX + i][KingY + j]))
						{
							// 공격 불가능한 지역이 있으면
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
		
		ArrayList <Position> toTheKing = new ArrayList<>();
		Knight kingCloneKn = new Knight(10);
		Queen kingCloneQu = new Queen(10);
		
		kingCloneKn.getMovement(board, new Position(KingX,KingY)).removeAll(kingCloneQu.getMovement(board, new Position(KingX,KingY)));
		kingCloneKn.getMovement(board, new Position(KingX,KingY)).addAll(kingCloneQu.getMovement(board, new Position(KingX,KingY)));
		toTheKing = kingCloneKn.getMovement(board, new Position(KingX,KingY));
		Iterator<Position> itr = toTheKing.iterator();
		for(;itr.hasNext();)
		{
			if(board.getPiece(itr.next().getX(),itr.next().getY()) != null && board.getPiece(itr.next().getX(),itr.next().getY()).getColor() != turn)
			{
				count++;
				assassinX = itr.next().getX();
				assassinY = itr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		//공격 방어가 가능한지 체크
		if(turn == WHITE && board.p1_catchable[assassinX][assassinY])
		{
			return false;
		}
		else if (board.p2_catchable[assassinX][assassinY])
		{
			return false;
		}
		//공격 방어가 안된다면 checkMate!!
		return true;
	}

	public boolean isCheckMate(Board_2 board,int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}

		return false;
	}

	public boolean isStaleMate(Board_1 board,int turn) {
		//진짜 말그대로 아무것도 못하죠 상태가 되어야함.
		if (isChecked(board, turn)) {
			return false;
		}
		for(int X = 0;X < BOARD1MAX;X++)
		{
			for(int Y = 0;Y < BOARD1MAX; Y++)
			{
				if((turn == WHITE) ? board.p1_catchable[X][Y] : board.p2_catchable[X][Y])
					return false;
			}
		}
		return true;
	}

	public boolean isStaleMate(Board_2 board,int turn) {
		if (isChecked(board, turn)) {
			return false;
		}

		return false;
	}
}
