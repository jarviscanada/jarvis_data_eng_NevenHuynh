package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DuplicateNumber_Test {

  private DuplicateNumber duplicateNumber;

  @Before
  public void setUp(){
    duplicateNumber = new DuplicateNumber();
  }

  @Test
  public void findDuplicateSorting(){
    assertEquals(2, duplicateNumber.findDuplicateSorting(new int[] {1,3,4,2,2}));
    assertEquals(3, duplicateNumber.findDuplicateSorting(new int[] {3,1,3,4,2}));
    assertEquals(3, duplicateNumber.findDuplicateSorting(new int[] {3,3,3,3,3}));
    assertEquals(1, duplicateNumber.findDuplicateSorting(new int[] {1,2,3,4,5,6,7,8,1}));
  }

  @Test
  public void findDuplicateFloyd(){
    assertEquals(2, duplicateNumber.findDuplicateFloyd(new int[] {1,3,4,2,2}));
    assertEquals(3, duplicateNumber.findDuplicateFloyd(new int[] {3,1,3,4,2}));
    assertEquals(3, duplicateNumber.findDuplicateFloyd(new int[] {3,3,3,3,3}));
    assertEquals(1, duplicateNumber.findDuplicateFloyd(new int[] {1,2,3,4,5,6,7,8,1}));
  }
}
