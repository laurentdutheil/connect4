package com.orange.connect4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzerTest {

    Board board;
    Analyzer analyzer;

    @Before
    public void setup() {
        board = new Board();
        analyzer = new Analyzer(board);
    }

    @Test
    public void should_have_no_winner_on_empty_board() {
        assertThat(analyzer.hasWinner()).isFalse();
        assertThat(analyzer.drawGame()).isFalse();
    }

    @Test
    public void should_have_a_line_winner_from_the_first_cell() {
        // given
        board.addToken(0, 'o');
        board.addToken(1, 'o');
        board.addToken(2, 'o');
        board.addToken(3, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_line_winner_from_the_second_cell() {
        // given
        board.addToken(1, 'o');
        board.addToken(2, 'o');
        board.addToken(3, 'o');
        board.addToken(4, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_line_winner_from_the_second_line() {
        // given
        board.addToken(1, 'x');
        board.addToken(2, 'o');
        board.addToken(3, 'x');
        board.addToken(4, 'o');
        board.addToken(1, 'o');
        board.addToken(2, 'o');
        board.addToken(3, 'o');
        board.addToken(4, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_column_winner_from_the_first_cell() {
        // given
        board.addToken(0, 'o');
        board.addToken(0, 'o');
        board.addToken(0, 'o');
        board.addToken(0, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_column_winner_from_the_second_column() {
        // given
        board.addToken(1, 'o');
        board.addToken(1, 'o');
        board.addToken(1, 'o');
        board.addToken(1, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_column_winner_from_the_last_column() {
        // given
        board.addToken(6, '*');
        board.addToken(6, '*');
        board.addToken(6, 'o');
        board.addToken(6, 'o');
        board.addToken(6, 'o');
        board.addToken(6, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_line_winner_at_the_end_of_the_first_line() {
        // given
        board.addToken(3, 'o');
        board.addToken(4, 'o');
        board.addToken(5, 'o');
        board.addToken(6, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_rising_diagonal_winner_from_first_column() {
        // given
        setupRisingDiagonalWinner(0);

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_rising_diagonal_winner_from_second_column() {
        // given
        setupRisingDiagonalWinner(1);

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_no_winner_with_one_token_on_last_column() {
        // given
        board.addToken(6, 'o');

        // when

        // then
        assertThat(analyzer.hasWinner()).isFalse();
        assertThat(analyzer.drawGame()).isFalse();
    }

    private void setupRisingDiagonalWinner(int startColumn) {
        board.addToken(startColumn, 'o');
        board.addToken(startColumn + 1, '*');
        board.addToken(startColumn + 1, 'o');
        board.addToken(startColumn + 2, '*');
        board.addToken(startColumn + 2, '*');
        board.addToken(startColumn + 2, 'o');
        board.addToken(startColumn + 3, '*');
        board.addToken(startColumn + 3, '*');
        board.addToken(startColumn + 3, '*');
        board.addToken(startColumn + 3, 'o');
    }

    @Test
    public void should_have_a_falling_diagonal_winner_from_first_column() {
        // given
        setupFallingDiagonalWinner(0);

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    @Test
    public void should_have_a_falling_diagonal_winner_from_fourth_column() {
        // given
        setupFallingDiagonalWinner(3);

        // when

        // then
        assertThat(analyzer.hasWinner()).isTrue();
    }

    private void setupFallingDiagonalWinner(int startColumn) {
        board.addToken(startColumn, '*');
        board.addToken(startColumn, '*');
        board.addToken(startColumn, '*');
        board.addToken(startColumn, 'o');
        board.addToken(startColumn + 1, '*');
        board.addToken(startColumn + 1, '*');
        board.addToken(startColumn + 1, 'o');
        board.addToken(startColumn + 2, '*');
        board.addToken(startColumn + 2, 'o');
        board.addToken(startColumn + 3, 'o');
    }


    @Test
    public void should_have_a_draw_Game() {
        for (int line=0; line < Board.LINES; line++) {
            char currentToken = line%2==0?'x':'o';
            for (int col=0; col<Board.COLUMNS; col++) {
                board.addToken(col, currentToken);
                if (col%3 == 0) {
                    currentToken = currentToken=='x'?'o':'x';
                }
            }
        }

        assertThat(analyzer.drawGame()).isTrue();
    }

}
