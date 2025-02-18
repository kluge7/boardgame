package no.ntnu.idatt2003.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Represents the game board, containing a list of tiles. */
public class Board {
  private final List<Tile> tiles;

  /**
   * Constructs a board with a given number of tiles.
   *
   * @param size the number of tiles on the board.
   * @throws IllegalArgumentException if {@code size} is less than 1.
   */
  public Board(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Board size must be at least 1, but was " + size);
    }
    tiles = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      tiles.add(new Tile());
    }
  }

  /** Link all tiles sequentially. Creates a one-way path through the board. */
  public void linkTilesInSequence() {
    for (int i = 0; i < tiles.size() - 1; i++) {
      tiles.get(i).addAdjacentTile(tiles.get(i + 1));
    }
  }

  /**
   * Checks if the given index is valid.
   *
   * @param index the index to check.
   * @return true if the index is valid, false otherwise.
   */
  private boolean isValidIndex(int index) {
    return index >= 0 && index < tiles.size();
  }

  /**
   * Gets a tile at a index.
   *
   * @param index the index of the tile.
   * @return the tile at the given index.
   * @throws IndexOutOfBoundsException if index is invalid.
   */
  public Tile getTile(int index) {
    if (!isValidIndex(index)) {
      throw new IndexOutOfBoundsException("Invalid tile index.");
    }
    return tiles.get(index);
  }

  public List<Tile> getTiles() {
    return Collections.unmodifiableList(tiles);
  }

  public int getSize() {
    return tiles.size();
  }

  @Override
  public String toString() {
    return "Board{" + "tiles=" + tiles + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Board board = (Board) obj;
    return Objects.equals(tiles, board.tiles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tiles);
  }
}
