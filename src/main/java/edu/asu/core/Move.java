package edu.asu.core;


import edu.asu.util.Letter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a move made on the board
 * @author Martin Alemajoh
 * @version 1.0
 */
public class Move {
    private List<Position> moves;

    /**
     * Constructs a move
     * @param moves moves
     */
    public Move(List<Position> moves) {
        this.moves = moves;
    }

    /**
     * Returns moves
     * @return moves
     */
    public List<Position> getMoves() {
        return this.moves;
    }

    /**
     * Converts moves entered by the user into a move instance
     * @param move player moves as string
     * @return a move
     */
    public static Move toMoves(String move){
        List<Position> playerMoves = new ArrayList<>();
        String[] moves = move.split("-");

        for(String decodedMove : moves){
            int row = Integer.parseInt(String.valueOf(decodedMove.charAt(0))) - 1;
            int col = Letter.valueOf(String.valueOf(decodedMove.charAt(1))).ordinal();
            Position position = new Position(row, col);
            playerMoves.add(position);
        }
        return new Move(playerMoves);
    }

    /**
     * String representation of a move
     * @return string representation of a move
     */
    public String toString(){
        return moves.toString();
    }
}
