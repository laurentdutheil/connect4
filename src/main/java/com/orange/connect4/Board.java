package com.orange.connect4;

public class Board {

    public static final int LINES = 6;
    public static final int COLUMNS = 7;
    public static final char NO_TOKEN = '.';

    char[][] grid;

    public Board() {
        grid = new char[LINES][COLUMNS];
        init();
    }

    private void init() {
        for (int line = 0; line < LINES; line++) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[line][col] = NO_TOKEN;
            }
        }
    }

    public void empty() {
        init();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int line = LINES - 1; line >= 0; line--) {
            for (int col = 0; col < COLUMNS; col++) {
                result.append(grid[line][col]);
            }
            if (line != 0) result.append("\n");
        }
        return result.toString();
    }

    public void addToken(int col, char token) {
        if (countTokens(col) >= LINES)
            throw new InvalidMoveException();
        grid[countTokens(col)][col] = token;
    }

    public char getToken(int line, int col) {
        return grid[line][col];
    }

    private int countTokens(int col) {
        int count = 0;
        for (int line = 0; line < LINES; line++) {
            if (grid[line][col] != NO_TOKEN) count++;
        }
        return count;
    }

}
