package no.ntnu.idatt2003.core.actions;

import java.util.Objects;
import no.ntnu.idatt2003.core.Player;
import no.ntnu.idatt2003.core.Tile;

/** Moves a player to a tile. */
public class MoveAction implements TileAction {
  private final Tile tile;

  /**
   * Constructs a move action that moves a player to a tile.
   *
   * @param newTile the tile to move the player to.
   */
  public MoveAction(Tile newTile) {
    this.tile = newTile;
  }

  /**
   * Executes the move action on the given player, setting their position to the specified tile.
   *
   * @param player the player affected by the move action.
   * @throws IllegalArgumentException if {@code player} is null or if the player cannot move to the
   *     tile.
   */
  @Override
  public void execute(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    try {
      player.move(tile);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Failed to move player: " + e.getMessage(), e);
    }
  }

  @Override
  public String toString() {
    return "MoveAction{tile=" + tile + "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MoveAction that = (MoveAction) obj;
    return Objects.equals(tile, that.tile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tile);
  }
}
