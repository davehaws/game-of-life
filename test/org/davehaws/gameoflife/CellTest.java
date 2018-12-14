package org.davehaws.gameoflife;
import static org.davehaws.gameoflife.Cell.State.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.davehaws.gameoflife.Cell;
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
