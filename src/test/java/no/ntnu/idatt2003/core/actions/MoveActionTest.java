package no.ntnu.idatt2003.core.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.idatt2003.core.Player;
import no.ntnu.idatt2003.core.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the MoveAction class. */
class MoveActionTest {
  private MoveAction moveAction;
  private Player player;
  private Tile initialTile;
  private Tile newTile;

  @BeforeEach
  void setUp() {
    initialTile = new Tile();
    newTile = new Tile();
    player = new Player("Player 1", initialTile);
    moveAction = new MoveAction(newTile);
  }

  @Test
  void executeMovesPlayerToNewTile() {
    moveAction.execute(player);
    assertSame(newTile, player.getTile());
  }

  @Test
  void executeThrowsExceptionWhenPlayerIsNull() {
    assertThrows(IllegalArgumentException.class, () -> moveAction.execute(null));
  }

  @Test
  void executeThrowsExceptionWhenTileIsNull() {
    Tile invalidTile = null;
    MoveAction invalidMoveAction = new MoveAction(invalidTile);
    assertThrows(IllegalArgumentException.class, () -> invalidMoveAction.execute(player));
  }

  @Test
  void equalsReturnsTrueForSameTile() {
    MoveAction moveAction1 = new MoveAction(newTile);
    MoveAction moveAction2 = new MoveAction(newTile);
    assertEquals(moveAction1, moveAction2);
  }

  @Test
  void equalsReturnsFalseForDifferentTiles() {
    MoveAction moveAction1 = new MoveAction(new Tile());
    Tile differentTile = new Tile();
    differentTile.addAdjacentTile(new Tile());
    MoveAction moveAction2 = new MoveAction(differentTile);
    assertNotEquals(moveAction1, moveAction2);
  }
}
