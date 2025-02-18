package no.ntnu.idatt2003.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import no.ntnu.idatt2003.core.actions.TileAction;

/** Represents a single tile on the game board. */
public class Tile {
  private final List<Tile> adjacentTiles;
  private final List<TileAction> actions;

  /** Constructs a tile at a given position with optional actions and adjacent tiles. */
  public Tile() {
    this.adjacentTiles = new ArrayList<>();
    this.actions = new ArrayList<>();
  }

  /**
   * Adds an adjacent tile to the tile.
   *
   * @param tile the tile to add as an adjacent tile.
   * @throws IllegalArgumentException if {@code tile} is null or already adjacent.
   */
  public void addAdjacentTile(Tile tile) {
    if (tile == null) {
      throw new IllegalArgumentException("Tile cannot be null.");
    }
    if (adjacentTiles.contains(tile)) {
      throw new IllegalArgumentException("Tile is already adjacent.");
    }
    adjacentTiles.add(tile);
  }

  /**
   * Add an action to the tile.
   *
   * @param action the action to add to the tile.
   * @throws IllegalArgumentException if {@code action} is null.
   */
  public void addAction(TileAction action) {
    if (action == null) {
      throw new IllegalArgumentException("Action cannot be null.");
    }
    actions.add(action);
  }

  public List<Tile> getAdjacentTiles() {
    return new ArrayList<>(adjacentTiles);
  }

  public List<TileAction> getActions() {
    return new ArrayList<>(actions);
  }

  @Override
  public String toString() {
    return "Tile{" + "adjacentTiles=" + adjacentTiles + ", actions=" + actions + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Tile tile = (Tile) obj;
    return Objects.equals(adjacentTiles, tile.adjacentTiles)
        && Objects.equals(actions, tile.actions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adjacentTiles, actions);
  }
}
