package ca.jrvs.problems;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RotateString_Test {

  private RotateString rotateString;

  @Before
  public void setUp(){
    rotateString = new RotateString();
  }
  @Test
  public void rotate(){
    assertTrue(rotateString.rotate("abcde", "cdeab"));
    assertTrue(rotateString.rotate("defdefdefabcabc", "defdefabcabcdef"));
  }
}
