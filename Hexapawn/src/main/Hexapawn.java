package main;

import game.*;
import window.*;

/**
 * <insert>
 * 
 * @author Eli Musgrove
 */
public class Hexapawn {
	
	/**
	 * The total number of games played.
	 */
	private int gameNum = 1;

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		System.out.println(board.toString());
		
		board.move(0, 3);
		System.out.println("\n" + board.toString());
	}
}
