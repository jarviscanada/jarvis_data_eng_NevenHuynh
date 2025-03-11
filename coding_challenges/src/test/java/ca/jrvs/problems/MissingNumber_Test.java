package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MissingNumber_Test {

  private MissingNumber missingNumber;

  @Before
  public void setUp(){
    missingNumber = new MissingNumber();
  }

  @Test
  public void sum(){
    int[] input = {9,6,4,2,3,5,7,0,1};
    int expected = 8;
    assertEquals(expected, missingNumber.sum(input));
  }

  @Test
  public void set(){
    int[] input = {9,6,4,2,3,5,7,0,1};
    int expected = 8;
    assertEquals(expected, missingNumber.set(input));
  }

  @Test
  public void gauss(){
    int[] input = {9,6,4,2,3,5,7,0,1};
    int expected = 8;
    assertEquals(expected, missingNumber.gauss(input));
  }
}
