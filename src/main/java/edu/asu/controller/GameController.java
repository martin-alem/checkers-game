package edu.asu.controller;

import edu.asu.core.Move;
import edu.asu.core.board.CheckerBoard;
import edu.asu.core.piece.Man;
import edu.asu.core.piece.Piece;
import edu.asu.core.player.Human;
import edu.asu.core.player.Player;
import edu.asu.ui.UI;
import edu.asu.util.BoardFactory;
import edu.asu.util.PlayerSymbol;
import java.util.Map;

/**
 * This class represents the Game Controller. It is responsible for coordinating the interaction
 * Between the Game Model and the Game UI similar to an MVC architecture.
 * @author Martin Alemajoh
 * @version 1.0
 */
public class GameController {
    private CheckerBoard checkerBoard;
    private Player[] players;
    private Player currentPlayer;
    private UI ui;

    /**
     * Sets up the Game UI
     * @param ui Game UI instance
     */
    public void setUI(UI ui){
        this.ui = ui;
    }

    /**
     * Sets up the board type to be used for the game.
     * 8x8, 10x10 etc.
     * @param boardSize the board dimension
     */
    private void setBoard(int boardSize){
        this.checkerBoard = new BoardFactory().createBoard(boardSize);
    }

    /**
     * Starts the game. This method is called my a driver class to start the game.
     * It calls the UI to render the game menu.
     * It sets up the board.
     * It initializes players.
     * It initializes the game board and calls the UI to render it and starts the game loop
     */
    public void startGame(){
        Map<String, String> userResponse = this.ui.renderGameMenu();
        String player1Name = userResponse.get("player1Name");
        String player2Name = userResponse.get("player2Name");
        String boardType = userResponse.get("boardType");
        String option = userResponse.get("option");

        if(option.equalsIgnoreCase("E")){
            ui.renderGoodbyeScreen();
            System.exit(0);
            return;
        }

        //initialize players
        this.setBoard(Integer.parseInt(boardType));
        this.initializePlayers(player1Name, player2Name);
        this.checkerBoard.initializeBoard(players[0], players[1]);
        this.ui.renderGameBoard();
        String gameState =
                "Player 1: " +
                players[0].getPlayerName() + ": " +
                players[0].getPiece().getSymbol() + " - " +
                this.checkerBoard.numberOfPieces(players[0].getPiece().getSymbol())+ "\n" +
                "Player 2: " +
                players[1].getPlayerName() + ": " +
                players[1].getPiece().getSymbol()+ " - " +
                        this.checkerBoard.numberOfPieces(players[1].getPiece().getSymbol())+ "\n";
        this.ui.renderGameMessages(gameState);
        this.currentPlayer = players[0];
        this.gameLoop();
    }

    /**
     * Initializers the players
     * @param player1Name player1's name
     * @param player2Name player2's name
     */
    private void initializePlayers(String player1Name, String player2Name){
        Piece xPiece = new Man(PlayerSymbol.X.getValue());
        Piece oPiece = new Man(PlayerSymbol.O.getValue());
        this.players = new Player[]{new Human(player1Name, oPiece), new Human(player2Name, xPiece)};
    }

    /**
     * Switches players after each player's turn
     */
    private void switchPlayers(){
        if(currentPlayer.getPiece().getSymbol().equalsIgnoreCase(PlayerSymbol.X.getValue())){
            currentPlayer = players[0];
        }else{
            currentPlayer = players[1];
        }
    }

    /**
     * Returns the game board state
     * @return the game state
     */
    public Piece[][] getBoardState() {
        return this.checkerBoard.getBoardState();
    }

    /**
     * Runs infinitely until a winner emerges or a tie or a block is detected.
     */
    private void gameLoop(){
        while(true){
            this.ui.renderGameMessages("["+this.checkerBoard.numberOfPieces(currentPlayer.getPiece().getSymbol())+ " - "+currentPlayer.getPiece().getSymbol()+ "] "+ currentPlayer.getPlayerName()+ " Your turn");
            boolean isValidMove = false;
            while(!isValidMove){
                String playerMove = this.ui.getMove(this.checkerBoard.getMOVE_REGEX());
                isValidMove = currentPlayer.makeMove(Move.toMoves(playerMove), this.checkerBoard);
            }
            this.ui.renderGameBoard();
            if(this.checkerBoard.won()){
                this.ui.renderGameMessages(currentPlayer.getPlayerName() + " Won!!!!!!!!!");
                this.ui.renderGoodbyeScreen();
                System.exit(0);
            }
            this.switchPlayers();
        }
    }

}
