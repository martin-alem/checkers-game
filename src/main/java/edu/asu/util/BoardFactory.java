package edu.asu.util;

import edu.asu.core.board.CheckerBoard;
import edu.asu.core.board.StandardCheckerBoard;

public class BoardFactory {

    public CheckerBoard createBoard(int size) {
        if (size == 8) {
            return new StandardCheckerBoard();
        }
        throw new IllegalArgumentException("Invalid board size or we don't support it at the moment: " + size);
    }
}
