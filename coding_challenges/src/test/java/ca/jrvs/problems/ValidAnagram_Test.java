package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidAnagram_Test {

  private ValidAnagram validAnagram;

  @Before
  public void setUp(){
    validAnagram = new ValidAnagram();
  }

  @Test
  public void sorting(){
    assertTrue(validAnagram.sorting("anagram", "nagaram"));
    assertTrue(validAnagram.sorting(" ", " "));
    assertTrue(validAnagram.sorting("{}:0", "0}{:"));
    assertFalse(validAnagram.sorting("anagram", "notanagram"));
  }

  @Test
  public void optimal(){
    assertTrue(validAnagram.optimal("anagram", "nagaram"));
    assertTrue(validAnagram.optimal(" ", " "));
    assertTrue(validAnagram.optimal("{}:0", "0}{:"));
    assertFalse(validAnagram.sorting("anagram", "notanagram"));
  }
}
