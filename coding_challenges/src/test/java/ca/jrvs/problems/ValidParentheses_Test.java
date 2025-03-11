package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidParentheses_Test {

  private ValidParentheses validParentheses;

  @Before
  public void setUp(){
    validParentheses = new ValidParentheses();
  }

  @Test
  public void isValid(){
    assertTrue(validParentheses.isValid("()"));
    assertTrue(validParentheses.isValid("()[]{}"));
    assertTrue(validParentheses.isValid("([{}])"));
    assertFalse(validParentheses.isValid("(]"));
    assertFalse(validParentheses.isValid("()]"));
    assertFalse(validParentheses.isValid(")([]"));

  }
}
