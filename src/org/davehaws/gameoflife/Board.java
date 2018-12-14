package org.davehaws.gameoflife;


import static org.davehaws.gameoflife.Cell.State.*;
import static org.davehaws.gameoflife.Location.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {

	final private int size;
	Map<Integer, Map<Integer, Cell>> cells = new HashMap<Integer,  Map<Integer,  Cell>>();
	

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
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				getCellXY(x, y);
			}
		}
	}

	public Cell getCell(Location location) {
		return getCellXY(location.get(X), location.get(Y));
	}

	private Cell getCellXY(int x, int y) {
		Cell result = null;
		
		Map<Integer, Cell> row = cells.get(x);
		if (row == null) {
			row = new HashMap<Integer, Cell>();
			cells.put(x, row);
		}
		result = row.get(y);
		
		if (result == null) {
			result = new Cell();
			row.put(y, result);
		}
		return result;
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
				Cell cell = getCellXY(x, y);
				List<Cell> neighbors = getNeighbors(x, y);
				cell.setNextState(evolutionRules.getNextCellState(cell, neighbors));
			}
		}
	}

	public void evolve() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				getCellXY(x, y).evolve();
			}
		}
	}

}
