package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement Stack Using Queue
 * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-a5e8c3e5edc244e885f2f4735e82fc0f
 *
 * Push :
 * If queue1 is empty, add elements to queue1, Time Complexity: O(1)
 * If queue1 is not empty, add all elements of queue1 to queue2 , add current element to queue1 and copy all elements of queue2 to queue1. Time Complexity: O(n)
 * Pop : Simply remove element from queue1.
 *
 */
public class StackUsingQueue {

  Queue<Integer> queue1;
  Queue<Integer> queue2;

  public StackUsingQueue() {
    queue1 = new LinkedList<Integer>();
    queue2 = new LinkedList<Integer>();
  }

  // Remove value from the beginning of the list for demonstrating behaviour of stack
  public void push(int i) {
    // If queue1 is empty, add elements to queue1
    if (queue1.size() == 0)
      queue1.add(i);
    else {
      int sizeOfQueue1 = queue1.size();
      // Copy elements of Queue1 to Queue2
      for (int j = 0; j < sizeOfQueue1; j++)
        queue2.add(queue1.remove());
      queue1.add(i);
      // Copy elements for Queue2 to Queue1
      for (int k = 0; k < sizeOfQueue1; k++)
        queue1.add(queue2.remove());
    }
  }

  public int pop() {
    if (queue1.size() == 0) {
      throw new QueueEmptyException("Queue is empty!");
    }
    return queue1.remove();
  }

  /**
   * Exception to indicate that Queue is empty.
   */

  class QueueEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueEmptyException() {
      super();
    }

    public QueueEmptyException(String message) {
      super(message);
    }
  }

}
