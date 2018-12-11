package GameOfLife;


import static GameOfLife.Cell.State.*;

public class Game {

	private int dimension = 10;
	private Board board = new Board();
	
	public int getDimension() {
		return dimension;
	}

	public Board getBoard() {
		return board;
	}

	public boolean cellIsAlive(int x, int y) {
		if (coordinatesAreIllegal(x, y)) {
			throw new IndexOutOfBoundsException();
		}
			
		return board.getCell(new Location(x,y)).is(ALIVE);
	}

	public void setInitialBoard(int[][] coordinates) {
		board = new Board(dimension);
		for (int[] pair : coordinates) {
			Location loc = new Location(pair[0], pair[1]);
			board.getCell(loc).setState(ALIVE);
		}
		
	}

	public void tick() {
		Board newBoard = new Board(dimension);
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				int neighborCount = getNeighborCount(x, y);
				Location loc = new Location(x, y);
				Cell newCell = board.getCell(loc).nextGen(neighborCount);
				if (newCell.is(ALIVE)) {
					newBoard.getCell(loc).setState(ALIVE);
				}
			}
		}
		board = newBoard;
	}

	public void setDimension(int newDimension) {
		dimension = newDimension;
		board = new Board(dimension);
	}

	private int getCellState(int x, int y) {
		if (coordinatesAreIllegal(x, y)) {
			return 0;
		}
		
		Location loc = new Location(x, y);
		return board.getCell(loc).is(ALIVE) ? 1 : 0;
	}

	private boolean coordinatesAreIllegal(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		}
		if (x >= dimension || y >= dimension) {
			return true;
		}
		return false;
	}
	
	public int getNeighborCount(int x, int y) {
		int count = 0;
		count += getCellState(x-1, y-1);
		count += getCellState(x-1, y);
		count += getCellState(x-1, y+1);
		count += getCellState(x, y-1);
		count += getCellState(x, y+1);
		count += getCellState(x+1, y-1);
		count += getCellState(x+1, y);
		count += getCellState(x+1, y+1);

		return count;
	}

}
