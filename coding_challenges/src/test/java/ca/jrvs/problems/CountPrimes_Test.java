package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CountPrimes_Test {

  private CountPrimes countPrimes;

  @Before
  public void setUp(){
    countPrimes = new CountPrimes();
  }

  @Test
  public void countPrimes(){
    assertEquals(4, countPrimes.countPrimes(10));
    assertEquals(1229, countPrimes.countPrimes(10000));
    assertEquals(348513, countPrimes.countPrimes(5000000));
  }
}
