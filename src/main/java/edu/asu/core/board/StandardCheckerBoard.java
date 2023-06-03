package edu.asu.core.board;

import edu.asu.core.Move;
import edu.asu.core.Position;
import edu.asu.core.piece.Man;
import edu.asu.core.piece.Piece;
import edu.asu.core.player.Player;
import edu.asu.util.PlayerSymbol;

/**
 * This class represents standard 8x8 checkerboard.
 * It defines the regex used for validation and the board size
 * @author Martin Alemajoh
 * @version 1.0
 */
public class StandardCheckerBoard extends CheckerBoard{

    private static final int BOARD_SIZE = 8;

    /**
     * Constructs and 8x8 checkerboard
     */
    public StandardCheckerBoard() {
        super(BOARD_SIZE,BOARD_SIZE); // Call to superclass constructor
    }

    /**
     * Returns the board state
     * @return the board state
     */
    @Override
    public Piece[][] getBoardState() {
        return this.grid;
    }

    /**
     * Return the regular expression used to valid moves
     * @return a regex used to valid a move on the board
     */
    public String getMOVE_REGEX() {
        return "[1-8][A-H](-[1-8][A-H])*-[1-8][A-H]$";
    }

    /**
     * Returns the count left for a piece on the board
     * @param playerSymbol player symbol also referred to as piece
     * @return the count left for a piece on the board
     */
    public int numberOfPieces(String playerSymbol) {
        int total = 0;
        for (Piece[] pieces : this.grid) {
            for (Piece piece : pieces) {
                if (piece != null && playerSymbol.equalsIgnoreCase(piece.getSymbol())) {
                    total += 1;
                }
            }
        }
        return total;
    }

    /**
     * Initializes the checkerboard
     * @param player1 player 1 instance
     * @param player2 player 2 instance
     */
    @Override
    public void initializeBoard(Player player1, Player player2) {
        // Set all spots to empty initially
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.grid[i][j] = null;
            }
        }

        // Place the pieces for the first player
        for(int i = 0; i < 3; i++) {
            for(int j = (i+1) % 2; j < 8; j+=2) {
                this.grid[i][j] = new Man(PlayerSymbol.O.getValue(), new Position(i, j));
            }
        }

        // Place the pieces for the second player
        for(int i = 5; i < 8; i++) {
            for(int j = (i+1) % 2; j < 8; j+=2) {
                this.grid[i][j] = new Man(PlayerSymbol.X.getValue(), new Position(i, j));
            }
        }
    }

    /**
     * Updates a move made by a player. A move is represented by a start and end position
     * Must be within the bound of the board
     * @param origin the origin of the move
     * @param destination the destination of the move
     */
    @Override
    public void updateMove(Position origin, Position destination) {
        this.getBoardState()[destination.getRow()][destination.getColumn()] = this.getBoardState()[origin.getRow()][origin.getColumn()];
        this.getBoardState()[origin.getRow()][origin.getColumn()] = null;
    }

    /**
     * Updates a capture move. A capture move is a move attempted to capture opponents piece.
     * It is represented by a start position capture piece position and destination
     * @param captured the position of the piece to be captured
     */
    @Override
    public void updateCaptureMove(Position captured) {
        this.getBoardState()[captured.getRow()][captured.getColumn()] = null;
    }

    /**
     * Attempts to make a capture.
     * @param moves Player's move
     * @param player Player
     * @return a boolean representing the result of the execution. True if the execution was successful and false otherwise
     */
    public boolean executeCaptureMove(Move moves, Player player) {
        int lengthOfMoves = moves.getMoves().size() / 2;

        for (int i = 0; i <= lengthOfMoves; i += 2) {
            Position origin = moves.getMoves().get(i);
            Position capture = moves.getMoves().get(i + 1);
            Position destination = moves.getMoves().get(i + 2);
            if (!this.isValidCaptureMove(origin, capture, destination, player)) {
                System.out.println("Invalid capture move");
                return false;
            }
            this.updateMove(origin, destination);
            this.updateCaptureMove(capture);
        }
        return true;
    }

    /**
     * Attempts to make a move
     * @param moves Players move
     * @param player Player
     * @return a boolean representing the result of execution. True if the execution was successful and false otherwise
     */
    public boolean executeMove(Move moves, Player player){
        if(!this.isValidMove(moves, player)){
           System.out.println("Invalid move");
           return false;
        }
        this.updateMove(moves.getMoves().get(0), moves.getMoves().get(1));
        return true;
    }

    /**
     * Validates if a move made by a player is valid
     * @param moves Player's move
     * @param player Player
     * @return a boolean representing the result of validation. True if the validation was successful and false otherwise
     */
    public boolean isValidMove(Move moves, Player player) {
        Position origin = moves.getMoves().get(0);
        Position destination = moves.getMoves().get(1);
        String playerSymbol = player.getPiece().getSymbol();

        if (this.getBoardState()[origin.getRow()][origin.getColumn()] == null) {
            System.out.println("Origin can't be empty");
            return false;
        }

        if (!this.getBoardState()[origin.getRow()][origin.getColumn()].getSymbol().equalsIgnoreCase(playerSymbol)) {
            System.out.println("You can't move opponent's piece");
            return false;
        }

        if (this.getBoardState()[destination.getRow()][destination.getColumn()] != null) {
            System.out.println("Destination must be empty");
            return false;
        }

        boolean validDiagonal = isValidForwardDiagonalMove(origin, destination, player);
        if (!validDiagonal) {
            System.out.println("Origin must be a valid diagonal with destination");
            return false;
        }
        return true;
    }

    /**
     * Checks if there is a tie. A tie occurs when neither players can make a move nor a capture
     * @return a boolean representing the result of the check. True if there is a tie and false otherwise
     */
    @Override
    public boolean isTie() {
        return false;
    }


    /**
     * Checks if there is a block. A block occurs when a player can neither make a move nor a capture
     * @return a boolean representing the result of the check. True if there is a block and false otherwise
     */
    @Override
    public boolean isBlocked() {
        return false;
    }

    /**
     * Checks if there is a win. A win occurs when one player has zero pieces or one player is blocked
     * @return a boolean representing the result of the check. True if there is a win and false otherwise
     */
    @Override
    public boolean won() {
        return this.numberOfPieces(PlayerSymbol.X.getValue()) == 0 || this.numberOfPieces(PlayerSymbol.O.getValue()) == 0;
    }

    /**
     * Validates if a move, based on the player is valid. If a player uses the top portion of the board they can only move downwards
     * Similarly if a player uses the bottom portion of the board, they can only move upwards
     * @param origin The origin of the move.
     * @param destination The destination of the move
     * @param player The player
     * @return a boolean representing the validation. True if move is valid and false otherwise
     */
    public boolean isValidForwardDiagonalMove(Position origin, Position destination, Player player) {
        String symbol = player.getPiece().getSymbol();
        Position leftForwardDiagonal = new Position(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Position rightForwardDiagonal = new Position(Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (symbol.equalsIgnoreCase(PlayerSymbol.X.getValue())) {
            leftForwardDiagonal.setRow(origin.getRow() - 1);
            leftForwardDiagonal.setColumn(origin.getColumn() - 1);
            rightForwardDiagonal.setRow(origin.getRow() - 1);
            rightForwardDiagonal.setColumn(origin.getColumn() + 1);
        } else if (symbol.equalsIgnoreCase(PlayerSymbol.O.getValue())) {
            leftForwardDiagonal.setRow(origin.getRow() + 1);
            leftForwardDiagonal.setColumn(origin.getColumn() - 1);
            rightForwardDiagonal.setRow(origin.getRow() + 1);
            rightForwardDiagonal.setColumn(origin.getColumn() + 1);
        }

        return leftForwardDiagonal.getRow() == destination.getRow() &&
                leftForwardDiagonal.getColumn() == destination.getColumn() ||
                rightForwardDiagonal.getRow() == destination.getRow() &&
                        rightForwardDiagonal.getColumn() == destination.getColumn();
    }

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
    public boolean isValidCaptureMove(Position origin, Position capturePiece, Position destination, Player player) {
        String playerSymbol = player.getPiece().getSymbol();

        if (!this.isValidForwardDiagonalMove(origin, capturePiece, player)) {
            System.out.println("Must be a valid forward diagonal");
            return false;
        }

        if (this.getBoardState()[origin.getRow()][origin.getColumn()] == null) {
            System.out.println("Origin can't be empty");
            return false;
        }

        if (!this.getBoardState()[origin.getRow()][origin.getColumn()].getSymbol().equalsIgnoreCase(playerSymbol)) {
            System.out.println("You can't move opponent's piece");
            return false;
        }

        if (this.getBoardState()[capturePiece.getRow()][capturePiece.getColumn()] == null) {
            System.out.println("Capture piece can't be empty");
            return false;
        }

        if (this.getBoardState()[capturePiece.getRow()][capturePiece.getColumn()].getSymbol().equalsIgnoreCase(playerSymbol)) {
            System.out.println("Can't capture your own piece");
            return false;
        }

        if (this.getBoardState()[destination.getRow()][destination.getColumn()] != null) {
            System.out.println("Destination must be empty");
            return false;
        }

        boolean validDiagonal = isValidForwardDiagonalMove(capturePiece, destination, player);
        if (!validDiagonal) {
            System.out.println("Destination must be a valid forward diagonal with capture piece");
            return false;
        }

        return true;
    }
}
