package game;

/**
 * <insert>
 * 
 * @author Eli Musgrove
 */
public class Game {

	/**
	 * The current round of this Game.
	 */
	private int round;
	
	/**
	 * The player that is currently moving.
	 */
	private Player player;
	
	/**
	 * The current GameBoard.
	 */
	private GameBoard board;
	
	/**
	 * True if the game is over, false otherwise.
	 */
	private boolean complete;
	
	/**
	 * Constructs a single game of Hexapawn.
	 */
	public Game() {
		this.round = 1;
		this.player = Player.HUMAN;
		this.board = new GameBoard();
		this.complete = false;
	}
	
	// TODO: Stop the current moving player from selecting opponents pieces to move
}
