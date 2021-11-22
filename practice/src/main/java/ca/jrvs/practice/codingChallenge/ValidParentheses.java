package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-52f3240d7c13487f81b5455f52c4d3e5
 */
public class ValidParentheses {

  /**
   * traversed the string once, hence the time complexity is O(n).
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    // Stack to store left symbols
    Stack<Character> leftPar = new Stack<>();
    // Loop for each character of the string
    for (char c : s.toCharArray()) {
      // Append the left symbol to the leftPar stack
      if (c == '(' || c == '{' || c == '[') {
        leftPar.push(c);
      }
      // If right symbol is encountered
      else if (c == ')' && !leftPar.isEmpty() && leftPar.peek() == '(') {
        leftPar.pop();
      } else if (c == '}' && !leftPar.isEmpty() && leftPar.peek() == '{') {
        leftPar.pop();
      } else if (c == ']' && !leftPar.isEmpty() && leftPar.peek() == '[') {
        leftPar.pop();
      }
      else {
        return false;
      }
    }
    return leftPar.isEmpty();
  }
}