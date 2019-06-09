package game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representation of the current state of the game.
 * 
 * @author Eli Musgrove
 */
public class GameBoard {

	/**
	 * The current GameBoard.
	 */
	protected HashMap<Integer, Square> gameboard;

	/**
	 * Constructs a GameBoard with the following initial positions:
	 * 
	 * 8 8 8 - - - O O O
	 * 
	 * (O: human player, 8: computer, -: empty square)
	 */
	public GameBoard() {
		this.gameboard = new HashMap<Integer, Square>();

		this.gameboard.put(0, new Square(0, 0, new Pawn(0, 0, Player.COMPUTER)));
		this.gameboard.put(1, new Square(0, 1, new Pawn(0, 1, Player.COMPUTER)));
		this.gameboard.put(2, new Square(0, 2, new Pawn(0, 2, Player.COMPUTER)));
		this.gameboard.put(3, new Square(1, 0, null));
		this.gameboard.put(4, new Square(1, 1, null));
		this.gameboard.put(5, new Square(1, 2, null));
		this.gameboard.put(6, new Square(2, 0, new Pawn(2, 0, Player.HUMAN)));
		this.gameboard.put(7, new Square(2, 1, new Pawn(2, 1, Player.HUMAN)));
		this.gameboard.put(8, new Square(2, 2, new Pawn(2, 2, Player.HUMAN)));
	}

	/**
	 * Getter for the GameBoard.
	 * 
	 * @return current GameBoard as a LinkedHashMap.
	 */
	public HashMap<Integer, Square> getGameBoard() {
		return this.gameboard;
	}

	/**
	 * Moves the given Pawn to the new Square if the move is possible.
	 * 
	 * @param start beginning square
	 * @param end   end square
	 * @return true if the move was successful, false otherwise
	 */
	public boolean move(int start, int end) {

		// No possible moves
		ArrayList<Integer> moves = getPossibleMoves(start);
		if (moves.size() == 0 || !moves.contains(end)) {
			return false;
		}
		
		Square startSquare = gameboard.get(start);
		Square endSquare = gameboard.get(end);
		Pawn pawn = gameboard.get(start).getPawn();
		
		// Update end location
		gameboard.put(end, new Square(endSquare.row(), endSquare.col(), new Pawn(pawn)));
		
		// Update starting location
		gameboard.put(start, new Square(startSquare.row(), startSquare.col(), null));
		
		return true;
	}

	/**
	 * Returns an array of all possible moves of a Pawn on a given Square.
	 * 
	 * @param start given Square that a Pawn is placed on
	 * @return array containing all possible destination squares
	 */
	public ArrayList<Integer> getPossibleMoves(int start) {
		ArrayList<Integer> ret = new ArrayList<>();

		// No Pawn in the selected Square
		if (start < 0 || start > 8 || gameboard.get(start).getPawn() == null) {
			return ret;
		}

		Player player = this.gameboard.get(start).getPawn().getPlayer();
		
		// Human player
		if (player == Player.HUMAN) {
			// Next row would be off the board
			if (start < 3) {
				return ret;
			}

			// Row the piece would move to
			int moveRow = gameboard.get(start).row() - 1;
			for (int i = 0; i < 9; i++) {
				// Add possible moves, for corner pieces exclude certain impossible moves
				if (gameboard.get(i).row() == moveRow && start - 1 != i && start - 5 != i) {
					ret.add(i);
				}
			}

			// Eliminate forward move if a Pawn is there
			if (gameboard.get(start - 3).getPawn() != null) {
				ret.remove(new Integer(start - 3));
			}

			// Eliminate diagonal move if a friendly Pawn is there
			for (int i = 0; i < ret.size(); i++) {
				if (gameboard.get(ret.get(i)).getPawn() != null
						&& gameboard.get(ret.get(i)).getPawn().getPlayer() == Player.HUMAN) {
					ret.remove(i);
					i++;
				}
			}
		}

		// Computer player
		else {
			// Next row would be off the board
			if (start > 5) {
				return ret;
			}

			// Row the piece would move to
			int moveRow = gameboard.get(start).row() + 1;
			for (int i = 0; i < 9; i++) {
				// Add possible moves, for corner pieces exclude certain impossible moves
				if (gameboard.get(i).row() == moveRow && start + 1 != i && start + 5 != i) {
					ret.add(i);
				}
			}

			// Eliminate forward move if a piece is there
			if (gameboard.get(start + 3).getPawn() != null) {
				ret.remove(new Integer(start + 3));
			}

			// Eliminate diagonal move if a friendly Pawn is there
			for (int i = 0; i < ret.size(); i++) {
				if (gameboard.get(ret.get(i)).getPawn() != null
						&& gameboard.get(ret.get(i)).getPawn().getPlayer() == Player.COMPUTER) {
					ret.remove(i);
					i++;
				}
			}
		}

		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		GameBoard other = (GameBoard) obj;
		for (Square square : this.gameboard.values()) {

			if (!other.gameboard.containsValue(square)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of this GameBoard, used for debug only.
	 */
	@Override
	public String toString() {
		String ret = "";
		Pawn pawn;

		for (int i = 0; i < 9; i++) {
			pawn = gameboard.get(i).getPawn();
			ret += (pawn == null) ? "-" : pawn.toString();
			ret += " ";

			if (i == 2 || i == 5) {
				ret += "\n";
			}
		}

		return ret;
	}
}
