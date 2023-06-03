package edu.asu.core.player;

import edu.asu.core.Move;
import edu.asu.core.board.CheckerBoard;
import edu.asu.core.piece.Piece;

/**
 * This abstract class severs as a base class for the different types of players: Human or Computer
 * @author Martin Alemajoh
 * @version 1.0
 */
public abstract class Player {
    protected String playerName;
    protected Piece piece;

    /**
     * Constructs a new player
     * @param playerName player's name
     * @param piece player's piece
     */
    public Player(String playerName, Piece piece){
        this.playerName = playerName;
        this.piece = piece;
    }

    /**
     * Returns a players name
     * @return player's name
     */
    public abstract String getPlayerName();

    /**
     * Returns a player's piece
     * @return player's piece
     */
    public abstract Piece getPiece();

    /**
     * Makes a move
     * @param moves player's move
     * @param board board instance
     * @return a boolean representing the result of the move. True if move was successfully made and false otherwise
     */
    public abstract boolean makeMove(Move moves, CheckerBoard board);
}
