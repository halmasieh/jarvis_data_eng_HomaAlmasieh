package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class StackUsingQueueTest {

  @Test
  public void StackUsingQueue() {
    StackUsingQueue stack = new StackUsingQueue();
    stack.push(20);
    stack.push(40);
    stack.push(70);
    stack.push(50);
    stack.push(90);
    stack.push(110);
    stack.push(30);
    int expected1 = 30;
    Assert.assertEquals(expected1, stack.pop());
    stack.push(170);
    int expected2 = 170;
    Assert.assertEquals(expected2, stack.pop());
    }
}