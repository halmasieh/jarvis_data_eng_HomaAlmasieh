package ca.jrvs.practice.codingChallenge;

/**
 *
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-ef15ba5f49cf46c5a4eb8bc215137481
 */
public class ValidPalindrome {

  /**
   * The time complexity of this approach is O(n).
   * The Space complexity of this approach is O(1).
   */
  public boolean isPalindrome(String str){
    if (str == null) {
      return false;
    }
    int i, j;
    i = 0;
    j = str.length() - 1;
    while (i < j) {
      char ch1 = str.charAt(i);
      char ch2 = str.charAt(j);
      if (Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
        i++;
        j--;
      } else if (Character.isDigit(ch1) == false && Character.isAlphabetic(ch1) == false) {
        i++;
      } else if (Character.isDigit(ch2) == false && Character.isAlphabetic(ch2) == false) {
        j--;
      } else {
        return false;
      }
    }
    return true;

  }

}
