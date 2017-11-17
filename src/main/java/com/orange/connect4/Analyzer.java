package com.orange.connect4;

import java.util.stream.IntStream;

public class Analyzer {
    private static final int LAST_LINE_TO_CHECK = Board.LINES - 3;
    private static final int LAST_COLUMN_TO_CHECK = Board.COLUMNS - 3;
    private static final int NO_INCREMENT = 0;
    public static final int INCREMENT = 1;
    public static final int DECREMENT = -1;

    private Board board;

    public Analyzer(Board board) {
        this.board = board;
    }

    public boolean hasWinner() {
        return hasLineWinner()
                || hasColumnWinner()
                || hasRisingDiagonalWinner()
                || hasFallingDiagonalWinner();
    }

    private boolean hasFallingDiagonalWinner() {
        return IntStream.range(0, LAST_COLUMN_TO_CHECK)
                .anyMatch(this::hasFallingDiagonalWinnerFromColumn);
    }

    private boolean hasFallingDiagonalWinnerFromColumn(int startColumn) {
        return IntStream.range(Board.LINES - LAST_LINE_TO_CHECK, Board.LINES)
                .anyMatch(lineIndex -> isConnected(lineIndex, startColumn, DECREMENT, INCREMENT));
    }

    private boolean hasRisingDiagonalWinner() {
        return IntStream.range(0, LAST_COLUMN_TO_CHECK)
                .anyMatch(this::hasRisingDiagonalWinnerFromColumn);
    }

    private boolean hasRisingDiagonalWinnerFromColumn(int columnIndex) {
        return IntStream.range(0, LAST_LINE_TO_CHECK)
                .anyMatch(lineIndex -> isConnected(lineIndex, columnIndex, INCREMENT, INCREMENT));
    }

    private boolean hasColumnWinner() {
        return IntStream.range(0, Board.COLUMNS)
                .anyMatch(this::hasColumnWinnerOnColumn);
    }

    private boolean hasColumnWinnerOnColumn(int columnIndex) {
        return IntStream.range(0, LAST_LINE_TO_CHECK)
                .anyMatch(lineIndex -> isConnected(lineIndex, columnIndex, INCREMENT, NO_INCREMENT));
    }

    private boolean hasLineWinner() {
        return IntStream.range(0, Board.LINES)
                .anyMatch(this::hasLineWinnerOnLine);
    }

    private boolean hasLineWinnerOnLine(int lineIndex) {
        return IntStream.range(0, LAST_COLUMN_TO_CHECK)
                .anyMatch(columnIndex -> isConnected(lineIndex, columnIndex, NO_INCREMENT, INCREMENT));
    }

    private boolean isConnected(int startLine, int startColumn, int incLine, int incColumn) {
        return board.getToken(startLine, startColumn) != Board.NO_TOKEN
                && board.getToken(startLine, startColumn) == board.getToken(startLine + incLine, startColumn + incColumn)
                && board.getToken(startLine, startColumn) == board.getToken(startLine + 2 * incLine, startColumn + 2 * incColumn)
                && board.getToken(startLine, startColumn) == board.getToken(startLine + 3 * incLine, startColumn + 3 * incColumn);
    }



    public boolean drawGame() {
        return !hasWinner() && lastLineIsFull();
    }

    private boolean lastLineIsFull() {
        return IntStream.range(0, Board.COLUMNS)
                .allMatch(columnIndex -> board.getToken(Board.LINES - 1, columnIndex) != Board.NO_TOKEN);
    }
}
