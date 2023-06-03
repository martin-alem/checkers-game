package edu.asu.util;

/**
 * Enum representing a player symbol X or O
 */
public enum PlayerSymbol {
    X("X"),
    O("O");

    private String value;

    PlayerSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
