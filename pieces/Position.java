package pieces;

public class Position {
	private int x;
	private int y;
	
	public Position(int a, int b)
	{
		x = a;
		y = b;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }
}
