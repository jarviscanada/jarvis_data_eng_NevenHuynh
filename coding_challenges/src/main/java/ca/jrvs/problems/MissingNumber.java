package ca.jrvs.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Missing Number problem
 * https://www.notion.so/jarvisdev/Missing-Number-1a8cc128f9de803a9ba2df6954120c56
 * https://leetcode.com/problems/missing-number/description/
 */
public class MissingNumber {

  /**
   * This approach takes the summation of all numbers between 0 and n where n is the length of the
   * array, and compares it with the summation of all elements in the given input array. The
   * difference of the two sums gives us the missing number.
   * Time complexity is O(n) to loop through the array.
   * Space complexity is O(1) for the constant number of variables.
   * @param nums
   * @return int
   */
  public int sum(int[] nums){
    int n = nums.length;
    int sum=0;
    int missingSum=0;

    for(int i=0; i<n; i++){
      sum += i;
      missingSum += nums[i];
    }
    sum += n;

    return sum - missingSum;
  }

  /**
   * This approach stores all the numbers in a hashset and then checks if the hashset contains all
   * numbers from 0 to n. The missing number is returned.
   * Time complexity is O(n) to loop through the array.
   * Space complexity is O(n) for the size of the hashset.
   * @param nums
   * @return int
   */
  public int set(int[] nums){
    Set<Integer> set = new HashSet<Integer>();
    int n = nums.length;

    for(int i=0; i<n; i++){
      set.add(nums[i]);
    }
    for(int i=0; i<=n; i++){
      if(!set.contains(i))
        return i;
    }
    return 0;
  }

  /**
   * This approach takes the summation of all numbers between 0 and n by using Gauss' formula, and
   * subtracting the sum of the numbers in the array.
   * Time complexity is O(n) to loop through the array.
   * Space complexity is O(1) for the constant number of variables.
   * @param nums
   * @return int
   */
  public int gauss(int[] nums){
    int missingSum = 0;
    int n = nums.length;

    for(int num : nums)
      missingSum += num;

    int sum = (n * (n + 1))/2;

    return sum - missingSum;
  }
}
