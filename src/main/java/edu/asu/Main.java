package edu.asu;

import edu.asu.controller.GameController;
import edu.asu.ui.UI;
import edu.asu.ui.console.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();
        UI console = new ConsoleUI(gameController);
        gameController.setUI(console);
        gameController.startGame();
    }
}