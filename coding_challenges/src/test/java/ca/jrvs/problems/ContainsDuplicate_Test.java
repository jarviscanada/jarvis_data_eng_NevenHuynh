package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ContainsDuplicate_Test {

  private ContainsDuplicate containsDuplicate;

  @Before
  public void setUp(){
    containsDuplicate = new ContainsDuplicate();
  }

  @Test
  public void sorting(){
    assertTrue(containsDuplicate.sorting(new int[] {1,2,3,1}));
    assertFalse(containsDuplicate.sorting(new int[] {1,2,3,4}));
    assertTrue(containsDuplicate.sorting(new int[] {1,1,1,3,3,4,3,2,4,2}));
  }

  @Test
  public void set(){
    assertTrue(containsDuplicate.set(new int[] {1,2,3,1}));
    assertFalse(containsDuplicate.set(new int[] {1,2,3,4}));
    assertTrue(containsDuplicate.set(new int[] {1,1,1,3,3,4,3,2,4,2}));
  }
}
