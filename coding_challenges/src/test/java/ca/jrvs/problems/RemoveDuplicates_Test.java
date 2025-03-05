package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RemoveDuplicates_Test {

  private RemoveDuplicates removeDuplicates;

  @Before
  public void setUp(){
    removeDuplicates = new RemoveDuplicates();
  }

  @Test
  public void removeDuplicates(){
    assertEquals(2, removeDuplicates.removeDuplicates(new int[] {1,1,2}));
    assertEquals(5, removeDuplicates.removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4}));
    assertEquals(4, removeDuplicates.removeDuplicates(new int[] {0,1,2,2,3}));
  }
}
