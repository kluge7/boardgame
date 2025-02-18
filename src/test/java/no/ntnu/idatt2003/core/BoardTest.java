package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the Board class. */
class BoardTest {
  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board(5); // Board with 5 tiles
  }

  @Test
  void constructorCreatesBoardWithValidSize() {
    assertDoesNotThrow(() -> new Board(7));
    assertDoesNotThrow(() -> new Board(53));
  }

  @Test
  void constructorThrowsExceptionWhenSizeIsLessThanOne() {
    assertThrows(IllegalArgumentException.class, () -> new Board(0));
    assertThrows(IllegalArgumentException.class, () -> new Board(-5));
  }

  void linkTilesInSequenceLinksTilesCorrectly() {
    board.linkTilesInSequence();

    for (int i = 0; i < board.getSize() - 1; i++) {
      Tile currentTile = board.getTile(i);
      Tile nextTile = board.getTile(i + 1);

      assertTrue(currentTile.getAdjacentTiles().contains(nextTile));
    }
  }

  @Test
  void getSizeReturnsCorrectValue() {
    assertTrue(board.getSize() == 5);
  }

  @Test
  void getTileReturnsCorrectTile() {
    Tile tile = board.getTile(2);
    assertEquals(tile, board.getTiles().get(2));
  }

  @Test
  void getTileThrowsExceptionForInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> board.getTile(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> board.getTile(10));
  }

  @Test
  void equalsReturnsTrueForSameBoard() {
    Board board1 = new Board(3);
    Board board2 = new Board(3);
    assertEquals(board1, board2);
  }

  @Test
  void equalsReturnsFalseForDifferentBoardSizes() {
    Board board1 = new Board(3);
    Board board2 = new Board(4);
    assertNotEquals(board1, board2);
  }

  @Test
  void equalsReturnsFalseForDifferentTileConfigurations() {
    Board board1 = new Board(3);
    Board board2 = new Board(3);
    board1.linkTilesInSequence();
    assertNotEquals(board1, board2);
  }
}
