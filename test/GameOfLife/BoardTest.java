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
	public void it_can_evolve_to_next_state() {
		int[][] initialLiveCells = new int[][] {{0,0},{1,0},{1,1},{2,2}};
		
		Board board = createXYBoardWithInitialState(3, initialLiveCells);
		board.setEvolutionRules(new EvolutionRules());
		board.setNextCellStates();
		board.evolve();
		
		int[][] evolvedLiveCells = new int[][] {{0,0},{1,0},{0,1},{1,1},{2,1}};
		int[][] deadCoors = new int[][] {{2,0},{0,2},{1,2},{2,2}};
		
		assertBoardState(board, 3, evolvedLiveCells, deadCoors);
		
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
