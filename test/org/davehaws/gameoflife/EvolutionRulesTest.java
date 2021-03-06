package org.davehaws.gameoflife;

import static org.junit.Assert.*;
import static org.davehaws.gameoflife.Cell.State.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.davehaws.gameoflife.Cell;
import org.davehaws.gameoflife.EvolutionRules;
import org.junit.Test;

public class EvolutionRulesTest {
	private Cell deadCell = new Cell(DEAD);
	private Cell liveCell = new Cell(ALIVE);
	EvolutionRules rules = new EvolutionRules();
	List<Cell> neighbors = new ArrayList<Cell>();

	@Test
	public void it_knows_a_live_cell_with_no_neighbors_will_die() {
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
	}

	@Test
	public void it_knows_a_live_cell_with_all_dead_neighbors_will_die() {
		neighbors.add(deadCell);
		neighbors.add(deadCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
	}

	@Test
	public void it_knows_a_live_cell_with_more_than_one_neighbor_will_live() {
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(ALIVE));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(ALIVE));
	}
	
	@Test
	public void it_knows_a_live_cell_will_die_from_overpopulation() {
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(liveCell, neighbors), is(DEAD));
	}
	
	@Test
	public void it_knows_a_dead_cell_will_stay_dead() {
		assertThat(rules.getNextCellState(deadCell, neighbors), is(DEAD));
		neighbors.add(deadCell);
		neighbors.add(deadCell);
		neighbors.add(deadCell);
		assertThat(rules.getNextCellState(deadCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(deadCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(deadCell, neighbors), is(DEAD));
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(deadCell, neighbors), is(DEAD));
	}
	
	@Test
	public void it_knows_a_dead_cell_will_evolve_to_a_live_cell_with_the_right_number_of_neighbors() {
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		neighbors.add(liveCell);
		assertThat(rules.getNextCellState(deadCell, neighbors), is(ALIVE));
	}
	
}
