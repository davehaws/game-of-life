package GameOfLife;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static GameOfLife.Location.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void can_create_a_location_for_two_dimensions() {
		Location location = new Location(2,3);
		assertThat(location.get(X), is(2));
		assertThat(location.get(Y), is(3));
	}
	
	@Test
	public void can_output_a_human_readable_state_for_the_location() {
		Location location = new Location(2, 7);
		assertThat(location.toString(), is("(X:2,Y:7)"));
	}

}
