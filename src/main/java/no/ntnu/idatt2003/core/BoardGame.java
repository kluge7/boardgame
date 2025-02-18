package no.ntnu.idatt2003.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Represents a board game with players, dice, and a board. */
public class BoardGame {
  private final Board board;
  private final Dice dice;
  private final List<Player> players;
  private Player currentPlayer;
  private int turnCount;
  private Player winner = null;

  /**
   * Constructs a board game with a specified board size, number of dice, and players.
   *
   * @param boardSize the number of tiles on the board.
   * @param numDice the number of dice used in the game.
   * @param lowerBound the minimum roll value for the dice.
   * @param upperBound the maximum roll value for the dice.
   * @param playerNames the list of player names.
   * @throws IllegalArgumentException if board size or number of dice is invalid.
   */
  public BoardGame(
      int boardSize, int numDice, int lowerBound, int upperBound, List<String> playerNames) {
    if (playerNames == null) {
      throw new IllegalArgumentException("Player names cannot be null.");
    }
    if (playerNames.isEmpty()) {
      throw new IllegalArgumentException("Player names cannot be empty.");
    }
    if (boardSize < 1) {
      throw new IllegalArgumentException("Board size must be at least 1, but was " + boardSize);
    }
    this.board = new Board(boardSize);
    this.dice = new Dice(numDice, lowerBound, upperBound);
    this.players = initializePlayers(playerNames);
    this.currentPlayer = players.get(0); // Start with the first player
    this.turnCount = 1;
  }

  /**
   * Initializes the player at the starting tile.
   *
   * @param playerNames the list of player names.
   * @return the list of players at the starting tile.
   */
  private List<Player> initializePlayers(List<String> playerNames) {
    Tile startingTile = board.getTile(0);
    List<Player> playerList = new ArrayList<>();

    playerNames.stream().map(name -> new Player(name, startingTile)).forEach(playerList::add);

    return playerList;
  }

  /** Rolls the dice and moves the current player based on the result. */
  public void takeTurn() {
    int rollResult = dice.rollSum();

    Tile newTile = movePlayer(currentPlayer, rollResult);
    executeTileActions(currentPlayer, newTile);

    checkWinCondition(currentPlayer);
    if (winner == null) {
      switchToNextPlayer();
      turnCount++;
    }
  }

  /**
   * Moves the player forward by the given steps.
   *
   * @param player the player to move.
   * @param steps the number of steps to move.
   * @return the new tile the player moves to.
   */
  private Tile movePlayer(Player player, int steps) {
    int currentIndex = board.getTiles().indexOf(player.getTile());
    int newIndex = calculateNewIndex(currentIndex, steps);
    Tile newTile = board.getTile(newIndex);
    player.move(newTile);
    return newTile;
  }

  /**
   * Calculates the new index based on the current index and steps to move.
   *
   * @param currentIndex the current index.
   * @param steps the number of steps to move.
   * @return the new index after moving the given steps.
   */
  private int calculateNewIndex(int currentIndex, int steps) {
    return Math.min(currentIndex + steps, board.getTiles().size() - 1);
  }

  /**
   * Executes actions on the tile.
   *
   * @param player the player on the tile.
   * @param tile the tile to check for actions.
   */
  private void executeTileActions(Player player, Tile tile) {
    tile.getActions().stream().forEach(action -> action.execute(player));
  }

  /** Switches to the next player in the turn order. */
  private void switchToNextPlayer() {
    int currentIndex = players.indexOf(currentPlayer);
    int nextIndex = (currentIndex + 1) % players.size();
    currentPlayer = players.get(nextIndex);
  }

  /**
   * Checks if the player has reached the end of the board.
   *
   * @param player the player to check.
   */
  private void checkWinCondition(Player player) {
    if (player.getTile().equals(board.getTile(board.getTiles().size() - 1))) {
      winner = player;
    }
  }

  public boolean isGameOver() {
    return winner != null;
  }

  public Player getWinner() {
    return winner;
  }

  public List<Player> getPlayers() {
    return Collections.unmodifiableList(players);
  }

  public Board getBoard() {
    return board;
  }

  public int getTurnCount() {
    return turnCount;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public Dice getDice() {
    return dice;
  }

  @Override
  public String toString() {
    return "BoardGame{"
        + "board="
        + board
        + ", dice="
        + dice
        + ", players="
        + players
        + ", currentPlayer="
        + currentPlayer
        + ", turnCount="
        + turnCount
        + ", winner="
        + winner
        + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    BoardGame boardGame = (BoardGame) obj;
    return turnCount == boardGame.turnCount
        && Objects.equals(board, boardGame.board)
        && Objects.equals(dice, boardGame.dice)
        && Objects.equals(players, boardGame.players)
        && Objects.equals(currentPlayer, boardGame.currentPlayer)
        && Objects.equals(winner, boardGame.winner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(board, dice, players, currentPlayer, turnCount, winner);
  }
}
