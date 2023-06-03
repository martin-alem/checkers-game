package edu.asu.core.piece;

import edu.asu.core.Position;

/**
 * This class represents a Man piece. A man piece is the regular piece on the checker's board with basic moves.
 * @author Martin Alemajoh
 * @version 1.0
 */
public class Man extends Piece{

    /**
     * Constructs a Man piece by assigning it a symbol X or O
     * @param symbol a symbol X or O
     */
    public Man(String symbol) {
        super(symbol);
    }

    /**
     * Constructs a Man piece by assigning it a symbol and position
     * @param symbol a symbol X or O
     * @param position a position on the board
     */
    public Man(String symbol, Position position){
        super(symbol, position);
    }

    /**
     * Returns a piece symbol
     * @return a symbol representing the piece
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Returns the position of the piece on the board
     * @return the position of the piece on the board
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

}
