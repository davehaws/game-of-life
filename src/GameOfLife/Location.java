package GameOfLife;


public class Location {
	int[] coors;

	final public static int X = 0;
	final public static int Y = 1;	
	
	public Location(int x, int y) {
		coors = new int[] {x,y};
	}

	public String toString() {
		return "(X:" + get(X) + ",Y:" + get(Y) + ")";
	}

	public int get(int coor) {
		return coors[coor];
	}
}
