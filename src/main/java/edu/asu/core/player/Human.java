package edu.asu.core.player;

import edu.asu.core.Move;
import edu.asu.core.board.CheckerBoard;
import edu.asu.core.piece.Piece;

/**
 * This class represents a Human player
 * @author Martin Alemajoh
 * @version 1.0
 */
public class Human extends Player {

    /**
     * Constructs a human player
     * @param playerName player name
     * @param piece player's piece
     */
    public Human(String playerName, Piece piece) {
        super(playerName, piece);
    }

    /**
     * Returns a players name
     * @return player's name
     */
    @Override
    public String getPlayerName() {
        return null;
    }

    /**
     * Returns a player's piece
     * @return player's piece
     */
    @Override
    public Piece getPiece() {
        return null;
    }

    /**
     * Makes a move
     * @param moves player's move
     * @param board board instance
     * @return a boolean representing the result of the move. True if move was successfully made and false otherwise
     */
    @Override
    public boolean makeMove(Move moves, CheckerBoard board) {
        boolean isValidMove = false;
        if (moves.getMoves().size() == 2) {
            System.out.println("A move");
            isValidMove = board.executeMove(moves, this);
        } else if (moves.getMoves().size() % 2 != 0) {
            System.out.println("A capture");
           isValidMove = board.executeCaptureMove(moves, this);
        }
        return isValidMove;
    }

}