package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciSequenceTest {

  @Before
  public void setUp() throws Exception {
    FibonacciSequence fib = new FibonacciSequence();
    }

  @Test
  public void fibRecursion() {
    int expected = 55;
    FibonacciSequence fib = new FibonacciSequence();
    Assert.assertEquals(expected, fib.fibRecursion(10));
  }

  @Test
  public void fibIteration() {
    int expected = 55;
    FibonacciSequence fib = new FibonacciSequence();
    Assert.assertEquals(expected, fib.fibIteration(10));
  }
}


