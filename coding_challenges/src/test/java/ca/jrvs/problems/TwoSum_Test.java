package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TwoSum_Test {
  private TwoSum twoSum;

  @Before
  public void setUp(){
    twoSum = new TwoSum();
  }

  @Test
  public void bruteForce1(){
    int[] nums = {2,7,11,15};
    int target = 9;
    int[] expected = {0, 1};
    assertTrue(Arrays.equals(expected, twoSum.bruteForce(nums, target)));
  }

  @Test
  public void bruteForce2() {
    int[] nums = {3, 2, 4};
    int target = 6;
    int[] expected = {1, 2};
    assertTrue(Arrays.equals(expected, twoSum.bruteForce(nums, target)));
  }

  @Test
  public void sorting() {
    int[] nums = {3, 2, 4};
    int target = 6;
    int[] expected = {1, 2};
    assertTrue(Arrays.equals(expected, twoSum.sorting(nums, target)));
  }
  @Test
  public void optimal() {
    int[] nums = {3, 2, 4, 19, 50};
    int target = 6;
    int[] expected = {1, 2};
    assertTrue(Arrays.equals(expected, twoSum.optimal(nums, target)));
  }
}
