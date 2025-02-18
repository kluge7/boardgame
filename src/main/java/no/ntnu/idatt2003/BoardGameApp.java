package no.ntnu.idatt2003;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import no.ntnu.idatt2003.core.BoardGame;
import no.ntnu.idatt2003.core.Player;

/** A simple board game application. */
public class BoardGameApp {
  /** The main method of the application. */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> playerNames = new ArrayList<>();

    int numPlayers = 0;

    while (numPlayers <= 0) {
      System.out.println("Enter the number of players:");
      if (scanner.hasNextInt()) {
        numPlayers = scanner.nextInt();
        scanner.nextLine();
        if (numPlayers <= 0) {
          System.out.println("Please enter a positive number.");
        }
      } else {
        System.out.println("Invalid input. Please enter a number.");
        scanner.next();
      }
    }

    for (int i = 0; i < numPlayers; i++) {
      String name;
      do {
        System.out.println("Enter player " + (i + 1) + "'s name:");
        name = scanner.nextLine().trim();
        if (name.isEmpty()) {
          System.out.println("Player name cannot be empty.");
        }
      } while (name.isEmpty());

      playerNames.add(name);
    }

    System.out.println("The following players are playing the game:");
    for (String name : playerNames) {
      System.out.println("Name: " + name);
    }
    scanner.close();

    int boardSize = 31;
    int numDice = 1;
    int lowerBound = 1;
    int upperBound = 6;

    BoardGame game = new BoardGame(boardSize, numDice, lowerBound, upperBound, playerNames);
    game.getBoard().linkTilesInSequence();

    // Play the game
    int round = 1;
    while (!game.isGameOver()) {
      System.out.println("\nRound " + round);

      for (Player player : game.getPlayers()) {
        game.takeTurn();
      }

      for (Player player : game.getPlayers()) {
        System.out.println(
            "Player "
                + player.getName()
                + " is now on tile "
                + game.getBoard().getTiles().indexOf(player.getTile()));
      }

      round++;
    }

    System.out.println("Game over! The winner is: " + game.getWinner().getName());
  }
}
