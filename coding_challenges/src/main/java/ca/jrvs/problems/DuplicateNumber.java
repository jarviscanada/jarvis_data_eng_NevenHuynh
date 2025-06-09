package ca.jrvs.problems;

import java.util.Arrays;

/**
 * Find the Duplicate Number problem
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-1-1abcc128f9de8061820ff4964d0ff7ed
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 */
public class DuplicateNumber {

  /**
   * This approach sorts the array so that any duplicates in the array will be adjacent. It loops
   * through the array and maintains a pointer to the previous element, comparing the current
   * and previous elements.
   * Time complexity is O(nlogn) as it sorts the array before finding the duplicate.
   * Space complexity is O(1) as only 1 pointer is used.
   * @param nums
   * @return int
   */
  public int findDuplicateSorting(int[] nums) {
    Arrays.sort(nums);
    int duplicate = nums[0];
    int n = nums.length;

    for(int i = 1; i < n ; i++){
      if(nums[i] == duplicate)
        return duplicate;
      else
        duplicate = nums[i];
    }
    return duplicate;
  }

  /**
   * This approach utilizes Floyd's hare and tortoise algorithm. It checks for cycles and utilizes
   * pointers to find the beginning position of the cycle, which is the duplicate in the array.
   * Time complexity is O(n).
   * Space complexitiy is O(1) for the constant number of pointers.
   * @param nums
   * @return int
   */
  public int findDuplicateFloyd(int[] nums) {
    int fast = nums[0];
    int slow = nums[0];

    while(true){
      slow = nums[slow];
      fast = nums[nums[fast]];
      if(slow == fast)
        break;
    }

    int finder = nums[0];
    while(slow != finder){
      slow = nums[slow];
      finder = nums[finder];
    }
    return slow;
  }
}
