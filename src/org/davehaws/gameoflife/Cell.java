package org.davehaws.gameoflife;


public class Cell {

	private static final int birthLevel = 3;
	private static final int lonelyLevel = 1;
	private static final int crowdedLevel = 4;
	
	public enum State {
		DEAD,
		ALIVE
	}
	
	private State state;
	private State nextState;
	
	public Cell(State state) {
		this.state = state;
		nextState = State.DEAD;
	}

	public Cell() {
		state = State.DEAD;
		nextState = State.DEAD;
	}

	public Boolean isAlive() {
		return state == State.ALIVE;
	}

	public Boolean is(State state) {
		return this.state.equals(state);
	}

	public void setState(State state) {
		this.state = state;
		
	}

	public void setNextState(State state) {
		nextState = state;
		
	}

	public void evolve() {
		state = nextState;
		nextState = State.DEAD;
	}

}
