package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import no.ntnu.idatt2003.core.actions.TileAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/** Unit tests for the Tile class. */
class TileTest {
  private Tile tile;
  private Tile adjacentTile;
  private TileAction action;

  @BeforeEach
  void setUp() {
    tile = new Tile();
    adjacentTile = new Tile();
    action = Mockito.mock(TileAction.class); // Mock TileAction to avoid dependency issues
  }

  @Test
  void constructorInitializesEmptyLists() {
    assertTrue(tile.getAdjacentTiles().isEmpty());
    assertTrue(tile.getActions().isEmpty());
  }

  @Test
  void addAdjacentTileAddsTileSuccessfully() {
    tile.addAdjacentTile(adjacentTile);
    assertEquals(1, tile.getAdjacentTiles().size());
    assertTrue(tile.getAdjacentTiles().contains(adjacentTile));
  }

  @Test
  void addAdjacentTileThrowsExceptionWhenTileIsNull() {
    assertThrows(IllegalArgumentException.class, () -> tile.addAdjacentTile(null));
  }

  @Test
  void addAdjacentTileThrowsExceptionWhenTileIsAlreadyAdjacent() {
    tile.addAdjacentTile(adjacentTile);
    assertThrows(IllegalArgumentException.class, () -> tile.addAdjacentTile(adjacentTile));
  }

  @Test
  void addActionAddsActionSuccessfully() {
    tile.addAction(action);
    assertEquals(1, tile.getActions().size());
    assertTrue(tile.getActions().contains(action));
  }

  @Test
  void addActionThrowsExceptionWhenActionIsNull() {
    assertThrows(IllegalArgumentException.class, () -> tile.addAction(null));
  }

  @Test
  void getAdjacentTilesReturnsCorrectList() {
    tile.addAdjacentTile(adjacentTile);
    List<Tile> adjacentTiles = tile.getAdjacentTiles();
    assertEquals(1, adjacentTiles.size());
    assertEquals(adjacentTile, adjacentTiles.get(0));
  }

  @Test
  void getActionsReturnsCorrectList() {
    tile.addAction(action);
    List<TileAction> actions = tile.getActions();
    assertEquals(1, actions.size());
    assertEquals(action, actions.get(0));
  }

  @Test
  void equalsReturnsTrueForSameTile() {
    Tile tile1 = new Tile();
    Tile tile2 = new Tile();
    assertEquals(tile1, tile2);
  }

  @Test
  void equalsReturnsFalseForDifferentAdjacentTiles() {
    Tile tile1 = new Tile();
    Tile tile2 = new Tile();
    tile1.addAdjacentTile(new Tile());
    assertNotEquals(tile1, tile2);
  }

  @Test
  void equalsReturnsFalseForDifferentActions() {
    Tile tile1 = new Tile();
    Tile tile2 = new Tile();
    tile1.addAction(Mockito.mock(TileAction.class));
    assertNotEquals(tile1, tile2);
  }
}
