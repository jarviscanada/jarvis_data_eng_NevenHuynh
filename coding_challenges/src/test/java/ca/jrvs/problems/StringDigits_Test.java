package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StringDigits_Test {

  private StringDigits stringDigits;

  @Before
  public void setUp(){
    stringDigits = new StringDigits();
  }

  @Test
  public void checkAscii(){
    assertTrue(stringDigits.checkAscii("1234"));
    assertFalse(stringDigits.checkAscii("123,000"));
    assertFalse(stringDigits.checkAscii("abc123"));
  }

  @Test
  public void checkApi(){
    assertTrue(stringDigits.checkApi("1234"));
    assertFalse(stringDigits.checkApi("123,000"));
    assertFalse(stringDigits.checkApi("abc123"));
  }

  @Test
  public void checkRegex(){
    assertTrue(stringDigits.checkRegex("1234"));
    assertFalse(stringDigits.checkRegex("123,000"));
    assertFalse(stringDigits.checkRegex("abc123"));
  }
}
