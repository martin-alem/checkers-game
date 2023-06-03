package edu.asu.core.piece;

import edu.asu.core.Position;

/**
 * This class represents a King piece. A King piece is a superior piece on the checker's board with advance moves.
 * @author Martin Alemajoh
 * @version 1.0
 */
public class King extends Piece{

    /**
     * Constructs a king piece by assigning it a symbol X or O
     * @param symbol a symbol X or O
     */
    public King(String symbol) {
        super(symbol);
    }

    /**
     * Constructs a King piece by assigning it a symbol and position
     * @param symbol a symbol X or O
     * @param position a position on the board
     */
    public King(String symbol, Position position){
        super(symbol, position);
    }

    /**
     * Returns a piece symbol
     * @return a symbol representing the piece
     */
    @Override
    public String getSymbol() {
        return null;
    }

    /**
     * Returns the position of the piece on the board
     * @return the position of the piece on the board
     */
    @Override
    public Position getPosition() {
        return null;
    }
}
