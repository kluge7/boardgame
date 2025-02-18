package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the Player class. */
class PlayerTest {
  private Player player;
  private Tile startingTile;

  @BeforeEach
  void setUp() {
    startingTile = new Tile();
    player = new Player("Player 1", startingTile);
  }

  @Test
  void constructorCreatsPlayerWithValidParameters() {
    assertDoesNotThrow(() -> new Player("Player 2", new Tile()));
  }

  @Test
  void constructorThrowsExceptionWhenNameIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new Player(null, new Tile()));
  }

  @Test
  void constructorThrowsExceptionWhenNameIsEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new Player("", new Tile()));
  }

  @Test
  void constructorThrowsExceptionWhenStartingTileIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new Player("Player 1", null));
  }

  @Test
  void moveToNewTileUpdatesPlayerPosition() {
    Tile newTile = new Tile();
    player.move(newTile);
    assertEquals(newTile, player.getTile());
  }

  @Test
  void moveThrowsExceptionWhenTileIsNull() {
    assertThrows(IllegalArgumentException.class, () -> player.move(null));
  }

  @Test
  void getNameReturnsCorrectValue() {
    assertEquals("Player 1", player.getName());
  }

  @Test
  void getTileReturnsCorrectValue() {
    assertEquals(startingTile, player.getTile());
  }

  @Test
  void equalsReturnsTrueForSamePlayer() {
    Player player1 = new Player("Player 1", new Tile());
    Player player2 = new Player("Player 1", new Tile());
    assertEquals(player1, player2);
  }

  @Test
  void equalsReturnsFalseForDifferentNames() {
    Player player1 = new Player("Player 1", new Tile());
    Player player2 = new Player("Player 2", new Tile());
    assertNotEquals(player1, player2);
  }

  @Test
  void equalsReturnsFalseForDifferentTiles() {
    Player player1 = new Player("Player 1", new Tile());
    Tile differentTile = new Tile();
    differentTile.addAdjacentTile(new Tile());
    Player player2 = new Player("Player 1", differentTile);
    assertNotEquals(player1, player2);
  }
}
