package ca.jrvs.problems;

/**
 * Remove duplicates from sorted array problem
 * https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-1-1adcc128f9de8089b792e7223cbb4320
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {

  /**
   * This approach utilizes two pointers to compare elements for equality and for swapping.
   * Time complexity is O(n) as this approach utilizes a one-pass loop of the array.
   * Space complexity is O(1) for the two pointers.
   * @param nums
   * @return int
   */
  public int removeDuplicates(int[] nums) {
    int slow = 0;
    int fast = 0;

    while(fast < nums.length){
      while(nums[slow] == nums[fast]){
        fast++;
        if(fast >= nums.length)
          break;
      }
      slow++;
      if(fast < nums.length)
        nums[slow] = nums[fast];
    }
    return slow;
  }
}
