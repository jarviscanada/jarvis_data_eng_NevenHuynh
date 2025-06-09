package ca.jrvs.problems;

import java.util.Arrays;

/**
 * Merge sorted array problem.
 * https://www.notion.so/jarvisdev/Merge-Sorted-Array-1adcc128f9de80469a71ccfaafd0df60
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

  /**
   * This approach combines the two arrays and performs a sort on the combined array.
   * Time complexity is O((m+n)log(m+n)) due to the sort.
   * Space complxitiy is O(1) as this utilizes in-place sorting.
   * @param nums1
   * @param m
   * @param nums2
   * @param n
   */
  public void sort(int[] nums1, int m, int[] nums2, int n) {
    for(int i = m; i < m + n; i++){
      nums1[i] = nums2[i-m];
    }
    Arrays.sort(nums1);
  }

  /**
   * This approach utilizes three pointers, one for the largest element of both arrays and one for
   * the end of the first array (empty space). By comparing the largest elements of both arrays, we
   * choose the largest and store it at the end of the first array, continuously doing so until we
   * reach the beginning of the second array.
   * Time complexity is O(m+n) to iterate through an array containing m+n elements.
   * Space complexity is O(1) for the pointers.
   * @param nums1
   * @param m
   * @param nums2
   * @param n
   */
  public void pointers(int[] nums1, int m, int[] nums2, int n){
    int k = m + n - 1;
    m--;
    n--;

    while(n >= 0){
      if(m >= 0 && nums1[m] > nums2[n]) {
        nums1[k--] = nums1[m--];
      } else{
        nums1[k--] = nums2[n--];
      }
    }
  }
}
