package ca.jrvs.practice.codingChallenge;

/**
 * Challenge: Fibonacci Number/Climbing Stairs
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-68ef9aa9db794472b00bcf6ff8fb9233
 */
public class FibonacciSequence {

   /**
   * Big-O: O(2^n)
   *
   * @param n
   * @return
   */
  public int fibRecursion(int n) {
    if (n == 1 | n == 2) {
      return 1;
    } else {
      return fibRecursion(n - 1) + fibRecursion(n - 2);
    }
  }

  public int fibIteration(int n) {
    int firstTerm = 0;
    int secondTerm = 1;
    int thirdTerm = 1;
    for (int i=0; i<n; i++){
      firstTerm = secondTerm;
      secondTerm = thirdTerm;
      thirdTerm = firstTerm + secondTerm;
    }
    return firstTerm;
    }
}