package edu.asu.ui.console;

import edu.asu.controller.GameController;
import edu.asu.core.piece.Piece;
import edu.asu.ui.UI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class serves the UI for the Game. It's a console based UI which has implemented the UI interface.
 * @author Martin Alemajoh
 * @version 1.0
 */
public class ConsoleUI implements UI {
    GameController gameController;

    /**
     * Constructs a console UI
     * @param gameController game controller instance
     */
    public ConsoleUI(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * Renders a game menu for the player. The user will enter their name and select a board type
     * @return A map with the input entered by the user.
     */
    @Override
    public Map<String, String> renderGameMenu() {
        System.out.println("Welcome To Checker Game");

        Scanner scanner = new Scanner(System.in);
        Pattern playerNamePattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Pattern boardTypePattern = Pattern.compile("\\b(?:8|10|12)\\b");
        String boardType = "";
        String player1Name = "";
        String player2Name = "";
        String option = "";

        boolean isPlayer1NameValid = playerNamePattern.matcher(player1Name).matches();
        boolean isPlayer2NameValid = playerNamePattern.matcher(player2Name).matches();

        while(!isPlayer1NameValid){
            System.out.println("Enter Player 1 Name:");
            player1Name = scanner.next();
            isPlayer1NameValid = playerNamePattern.matcher(player1Name).matches();
        }

        while(!isPlayer2NameValid){
            System.out.println("Enter Player 2 Name:");
            player2Name = scanner.next();
            isPlayer2NameValid = playerNamePattern.matcher(player2Name).matches();
        }

        boolean isValidBoardType = boardTypePattern.matcher(boardType).matches();

        while(!isValidBoardType){
            System.out.println("What board type would you like to use:\n");
            System.out.println("8 - Standard 8x8");
            System.out.println("10 - Standard 10x10");
            System.out.println("12 - Standard 12x12");

            boardType = scanner.next();
            isValidBoardType = boardTypePattern.matcher(boardType).matches();
        }

        while(!option.equalsIgnoreCase("S") && !option.equalsIgnoreCase("E")){
            System.out.println("Enter S to start or E to end the game:");
            option = scanner.next();
        }

        Map<String, String> payload = new HashMap<>();
        payload.put("player1Name", player1Name);
        payload.put("player2Name", player2Name);
        payload.put("boardType", boardType);
        payload.put("option", option);
        return payload;
    }

    /**
     * Renders a goodbye screen when the game ends
     */
    @Override
    public void renderGoodbyeScreen() {
        System.out.println("Goodbye...");
    }

    /**
     * Renders the game board
     */
    @Override
    public void renderGameBoard() {
        Piece[][] board = this.gameController.getBoardState();
        System.out.println("   A  B  C  D  E  F  G  H");
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + 1 + " ");
            for (int col = 0; col < board.length; col++) {
                if(board[row][col] != null){
                    System.out.print("["+board[row][col].getSymbol()+ "]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println(" " + (row + 1));
        }
        System.out.println("   A  B  C  D  E  F  G  H");
    }

    /**
     * Displays a string representation of the game's messages. This could be any message the games may want to display
     * @param gameState game state
     */
    @Override
    public void renderGameMessages(String gameState) {
        System.out.println(gameState);
    }

    /**
     * Gets and validates the move from the user.
     * @param moveRegex a regex used to validate the move based on the board type
     * @return a string representing the move
     */
    @Override
    public String getMove(String moveRegex) {
        Scanner scanner = new Scanner(System.in);
        Pattern movePattern = Pattern.compile(moveRegex);
        String playerMove = scanner.next();
        boolean isValidPlayerMove = movePattern.matcher(playerMove).matches();
        while(!isValidPlayerMove){
            System.out.println("Invalid move: Try again");
            playerMove = scanner.next();
            isValidPlayerMove = movePattern.matcher(playerMove).matches();
        }
        return playerMove;
    }
}
