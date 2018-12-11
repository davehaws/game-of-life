package GameOfLife;

import static GameOfLife.Cell.State.*;
import java.util.List;

import GameOfLife.Cell.State;

public class EvolutionRules {

	public State getNextCellState(Cell cell, List<Cell> neighbors) {
		State result = DEAD;
		
		int count = 0;
		for (Cell neighbor : neighbors) {
			if (neighbor.is(ALIVE)) {
				count++;
			}
		}
		
		if (count > 1) {
			result = ALIVE;
		}
		return result;
	}

}
