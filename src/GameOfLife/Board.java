package GameOfLife;


import static GameOfLife.Cell.State.*;
import static GameOfLife.Location.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

	final private int size;
	Cell[][] cells;

	final static private Cell outOfRangeCell = new Cell();

	public Board() {
		this.size = 10;
		initialize();
	}

	public Board(int size) {
		this.size = size;
		initialize();
	}

	public int getDimension() {
		return size;
	}

	private void initialize() {
		cells = new Cell[size][];
		for (int x = 0; x < size; x++) {
			cells[x] = new Cell[size];
			for (int y = 0; y < size; y++) {
				cells[x][y] = new Cell();
			}
		}
	}

	private Boolean indexIsOutOfRange(int x, int y) {
		if (x < 0 || y < 0) {
			return true;
		}
		if(x >= size || y >= size) {
			return true;
		}
		return false;
	}
	
	public Cell getCell(Location location) {
		return getCellXY(location.get(X), location.get(Y));
	}
	
	private Cell getCellXY(int x, int y) {
		if (indexIsOutOfRange(x, y)) {
			return outOfRangeCell;
		}
		return getInRangeCell(x, y);
	}

	private Cell getInRangeCell(int x, int y) {
		return cells[x][y];
	}

	public void setInitialState(List<Location> locations) {
		for (Location location : locations) {
			getCell(location).setState(ALIVE);
		}
	}

	public int getLiveNeighborCount(Location location) {
		List<Cell> neighbors = getNeighbors(location);
		
		return getLiveCellCount(neighbors);
	}

	private int getLiveCellCount(List<Cell> cells) {
		int result = 0;
		for (Cell cell : cells) {
			if (cell.is(ALIVE)) {
				result++;
			}
		}
		return result;
	}

	private List<Cell> getNeighbors(Location location) {
		int x = location.get(X);
		int y = location.get(Y);
		
		List<Cell> neighbors = new ArrayList<Cell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 || j != 0) {
					neighbors.add(getCellXY(x+i, y+j));
				}
			}
		}

		return neighbors;
	}

}
