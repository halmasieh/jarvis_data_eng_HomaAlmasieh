package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class ValidParenthesesTest{

  @Test
  public void isValidTest() {
    ValidParentheses validParentheses= new ValidParentheses();


    assertEquals(true, validParentheses.isValid("()"));
    assertEquals(true, validParentheses.isValid("(){}[]"));
    assertEquals(false, validParentheses.isValid("{)"));
    assertEquals(true, validParentheses.isValid("([])"));
    assertEquals(false, validParentheses.isValid("({)}"));
  }

}



