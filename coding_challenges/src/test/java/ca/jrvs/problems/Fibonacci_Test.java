package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Fibonacci_Test {
  private Fibonacci fibonacci;

  @Before
  public void setUp(){
    fibonacci = new Fibonacci();
  }

  @Test
  public void recursion(){
    assertEquals(0, fibonacci.recursion(0));
    assertEquals(1, fibonacci.recursion(1));
    assertEquals(1, fibonacci.recursion(2));
    assertEquals(2, fibonacci.recursion(3));
    assertEquals(3, fibonacci.recursion(4));
    assertEquals(5, fibonacci.recursion(5));
    assertEquals(21, fibonacci.recursion(8));
  }

  @Test
  public void topDown(){
    assertEquals(21, fibonacci.topDown(8));
    assertEquals(610, fibonacci.topDown(15));
  }
  @Test
  public void bottomUp(){
    assertEquals(21, fibonacci.bottomUp(8));
    assertEquals(610, fibonacci.bottomUp(15));
  }
}
