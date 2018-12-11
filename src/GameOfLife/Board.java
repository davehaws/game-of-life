package GameOfLife;


import static GameOfLife.Cell.State.*;
import static GameOfLife.Location.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

	final private int size;
	Cell[][] cells;

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

	private Boolean indexIsOutOfRange(Location loc) {
		int x = loc.get(X);
		int y = loc.get(Y);
		if (x < 0 || y < 0) {
			return true;
		}
		if(x >= size || y >= size) {
			return true;
		}
		return false;
	}
	
	final static private Cell outOfRangeCell = new Cell();
	public Cell getCell(Location location) {
		if (indexIsOutOfRange(location)) {
			return outOfRangeCell;
		}
		return getInRangeCell(location);
	}

	private Cell getInRangeCell(Location location) {
		return cells[location.get(X)][location.get(Y)];
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
		neighbors.add(getCell(new Location(x-1,y-1)));
		neighbors.add(getCell(new Location(x-1,y)));
		neighbors.add(getCell(new Location(x-1,y+1)));
		neighbors.add(getCell(new Location(x,y-1)));
		neighbors.add(getCell(new Location(x,y+1)));
		neighbors.add(getCell(new Location(x+1,y-1)));
		neighbors.add(getCell(new Location(x+1,y)));
		neighbors.add(getCell(new Location(x+1,y+1)));
		return neighbors;
	}

}
