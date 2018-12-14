package org.davehaws.gameoflife;

public class Game {

	private int dimension = 10;
	private Board board = new Board();
	
	public int getDimension() {
		return dimension;
	}

	public Board getBoard() {
		return board;
	}

}
