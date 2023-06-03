package edu.asu.core;

/**
 * This class represents the x and y coordinate on the checker's board
 * @author Martin Alemajoh
 * @version 1.0
 */
public class Position {

    private int row;
    private int column;

    /**
     * Constructs a new position
     * @param row x coordinate
     * @param column y coordinate
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the x coordinate
     * @return the x coordinate
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the y coordinate
     * @return the y coordinate
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets the x coordinate
     * @param row x coordinate
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the y coordinate
     * @param column y coordinate
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * The string representation of a position
     * @return string representation of a position
     */
    public String toString(){
        return "["+row+" "+column+"]";
    }
}
