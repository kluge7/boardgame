package no.ntnu.idatt2003.core;

import java.util.Objects;
import java.util.Random;

/** Represents a single die that can be rolled with custom bounds. */
public class Die {
  private final int lowerBound;
  private final int upperBound;
  private final Random random;

  /**
   * Constructs a die with specified bounds.
   *
   * @param lowerBound the minimum roll value.
   * @param upperBound the maximum roll value.
   * @throws IllegalArgumentException if {@code lowerBound} is greater than {@code upperBound}.
   */
  public Die(int lowerBound, int upperBound) {
    if (lowerBound > upperBound) {
      throw new IllegalArgumentException("Lower bound cannot be greater than upper bound.");
    }
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.random = new Random();
  }

  /**
   * Rolls the die and returns the result.
   *
   * @return a random number between {@code lowerBound} and {@code upperBound}.
   */
  public int roll() {
    return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
  }

  public int getLowerBound() {
    return lowerBound;
  }

  public int getUpperBound() {
    return upperBound;
  }

  @Override
  public String toString() {
    return "Die{" + "lowerBound=" + lowerBound + ", upperBound=" + upperBound + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Die die = (Die) obj;
    return lowerBound == die.lowerBound && upperBound == die.upperBound;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lowerBound, upperBound);
  }
}
