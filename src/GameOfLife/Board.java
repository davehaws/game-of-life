package GameOfLife;


import static GameOfLife.Cell.State.*;
import static GameOfLife.Location.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

	final private int size;
	Cell[][] cells;

	final static private Cell outOfRangeCell = new Cell();
	private EvolutionRules evolutionRules;

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

	private List<Cell> getNeighbors(int x, int y) {
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

	public void setEvolutionRules(EvolutionRules rules) {
		this.evolutionRules = rules;
	}

	public void setNextCellStates() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Cell cell = cells[x][y];
				List<Cell> neighbors = getNeighbors(x, y);
				cell.setNextState(evolutionRules.getNextCellState(cell, neighbors));
			}
		}
	}

	public void evolve() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				cells[x][y].evolve();;
			}
		}
	}

}
