package no.ntnu.idatt2003.core.actions;

import no.ntnu.idatt2003.core.Player;

/** Represents an action that can be performed on a player when landing on a tile. */
public interface TileAction {

  /**
   * Executes the action on the given player.
   *
   * @param player the player affected by the action.
   * @throws IllegalArgumentException if {@code player} is null.
   */
  void execute(Player player);
}
