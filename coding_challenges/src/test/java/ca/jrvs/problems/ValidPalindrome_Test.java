package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidPalindrome_Test {

  private ValidPalindrome validPalindrome;

  @Before
  public void setUp(){
    validPalindrome = new ValidPalindrome();
  }

  @Test
  public void twoPointers(){
    assertTrue(validPalindrome.twoPointers("racecar"));
    assertTrue(validPalindrome.twoPointers(" "));
    assertTrue(validPalindrome.twoPointers("Step on no pets"));
    assertTrue(validPalindrome.twoPointers("race : car"));
    assertFalse(validPalindrome.twoPointers("notapalindrome"));
  }

  @Test
  public void recursion(){
    assertTrue(validPalindrome.recursion("racecar"));
    assertTrue(validPalindrome.recursion(" "));
    assertTrue(validPalindrome.recursion("Step on no pets"));
    assertTrue(validPalindrome.recursion("race : car"));
    assertFalse(validPalindrome.recursion("notapalindrome"));
  }
}
