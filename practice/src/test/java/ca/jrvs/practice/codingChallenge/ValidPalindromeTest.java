package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPalindromeTest {

    @Test
    public void isPalindromeTest() {
      ValidPalindrome validPalindrome = new ValidPalindrome();


      assertEquals(true, validPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
      assertEquals(true, validPalindrome.isPalindrome(" "));
      assertEquals(true, validPalindrome.isPalindrome("Borrow or rob"));
      assertEquals(false, validPalindrome.isPalindrome("race a car"));

    }
}


