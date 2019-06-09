package game;

/**
 * <insert>
 * 
 * @author Eli Musgrove
 */
public class Pawn {

	/**
	 * The current row of this Pawn.
	 */
	private int row;

	/**
	 * The current column of this Pawn.
	 */
	private int col;

	/**
	 * The player that owns this Pawn.
	 */
	private final Player player;

	/**
	 * Constructs a Pawn.
	 * 
	 * @param row row for this Pawn to be placed into
	 * @param col column for this Pawn to be placed into
	 */
	public Pawn(int row, int col, Player player) {
		this.row = row;
		this.col = col;
		this.player = player;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other given Pawn to copy
	 */
	public Pawn(Pawn other) {
		this.row = other.row;
		this.col = other.col;
		this.player = other.player;
	}

	/**
	 * Moves this Pawn to specified row and column. Destination must be on the 3*3
	 * GameBoard.
	 * 
	 * @param x number of rows to move
	 * @param y number of columns to move
	 */
	public boolean move(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			return false;
		}

		this.row = row;
		this.col = col;
		return true;
	}

	/**
	 * Getter for the Player that owns this Pawn.
	 * 
	 * @return the Player that owns this Pawn
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Returns true if this Pawn is equal to the given Pawn, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Pawn other = (Pawn) obj;
		return (other.row == this.row && other.col == this.col);
	}

	/**
	 * Returns a string describing this Pawn's location.
	 */
	@Override
	public String toString() {
		return (player == Player.HUMAN) ? "O" : "8";
	}
}
