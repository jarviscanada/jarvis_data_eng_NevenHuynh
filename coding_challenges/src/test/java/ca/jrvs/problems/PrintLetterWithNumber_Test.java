package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PrintLetterWithNumber_Test {

  private PrintLetterWithNumber printLetterWithNumber;

  @Before
  public void setUp(){
    printLetterWithNumber = new PrintLetterWithNumber();
  }

  @Test
  public void solution(){
    assertEquals("a1b2c3",printLetterWithNumber.solution("abc"));
    assertEquals("A27B28C29",printLetterWithNumber.solution("ABC"));
    assertEquals("A27a1B28b2",printLetterWithNumber.solution("AaBb"));
  }
}
