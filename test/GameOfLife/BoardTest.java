package GameOfLife;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static GameOfLife.Cell.State.*;
import static GameOfLife.Location.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GameOfLife.Cell.State;

public class BoardTest {

	@Test
	public void it_allows_setting_initial_setup() {
		int[][] liveCells = new int[][] {{0,0},{1,1},{2,2}};
		int[][] deadCoors = new int[][] {{0,1},{0,2},{1,0},{1,2},{2,0},{2,1}};
		int dimension = 3;
		
		Board board = createXYBoardWithInitialState(dimension, liveCells);
		
		assertBoardState(board, dimension, liveCells, deadCoors);
	}

	@Test
	public void it_will_return_a_dead_cell_if_we_ask_for_cells_outside_the_board() {
		Board board = new Board(4);
		assertStateOfSpecifiedCells(board, new int[][] {{-1, 0},{0,-1},{4,0},{0,4}}, DEAD);
	}
	
	@Test
	public void it_can_return_the_number_of_live_cells() {
		int[][] liveCells = new int[][] {{0,0},{1,1},{2,2}};
		
		Board board = createXYBoardWithInitialState(3, liveCells);
		assertThat(board.getLiveNeighborCount(new Location(0,0)), is(1));
		assertThat(board.getLiveNeighborCount(new Location(1,0)), is(2));
		assertThat(board.getLiveNeighborCount(new Location(2,0)), is(1));
		assertThat(board.getLiveNeighborCount(new Location(0,1)), is(2));
		assertThat(board.getLiveNeighborCount(new Location(1,1)), is(2));
		assertThat(board.getLiveNeighborCount(new Location(2,1)), is(2));
		assertThat(board.getLiveNeighborCount(new Location(0,2)), is(1));
		assertThat(board.getLiveNeighborCount(new Location(1,2)), is(2));
		assertThat(board.getLiveNeighborCount(new Location(2,2)), is(1));
	}
	/* 
	 * HELPER SETUP FUNCTIONS
	 */
	private Board createXYBoardWithInitialState(int dimension, int[][] coors) {
		Board board = new Board(dimension);

		List<Location> locations = new ArrayList<Location>();
		for (int[] coor : coors) {
			locations.add(new Location(coor[X], coor[Y]));
		}		
		board.setInitialState(locations);
		return board;
	}

	/*
	 * CUSTOM ASSERT METHODS
	 */
	private void assertBoardState(Board board, int size, int[][] liveCells, int[][] deadCoors) {
		assertThat(board.getDimension(), is(size));
		assertStateOfSpecifiedCells(board, liveCells, ALIVE);
		assertStateOfSpecifiedCells(board, deadCoors, DEAD);
	}

	private void assertStateOfSpecifiedCells(Board board, int[][] liveCells, State state) {
		for (int[] coor : liveCells) {
			Location loc = new Location(coor[X],coor[Y]);
			
			String errorMessage = "Location " + loc.toString() + " was expected to be " + state;
			assertThat(errorMessage, board.getCell(loc).is(state), is(true));
		}
	}

}
