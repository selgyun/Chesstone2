package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import chess.*;

public abstract class Piece implements ConstDef {
	protected int color;
	protected int team; // TEAM 1 : Black, White || TEAM 2 : Red, Green
	protected int name;
	protected BufferedImage img = null;
	public static int players = 1;
	protected static boolean checking = false;

	public int getColor() {
		return color;
	}

	public int getName() {
		return name;
	}

	public BufferedImage getImg() {
		return img;
	}

	abstract public ArrayList<Position> getMovement(Board_Master board, Position now);
}
