package ca.jrvs.problems;


import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class MergeSortedArray_Test {

  private MergeSortedArray mergeSortedArray;

  @Before
  public void setUp(){
    mergeSortedArray = new MergeSortedArray();
  }

  @Test
  public void sort1(){
    int[] nums1 = {1,2,3,0,0,0};
    int m = 3;
    int[] nums2 = {2,5,6};
    int n = 3;
    int[] expected = {1,2,2,3,5,6};
    mergeSortedArray.sort(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }

  @Test
  public void sort2(){
    int[] nums1 = {1};
    int m = 1;
    int[] nums2 = {};
    int n = 0;
    int[] expected = {1};
    mergeSortedArray.sort(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }

  @Test
  public void sort3(){
    int[] nums1 = {0};
    int m = 0;
    int[] nums2 = {1};
    int n = 1;
    int[] expected = {1};
    mergeSortedArray.sort(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }

  @Test
  public void pointers1(){
    int[] nums1 = {1,2,3,0,0,0};
    int m = 3;
    int[] nums2 = {2,5,6};
    int n = 3;
    int[] expected = {1,2,2,3,5,6};
    mergeSortedArray.pointers(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }

  @Test
  public void pointers2(){
    int[] nums1 = {1};
    int m = 1;
    int[] nums2 = {};
    int n = 0;
    int[] expected = {1};
    mergeSortedArray.pointers(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }

  @Test
  public void pointers3(){
    int[] nums1 = {0};
    int m = 0;
    int[] nums2 = {1};
    int n = 1;
    int[] expected = {1};
    mergeSortedArray.pointers(nums1, m, nums2, n);
    assertArrayEquals(expected, nums1);
  }
}
