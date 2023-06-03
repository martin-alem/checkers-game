package edu.asu.core.board;

import edu.asu.core.Move;
import edu.asu.core.Position;
import edu.asu.core.piece.Piece;
import edu.asu.core.player.Player;

/**
 * This abstract class serves as a base class for all types of checkerboards: 8x8 10x10 etc.
 * it declares methods and attributes common to all types of checkerboards.
 * @author Martin Alemajoh
 * @version 1.0
 */
public abstract class CheckerBoard {
    protected Piece[][] grid; // 2D array to represent the grid;

    /**
     * Constructs a 2D checkerboard
     * @param rows rows
     * @param columns columns
     */
    public CheckerBoard(int rows, int columns) {
        this.grid = new Piece[rows][columns];
    }

    /**
     * Returns the board state
     * @return the board state
     */
    public abstract Piece[][] getBoardState();

    /**
     * Return the regular expression used to valid moves
     * @return a regex used to valid a move on the board
     */
    public abstract String getMOVE_REGEX();

    /**
     * Returns the count left for a piece on the board
     * @param playerSymbol player symbol also referred to as piece
     * @return the count left for a piece on the board
     */
    public abstract int numberOfPieces(String playerSymbol);

    /**
     * Initializes the checkerboard
     * @param player1 player 1 instance
     * @param player2 player 2 instance
     */
    public abstract void initializeBoard(Player player1, Player player2);

    /**
     * Updates a move made by a player. A move is represented by a start and end position
     * Must be within the bound of the board
     * @param origin the origin of the move
     * @param destination the destination of the move
     */
    public abstract void updateMove(Position origin , Position destination);

    /**
     * Updates a capture move. A capture move is a move attempted to capture opponents piece.
     * It is represented by a start position capture piece position and destination
     * @param captured the position of the piece to be captured
     */
    public abstract void updateCaptureMove(Position captured);

    /**
     * Attempts to make a capture.
     * @param moves Player's move
     * @param player Player
     * @return a boolean representing the result of the execution. True if the execution was successful and false otherwise
     */
    public abstract boolean executeCaptureMove(Move moves, Player player);

    /**
     * Validates if a move made by a player is valid
     * @param moves Player's move
     * @param player Player
     * @return a boolean representing the result of validation. True if the validation was successful and false otherwise
     */
    public abstract boolean isValidMove(Move moves, Player player);

    /**
     * Attempts to make a move
     * @param moves Players move
     * @param player Player
     * @return a boolean representing the result of execution. True if the execution was successful and false otherwise
     */
    public abstract boolean executeMove(Move moves, Player player);

    /**
     * Checks if there is a tie. A tie occurs when neither players can make a move nor a capture
     * @return a boolean representing the result of the check. True if there is a tie and false otherwise
     */
    public abstract boolean isTie();

    /**
     * Checks if there is a block. A block occurs when a player can neither make a move nor a capture
     * @return a boolean representing the result of the check. True if there is a block and false otherwise
     */
    public abstract boolean isBlocked();

    /**
     * Checks if there is a win. A win occurs when one player has zero pieces or one player is blocked
     * @return a boolean representing the result of the check. True if there is a win and false otherwise
     */
    public abstract boolean won();

    /**
     * Validates if a move, based on the player is valid. If a player uses the top portion of the board they can only move downwards
     * Similarly if a player uses the bottom portion of the board, they can only move upwards
     * @param origin The origin of the move.
     * @param destination The destination of the move
     * @param player The player
     * @return a boolean representing the validation. True if move is valid and false otherwise
     */
    public abstract boolean isValidForwardDiagonalMove(Position origin, Position destination, Player player);

    /**
     * Validates if a capture move is valid. A valid capture move must have a valid start position
     * The capture piece, start and destination position must be valid diagonals
     * destination must be empty.
     * @param origin The origin of the move
     * @param capturePiece The position of the opponent's piece
     * @param destination The destination of the move
     * @param player The player
     * @return a boolean representing the validation. True if capture is valid and false otherwise
     */
    public abstract boolean isValidCaptureMove(Position origin, Position capturePiece, Position destination, Player player);


}
