package GameOfLife;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static GameOfLife.Cell.State.*; 

import org.junit.Test;

public class CellTest {

	Cell deadCell = new Cell();
	Cell liveCell = new Cell(ALIVE);
	
	@Test
	public void dead_by_default() {
		assertThat(deadCell.is(DEAD),  is(true));
	}

	@Test
	public void can_be_created_alive() {
		assertThat(liveCell.is(ALIVE),  is(true));
	}
	
	private void assertNextGen(Cell cell, int numNeighbors, Boolean expectedState) {
		Cell nextGen = cell.nextGen(numNeighbors);
		assertThat(nextGen.is(ALIVE), is(expectedState));
	}

	@Test
	public void a_live_cell_will_die_from_loneliness() {
		assertNextGen(liveCell, 0, false);
		assertNextGen(liveCell, 1, false);
	}

	@Test
	public void a_live_cell_will_die_from_overcrowding() {
		assertNextGen(liveCell, 0, false);
		assertNextGen(liveCell, 4, false);
		assertNextGen(liveCell, 5, false);
		assertNextGen(liveCell, 6, false);
		assertNextGen(liveCell, 7, false);
		assertNextGen(liveCell, 8, false);
	}

	@Test
	public void a_live_cell_that_is_not_lonely_or_crowded_will_live() {
		assertNextGen(liveCell, 2, true);
		assertNextGen(liveCell, 3, true);
	}

	@Test
	public void a_dead_cell_will_only_live_with_the_right_number_of_neighbors() {
		assertNextGen(deadCell, 0, false);
		assertNextGen(deadCell, 1, false);
		assertNextGen(deadCell, 2, false);
		assertNextGen(deadCell, 3, true);
		assertNextGen(deadCell, 4, false);
		assertNextGen(deadCell, 5, false);
		assertNextGen(deadCell, 6, false);
		assertNextGen(deadCell, 7, false);
		assertNextGen(deadCell, 8, false);
	}

	@Test
	public void can_change_state() {
		Cell cell = new Cell();
		assertThat(cell.is(DEAD), is(true));
		cell.setState(ALIVE);
		assertThat(cell.is(ALIVE), is(true));
	}
	
	@Test
	public void can_set_next_generation_on_cell() {
		Cell cell = new Cell();
		assertThat(cell.is(DEAD), is(true));
		cell.setNextState(ALIVE);
		assertThat(cell.is(DEAD), is(true));
		cell.evolve();
		assertThat(cell.is(ALIVE), is(true));
	}
	
}
