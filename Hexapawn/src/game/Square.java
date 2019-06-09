package game;

/**
 * Represents a Square on the GameBoard.
 * 
 * @author Eli Musgrove
 */
public class Square {

	/**
	 * Immutable row.
	 */
	private final int row;

	/**
	 * Immutable column.
	 */
	private final int col;

	/**
	 * The Pawn currently in this Square.
	 */
	private Pawn pawn;

	/**
	 * Constructs a Square given the row, column, and a Pawn (null for empty
	 * square).
	 * 
	 * @param row  given row
	 * @param col  given column
	 * @param pawn Pawn, null if empty square
	 */
	public Square(int row, int col, Pawn pawn) {
		this.row = row;
		this.col = col;
		this.pawn = pawn;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other given Square to copy
	 */
	public Square(Square other) {
		this.row = other.row;
		this.col = other.col;
		this.pawn = other.pawn;
	}

	/**
	 * Getter for the row.
	 * 
	 * @return row
	 */
	public int row() {
		return this.row;
	}

	/**
	 * Getter for the column
	 * 
	 * @return column
	 */
	public int col() {
		return this.col;
	}

	/**
	 * Returns the Pawn currently in this Square.
	 * 
	 * @return the Pawn currently in this Square
	 */
	public Pawn getPawn() {
		return this.pawn;
	}

	/**
	 * Updates the Pawn on this Square.
	 * 
	 * @param pawn given Pawn
	 */
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

	@Override
	public int hashCode() {
		return (2 * this.row + 3 * this.col);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Square other = (Square) obj;
		return (other.row == this.row && other.col == this.col);
	}
}
