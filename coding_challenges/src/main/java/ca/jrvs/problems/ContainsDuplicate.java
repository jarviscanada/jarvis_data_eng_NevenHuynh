package ca.jrvs.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains duplicate problem.
 * https://www.notion.so/jarvisdev/Contains-Duplicate-1-1accc128f9de8017852ac03d85110484
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {

  /**
   * This approach sorts the input array so that any duplicates will appear side by side. A one pass
   * loop can then be used to determine if there is a duplicate.
   * Time complexity is O(nlogn) to sort the array.
   * Space complexity is O(1) as this utilizes in-place sorting and a constant number of variables.
   * @param nums
   * @return boolean
   */
  public boolean sorting(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int duplicate = nums[0];

    for(int i = 1; i < n ; i++){
      if(nums[i] == duplicate)
        return true;
      else
        duplicate = nums[i];
    }
    return false;
  }

  /**
   * This approach utilizes a hashset to store all the elements of the array and checking whether
   * any elements are already contained in the hashset before adding it.
   * Time complexity is O(n) to iterate through the array. Hashset lookup is O(1).
   * Space complexity is O(n) for the hashset data structure.
   * @param nums
   * @return boolean
   */
  public boolean set(int[] nums){
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
      if (set.contains(num))
        return true;
      else
        set.add(num);
    }
    return false;
  }
}
