package edu.asu.ui;

import java.util.Map;

/**
 * This interface serves as a contract to be fulfilled by any class that wants to take up the responsibility of being UI
 * @author Martin Alemajoh
 * @version 1.0
 */
public interface UI {

    /**
     * Renders a game menu for the player. The user will enter their name and select a board type
     * @return A map with the input entered by the user.
     */
     Map<String,String> renderGameMenu();

    /**
     * Renders a goodbye screen when the game ends
     */
    void renderGoodbyeScreen();

    /**
     * Renders the game board
     */
     void renderGameBoard();

    /**
     * Displays a string representation of the game's messages. This could be any message the games may want to display
     * @param gameState game state
     */
    void renderGameMessages(String gameState);

    /**
     * Gets and validates the move from the user.
     * @param moveRegex a regex used to validate the move based on the board type
     * @return a string representing the move
     */
    String getMove(String moveRegex);
}
