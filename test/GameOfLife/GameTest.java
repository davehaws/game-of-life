package GameOfLife;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	private Game game = new Game();
	
	@Test
	public void game_has_access_to_board() {
		assertThat(game.getBoard(), is(notNullValue()));
	}
	
	
}
