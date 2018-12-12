package GameOfLife;

import static GameOfLife.Cell.State.*;
import java.util.List;

import GameOfLife.Cell.State;

public class EvolutionRules {

	private static final int BIRTH_LEVEL = 3;
	private static final int OVERCROWDED_LEVEL = 4;
	private static final int LONELY_LEVEL = 1;

	public State getNextCellState(Cell cell, List<Cell> neighbors) {
		State result = DEAD;
		
		int count = 0;
		for (Cell neighbor : neighbors) {
			if (neighbor.is(ALIVE)) {
				count++;
			}
		}
		
		if (cell.is(ALIVE)) {
			if (count > LONELY_LEVEL && count < OVERCROWDED_LEVEL) {
				result = ALIVE;
			}
		} else {
			if (count == BIRTH_LEVEL) {
				result = ALIVE;
			}
		}
		return result;
	}

}
