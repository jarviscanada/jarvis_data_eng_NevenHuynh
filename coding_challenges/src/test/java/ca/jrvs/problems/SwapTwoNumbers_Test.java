package ca.jrvs.problems;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class SwapTwoNumbers_Test {

  private SwapTwoNumbers swapTwoNumbers;

  @Before
  public void setUp(){
    swapTwoNumbers = new SwapTwoNumbers();
  }

  @Test
  public void swapBits(){
    int[] actual = {2,3};
    swapTwoNumbers.swapBits(actual);
    int[] expected = {3,2};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void swapArithmetic(){
    int[] actual = {2,3};
    swapTwoNumbers.swapArithmetic(actual);
    int[] expected = {3,2};

    assertArrayEquals(expected, actual);
  }
}
