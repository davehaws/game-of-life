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
	public void game_with_a_single_live_cell_will_die() {
		int[][] initialLiveCells = new int[][] {{1,1}};
		int[][] evolvedLiveCells = new int[][] {};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_live_cell_with_one_neighbor_will_die() {
		int[][] initialLiveCells = new int[][] {{1,1},{1,2}};
		int[][] evolvedLiveCells = new int[][] {};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_live_cell_with_two_neighbors_will_live() {
		int[][] initialLiveCells = new int[][] {{0,0}, {1,1}, {2,2}};
		int[][] evolvedLiveCells = new int[][] {{1,1}};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_live_cell_with_three_neighbors_will_live() {
		int[][] initialLiveCells = new int[][] {{0,0}, {2,0}, {2,2}, {1,1}};
		int[][] evolvedLiveCells = new int[][] {{1,1}, {1,0}, {2,1}};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_live_cell_with_four_neighbors_will_die() {
		int[][] initialLiveCells = new int[][] {{0,0}, {2,0}, {2,2}, {0,2}, {1,1}};
		int[][] evolvedLiveCells = new int[][] {{1,0}, {2,1}, {1,2}, {0,1}};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_dead_cell_with_three_neighbors_will_live() {
		int[][] initialLiveCells = new int[][] {{0,0}, {2,0}, {2,2}};
		int[][] evolvedLiveCells = new int[][] {{1,1}};
		
		assertBoardEvolution(3, initialLiveCells, evolvedLiveCells);
	}

	@Test
	public void game_with_a_line_of_three_cells_will_occilate() {
		int[][] firstLiveCoors = new int[][] {{0,1}, {1,1}, {2,1}};
		int[][] firstDeadCoors = new int[][] {{0,0},{1,0},{2,0},{0,2},{1,2},{2,2}};
		int[][] secondLiveCoors = new int[][] {{1,0}, {1,1}, {1,2}};
		int[][] secondDeadCoors = new int[][] {{0,0},{2,0},{0,1},{2,1},{0,2},{2,2}};
		
		Board board = createXYBoardWithInitialState(3, secondLiveCoors);
		board.setEvolutionRules(new EvolutionRules());

		for (int i = 0; i < 3; i++) {
			board.setNextCellStates();
			board.evolve();
			assertBoardState(board, 3, firstLiveCoors, firstDeadCoors);
			board.setNextCellStates();
			board.evolve();
			assertBoardState(board, 3, secondLiveCoors, secondDeadCoors);
		}
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

	private void assertBoardEvolution(int boardSize, int[][] initialLiveCoors, int[][] evolvedLiveCoors) {
		int[][] evolvedDeadCoors = getDeadCoorsFromLiveCoors(3, evolvedLiveCoors);
		
		Board board = createXYBoardWithInitialState(boardSize, initialLiveCoors);
		board.setEvolutionRules(new EvolutionRules());
		board.setNextCellStates();
		board.evolve();
		
		assertBoardState(board, 3, evolvedLiveCoors, evolvedDeadCoors);
	}

	private int[][] getDeadCoorsFromLiveCoors(int boardSize, int[][] evolvedLiveCoors) {
		List<int[]> deadCoors = new ArrayList<int[]>();

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				boolean found = false;
				for (int[] liveCoor : evolvedLiveCoors) {
					if (liveCoor[0] == i && liveCoor[1] == j) {
						found = true;
						break;
					}
				}
				if (!found) {
					deadCoors.add(new int[] {i,j});
				}
			}
		}
		return deadCoors.toArray(new int[deadCoors.size()][]);
	}

	private void assertStateOfSpecifiedCells(Board board, int[][] liveCells, State state) {
		for (int[] coor : liveCells) {
			Location loc = new Location(coor[X],coor[Y]);
			
			String errorMessage = "Location " + loc.toString() + " was expected to be " + state;
			assertThat(errorMessage, board.getCell(loc).is(state), is(true));
		}
	}

}
