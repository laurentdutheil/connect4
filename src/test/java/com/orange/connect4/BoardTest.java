package com.orange.connect4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void boardIsEmptyAtFirst() {
        assertThat(board.toString()).isEqualTo(".......\n.......\n.......\n.......\n.......\n.......");
    }

    @Test
    public void gravityColumnWithO() {
        board.addToken(4, 'o');
        assertThat(board.getToken(0, 4)).isEqualTo('o');
    }

    @Test
    public void gravityColumnWithX() {
        board.addToken(4, 'x');
        assertThat(board.getToken(0, 4)).isEqualTo('x');
    }

    @Test
    public void gravityColumnNotEmpty() {
        board.addToken(3, 'o');
        board.addToken(3, 'x');

        assertThat(board.getToken(1, 3)).isEqualTo('x');
        assertThat(board.getToken(0, 3)).isEqualTo('o');
    }

    @Test(expected = InvalidMoveException.class)
    public void noMoreThan6TokensInColumn() {
        board.addToken(3, 'o');
        board.addToken(3, 'x');
        board.addToken(3, 'o');
        board.addToken(3, 'x');
        board.addToken(3, 'o');
        board.addToken(3, 'x');
        board.addToken(3, 'o');
    }

    @Test
    public void representationOfBoard() {
        board.addToken(3, 'o');
        board.addToken(3, 'x');
        board.addToken(3, 'o');
        assertThat(board.toString()).isEqualTo(".......\n.......\n.......\n...o...\n...x...\n...o...");
    }

    @Test
    public void boardIsEmptyAfterBeingEmptied() {
        board.addToken(3, 'o');
        board.addToken(3, 'x');
        board.addToken(3, 'o');
        board.empty();
        assertThat(board.toString()).isEqualTo(".......\n.......\n.......\n.......\n.......\n.......");
    }

}
