package no.ntnu.idatt2003.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/** Unit tests for the Die class. */
class DieTest {
  private static Die die;

  @BeforeAll
  static void setUp() {
    die = new Die(1, 6); // Die with bounds 1-6
  }

  @Test
  void constructorCreatesDieWithValidParameters() {
    assertDoesNotThrow(() -> new Die(1, 6));
  }

  @Test
  void constructorThrowsExceptionWhenLowerBoundIsGreaterThanUpperBound() {
    assertThrows(IllegalArgumentException.class, () -> new Die(2, 1));
  }

  @RepeatedTest(10)
  void rollReturnsValueWithinBounds() {
    int result = die.roll();
    assertTrue(result >= die.getLowerBound() && result <= die.getUpperBound());
  }

  @Test
  void rollReturnsFixedValueWhenBoundsAreEqual() {
    Die die2 = new Die(4, 4);
    assertTrue(die2.roll() == 4);
  }

  @Test
  void getLowerBoundReturnsCorrectValue() {
    assertTrue(die.getLowerBound() == 1);
  }

  @Test
  void getUpperBoundReturnsCorrectValue() {
    assertTrue(die.getUpperBound() == 6);
  }

  @Test
  void equalsReturnsTrueForSameBounds() {
    Die die1 = new Die(1, 6);
    Die die2 = new Die(1, 6);
    assertEquals(die1, die2);
  }

  @Test
  void equalsReturnsFalseForDifferentBounds() {
    Die die1 = new Die(1, 6);
    Die die2 = new Die(2, 6);
    assertNotEquals(die1, die2);
  }
}
