package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/** Unit tests for the Dice class. */
class DiceTest {
  private static Dice dice;

  @BeforeAll
  static void setUp() {
    dice = new Dice(3, 1, 6); // Three six-sided dice
  }

  @Test
  void constructorCreatesDiceWithValidParameters() {
    assertDoesNotThrow(() -> new Dice(3, 1, 6));
  }

  @Test
  void constructorThrowsExceptionWhenNumberOfDiceIsLessThanOne() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(0, 1, 6));
    assertThrows(IllegalArgumentException.class, () -> new Dice(-5, 1, 6));
  }

  @Test
  void constructorThrowsExceptionWhenLowerBoundIsGreaterThanUpperBound() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(4, 2, 1));
  }

  @Test
  void constructorThrowsExceptionWhenListIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(null));
  }

  @Test
  void constructorThrowsExceptionWhenListIsEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(List.of()));
  }

  @RepeatedTest(10)
  void rollSumReturnsValueWithinBounds() {
    int sum = dice.rollSum();
    assertTrue(sum >= 3 && sum <= 18);
  }

  @RepeatedTest(10)
  void rollIndividualReturnsValuesWithinBounds() {
    List<Integer> results = dice.rollIndividual();
    for (int result : results) {
      assertTrue(result >= 1 && result <= 6);
    }
  }

  @Test
  void getNumberOfDiceReturnsCorrectValue() {
    assertTrue(dice.getNumberOfDice() == 3);
  }

  @Test
  void equalsReturnsTrueForSameDice() {
    Dice dice1 = new Dice(List.of(new Die(1, 6), new Die(1, 6), new Die(1, 6)));
    Dice dice2 = new Dice(List.of(new Die(1, 6), new Die(1, 6), new Die(1, 6)));
    assertEquals(dice1, dice2);
  }

  @Test
  void equalsReturnsFalseForDifferentDiceSet() {
    Dice dice1 = new Dice(List.of(new Die(1, 6), new Die(1, 6), new Die(1, 6)));
    Dice dice2 = new Dice(List.of(new Die(2, 6), new Die(1, 6), new Die(1, 6)));
    assertNotEquals(dice1, dice2);
  }
}
