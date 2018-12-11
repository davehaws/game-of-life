package GameOfLife;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	private Game game = new Game();
	
	@Test
	public void game_has_a_default_dimension_with_blank_board() {
		game.setDimension(10);
		assertThat(game.getDimension(), is(10));
		assertThat(game.cellIsAlive(0,0), is(false));
		assertThat(game.cellIsAlive(9,9), is(false));
		assertThat(game.cellIsAlive(3,7), is(false));
	}
	
	@Test
	public void game_has_access_to_board() {
		assertThat(game.getBoard(), is(notNullValue()));
	}
	
	@Test
	public void game_can_count_number_of_neighbors_for_a_single_cell_in_middle_of_board() {
		game.setInitialBoard(new int[][] {{2,3}});
		assertThat(game.getNeighborCount(2,3), is(0));
	}
	
	@Test
	public void game_can_count_number_of_neighbors_for_a_cell_in_middle_of_board() {
		game.setInitialBoard(new int[][] {{2,3}, {2,4}, {3,2}});
		assertThat(game.getNeighborCount(2,3), is(2));
		
		game.setInitialBoard(new int[][] {
			{1,1}, {1,2}, {1,3},
			{2,1}, {2,2}, {2,3},
			{3,1}, {3,2}, {3,3},
			});
		assertThat(game.getNeighborCount(2,2), is(8));
	}
	
	@Test
	public void game_can_count_number_of_neighbors_for_a_cell_on_negative_edge() {
		game.setInitialBoard(new int[][] {{0,0}, {1,0}, {1,1}, {0,1}});
		assertThat(game.getNeighborCount(0,0), is(3));
	}
	
	@Test
	public void game_can_count_number_of_neighbors_for_a_cell_on_dimension_edge() {
		game.setInitialBoard(new int[][] {{9,9}, {8,9}, {9,8}, {8,8}});
		assertThat(game.getNeighborCount(9,9), is(3));
	}
	
	@Test
	public void game_with_a_single_live_cell_will_die() {
		game.setInitialBoard(new int[][] {{2,3}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(false));
	}

	@Test
	public void game_with_a_live_cell_with_one_neighbor_will_die() {
		game.setInitialBoard(new int[][] {{2,3}, {3,3}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(false));
	}

	@Test
	public void game_with_a_live_cell_with_two_neighbors_will_live() {
		game.setInitialBoard(new int[][] {{2,3}, {3,3}, {1,2}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(true));
	}

	@Test
	public void game_with_a_live_cell_with_three_neighbors_will_live() {
		game.setInitialBoard(new int[][] {{2,3}, {3,3}, {1,2}, {2,2}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(true));
	}

	@Test
	public void game_with_a_live_cell_with_four_neighbors_will_die() {
		game.setInitialBoard(new int[][] {{2,3}, {3,3}, {1,2}, {2,2}, {3,4}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(false));
	}

	@Test
	public void game_with_a_dead_cell_with_three_neighbors_will_live() {
		game.setInitialBoard(new int[][] {{3,3}, {1,2}, {2,2}});
		game.tick();
		assertThat(game.cellIsAlive(2, 3), is(true));
	}

	@Test
	public void game_with_a_line_of_three_cells_will_occilate() {
		game.setDimension(3);
		game.setInitialBoard(new int[][] {{0,1}, {1,1}, {2,1}});

		for (int i = 0; i < 3; i++) {
			assertGameBoard(new boolean[][]{{false, true, false}, {false, true, false}, {false, true, false}});		
			game.tick();
			assertGameBoard(new boolean[][]{{false, false, false}, {true, true, true}, {false, false, false}});		
			game.tick();
		}
	}

	/*
	 * CUSTOM ASSERTS
	 */
	private void assertGameBoard(boolean[][] values) {
		int dimension = game.getDimension();
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				assertThat("Problem at (" + x + "," + y + ")", game.cellIsAlive(x, y), is(values[x][y]));
			}
		}		
	}
	
}
