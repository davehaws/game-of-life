package GameOfLife;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static GameOfLife.Cell.State.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class EvolutionRulesTest {
	private Cell deadCell = new Cell(DEAD);
	private Cell liveCell = new Cell(ALIVE);
	EvolutionRules rules = new EvolutionRules();
	List<Cell> neighbors = new ArrayList<Cell>();

	@Test
	public void it_knows_a_live_cell_with_no_neighbors_will_die() {
		// No neighbors
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
	}

	@Test
	public void it_knows_a_live_cell_with_all_dead_neighbors_will_die() {
		// All neighbors dead
		neighbors.add(deadCell);
		neighbors.add(deadCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
	}


}
