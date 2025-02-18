package no.ntnu.idatt2003.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Represents a collection of dice that can be rolled together. */
public class Dice {
  private final List<Die> dice;

  /**
   * Constructs a set of dice with the given bounds.
   *
   * @param numberOfDice the number of dice to create.
   * @param lowerBound the minimum roll value for each die.
   * @param upperBound the maximum roll value for each die.
   * @throws IllegalArgumentException if {@code numberOfDice} is less than 1 or if {@code
   *     lowerBound} is greater than {@code upperBound}.
   */
  public Dice(int numberOfDice, int lowerBound, int upperBound) {
    if (numberOfDice < 1) {
      throw new IllegalArgumentException(
          "Number of dice must be at least 1, but was " + numberOfDice);
    }
    if (lowerBound > upperBound) {
      throw new IllegalArgumentException("Lower bound cannot be greater than upper bound.");
    }
    this.dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      this.dice.add(new Die(lowerBound, upperBound));
    }
  }

  /**
   * Constructs a set of dice from a list of dice.
   *
   * @param dice the list of dice to use.
   * @throws IllegalArgumentException if {@code dice} is null or empty.
   */
  public Dice(List<Die> dice) {
    if (dice == null) {
      throw new IllegalArgumentException("Dice list cannot be null.");
    }
    if (dice.isEmpty()) {
      throw new IllegalArgumentException("Dice list cannot be empty.");
    }
    this.dice = new ArrayList<>(dice);
  }

  /**
   * Rolls all dice and returns the total sum.
   *
   * @return the sum of all dice rolls.
   */
  public int rollSum() {
    return dice.stream().mapToInt(Die::roll).sum();
  }

  /**
   * Rolls each die individually and returns the results.
   *
   * @return a list of individual die rolls.
   */
  public List<Integer> rollIndividual() {
    return dice.stream().map(Die::roll).collect(Collectors.toList());
  }

  public List<Die> getDice() {
    return Collections.unmodifiableList(dice);
  }

  public int getNumberOfDice() {
    return dice.size();
  }

  @Override
  public String toString() {
    return "Dice{" + "dice=" + dice + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Dice diceSet = (Dice) obj;
    return Objects.equals(dice, diceSet.dice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dice);
  }
}
