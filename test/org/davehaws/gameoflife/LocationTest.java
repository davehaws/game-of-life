package org.davehaws.gameoflife;
import static org.davehaws.gameoflife.Location.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.davehaws.gameoflife.Location;
import org.junit.Test;

public class LocationTest {

	@Test
	public void can_create_a_location_for_two_dimensions() {
		Location location = new Location(2,3);
		assertThat(location.get(X), is(2));
		assertThat(location.get(Y), is(3));
	}
	
	@Test
	public void can_change_location_cooridnates_two_dimensions() {
		Location location = new Location(2,3);
		location.set(X, 4);
		location.set(Y, 6);
		assertThat(location.get(X), is(4));
		assertThat(location.get(Y), is(6));
	}
	
	@Test
	public void can_output_a_human_readable_state_for_the_location() {
		Location location = new Location(2, 7);
		assertThat(location.toString(), is("(X:2,Y:7)"));
	}

}
