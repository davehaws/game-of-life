package GameOfLife;


public class Cell {

	private static final int birthLevel = 3;
	private static final int lonelyLevel = 1;
	private static final int crowdedLevel = 4;
	
	public enum State {
		DEAD,
		ALIVE
	}
	
	private State state;
	public Cell(boolean isAlive) {
		this.state = isAlive ? State.ALIVE : State.DEAD;
	}

	public Cell() {
		state = State.DEAD;
	}

	public Boolean isAlive() {
		return state == State.ALIVE;
	}

	public Cell nextGen(int numNeighbors) {
		Boolean willBeAlive = false;
		if (is(State.ALIVE) && numNeighbors > lonelyLevel && numNeighbors < crowdedLevel) {
			willBeAlive = true;
		} else if (is(State.DEAD) && numNeighbors == birthLevel) {
			willBeAlive = true;
		}
		return new Cell(willBeAlive);
	}

	public Boolean is(State state) {
		// TODO Auto-generated method stub
		return this.state.equals(state);
	}

	public void setState(State state) {
		this.state = state;
		
	}

}