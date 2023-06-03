package edu.asu.core.piece;

import edu.asu.core.Position;

/**
 * This abstract class serves a base class for different type of piece such as King and Man and their capabilities
 * It declares methods and attributed common to all pieces
 * @author Martin Alemajoh
 * @version 1.0
 */
public abstract class Piece {
    protected String symbol;

    protected Position position;

    /**
     * Constructs a piece by assigning it a symbol X or O
     * @param symbol a symbol X or O
     */
    public Piece(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Constructs a piece by assigning it a symbol and position
     * @param symbol a symbol X or O
     * @param position a position on the board
     */
    public Piece(String symbol, Position position){
        this(symbol);
        this.position = position;
    }

    /**
     * Returns a piece symbol
     * @return a symbol representing the piece
     */
    public abstract String getSymbol();

    /**
     * Returns the position of the piece on the board
     * @return the position of the piece on the board
     */
    public abstract Position getPosition();
}
