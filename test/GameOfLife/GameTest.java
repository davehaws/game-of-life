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
	
	
}
