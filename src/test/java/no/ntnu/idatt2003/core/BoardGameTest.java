package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the BoardGame class. */
class BoardGameTest {
  private BoardGame boardGame;

  @BeforeEach
  void setUp() {
    boardGame = new BoardGame(100, 1, 1, 3, Arrays.asList("Player 1", "Player 2"));
    boardGame.getBoard().linkTilesInSequence();
  }

  @Test
  void constructorCreatesGameWithValidParameters() {
    assertDoesNotThrow(() -> new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2")));
  }

  @Test
  void constructorThrowsExceptionForInvalidBoardSize() {
    assertThrows(
        IllegalArgumentException.class, () -> new BoardGame(0, 2, 1, 6, Arrays.asList("Player 1")));
  }

  @Test
  void constructorThrowsExceptionForEmptyPlayersList() {
    assertThrows(IllegalArgumentException.class, () -> new BoardGame(10, 2, 1, 6, List.of()));
  }

  @Test
  void getCurrentPlayerReturnsFirstPlayerAtTheStart() {
    assertEquals("Player 1", boardGame.getCurrentPlayer().getName());
  }

  @Test
  void takeTurnMovesPlayer() {
    Player initialPlayer = boardGame.getCurrentPlayer();
    Tile initialTile = initialPlayer.getTile();

    boardGame.takeTurn();

    assertNotEquals(initialTile, initialPlayer.getTile());
  }

  @Test
  void takeTurnIncrementsTurnCount() {
    int initialTurnCount = boardGame.getTurnCount();

    boardGame.takeTurn();

    assertEquals(initialTurnCount + 1, boardGame.getTurnCount());
  }

  @Test
  void takeTurnSwitchesPlayer() {
    Player initialPlayer = boardGame.getCurrentPlayer();
    boardGame.takeTurn();

    assertNotEquals(initialPlayer, boardGame.getCurrentPlayer());

    boardGame.takeTurn();
    assertEquals(initialPlayer, boardGame.getCurrentPlayer()); // Back to first player
  }

  @Test
  void playerDoesNotMoveBeyondLastTile() {
    BoardGame shortGame =
        new BoardGame(5, 2, 10, 10, Arrays.asList("Alice")); // Dice always rolls 10
    shortGame.takeTurn();

    assertEquals(shortGame.getBoard().getTile(4), shortGame.getCurrentPlayer().getTile());
  }

  void gameEndsWhenPlayerReachesLastTile() {
    BoardGame shortGame = new BoardGame(3, 1, 6, 6, Arrays.asList("Alice")); // Dice always rolls 6
    shortGame.takeTurn();

    assertTrue(shortGame.isGameOver());
    assertEquals("Player 1", shortGame.getWinner().getName());
  }

  @Test
  void equalsReturnsTrueForSameGameConfiguration() {
    BoardGame game1 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    BoardGame game2 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    assertEquals(game1, game2);
  }

  @Test
  void equalsReturnsFalseForDifferentBoardSizes() {
    BoardGame game1 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    BoardGame game2 = new BoardGame(12, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    assertNotEquals(game1, game2);
  }

  @Test
  void equalsReturnsFalseForDifferentNumberOfDice() {
    BoardGame game1 = new BoardGame(10, 4, 1, 6, Arrays.asList("Player 1", "Player 2"));
    BoardGame game2 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    assertNotEquals(game1, game2);
  }

  @Test
  void equalsReturnsFalseForDifferentDiceConfiguration() {
    BoardGame game1 = new BoardGame(10, 2, 3, 8, Arrays.asList("Player 1", "Player 2"));
    BoardGame game2 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    assertNotEquals(game1, game2);
  }

  @Test
  void equalsReturnsFalseForDifferentPlayers() {
    BoardGame game1 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 1", "Player 2"));
    BoardGame game2 = new BoardGame(10, 2, 1, 6, Arrays.asList("Player 3", "Player 4"));
    assertNotEquals(game1, game2);
  }
}
