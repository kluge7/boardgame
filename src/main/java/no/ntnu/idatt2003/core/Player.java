package no.ntnu.idatt2003.core;

import java.util.Objects;

/** Represents a player in the board game. */
public class Player {
  private final String name;
  private Tile tile;

  /**
   * Constructs a player with a given name and starting tile.
   *
   * @param name the name of the player.
   * @param startingTile the starting tile of the player.
   * @throws IllegalArgumentException if {@code name} or {@code startingTile} is null or empty.
   */
  public Player(String name, Tile startingTile) {
    if (name == null) {
      throw new IllegalArgumentException("Player name cannot be null.");
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Player name cannot be empty.");
    }
    if (startingTile == null) {
      throw new IllegalArgumentException("Starting tile cannot be null.");
    }
    this.name = name;
    this.tile = startingTile;
  }

  /**
   * Moves the player to a new tile.
   *
   * @param newTile the new tile the player moves to.
   * @throws IllegalArgumentException if {@code newTile} is null.
   */
  public void move(Tile newTile) {
    if (newTile == null) {
      throw new IllegalArgumentException("New tile cannot be null.");
    }
    this.tile = newTile;
  }

  public String getName() {
    return name;
  }

  public Tile getTile() {
    return tile;
  }

  @Override
  public String toString() {
    return "Player{" + "name='" + name + '\'' + ", tile=" + tile + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Player player = (Player) obj;
    return name.equals(player.name) && tile.equals(player.tile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, tile);
  }
}
