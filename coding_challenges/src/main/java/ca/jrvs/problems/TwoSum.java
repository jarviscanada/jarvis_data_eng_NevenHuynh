package ca.jrvs.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/Two-Sum-1a1cc128f9de80c49a36fd152d4fcb4e
 */
public class TwoSum {

  /**
   * The brute force approach to solve the twoSum problem. This approach uses a nested for-loop to
   * search the array n times for each index n. Thus, the time complexity is O(n^2) and the space
   * complexity is O(1).
   * @param nums
   * @param target
   * @return An array of the indices of the input array that contain the elements that add up to the
   * target
   */
  public int[] bruteForce(int[] nums, int target){
    int n = nums.length;
    for(int i = 0; i < n-1; i++){
      for(int j = i+1; j < n; j++){
        if(nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return new int[]{};
  }

  /**
   * The sorting approach to solve the twoSum problem. This approach sorts the input array and uses
   * two pointers to search for the elements that add up to the target. The time complexity is
   * O(nlogn) as we are sorting the input array before checking the elements. The space complexity
   * is O(n) for the hashmap.
   * @param nums
   * @param target
   * @return An array of the indices of the input array that contain the elements that add up to the
   * target
   */
  public int[] sorting(int[] nums, int target){
    int n = nums.length;
    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < n ; i++){
      map.put(nums[i], i);
    }
    int start = 0;
    int end = n - 1;
    Arrays.sort(nums);

    while(start < end){
      if (nums[start] + nums[end] == target)
        return new int[]{map.get(nums[start]), map.get(nums[end])};
      else if (nums[start] + nums[end] > target)
        end--;
      else
        start++;
    }
    return new int[]{};
  }

  /**
   * The optimal approach to solving the twoSum problem. This approach stores the elements and indices
   * as key-value pairs in a hashmap respectively and iterates through the array, calculating the
   * complement of the element in the array where complement = target - array[i]. Then, the hashmap
   * is checked to see if it contains the complement as a key. The time complexity is O(n) as we
   * iterate through the array twice and hashmap lookup takes O(1) time on average. The space
   * complexity is O(n) for the hashmap.
   * @param nums
   * @param target
   * @return An array of the indices of the input array that contain the elements that add up to the
   * target
   */
  public int[] optimal(int[] nums, int target){
    Map<Integer, Integer> map = new HashMap<>();
    int n = nums.length;
    for(int i = 0; i < n; i++){
      map.put(nums[i], i);
    }
    for(int i = 0; i < n; i++){
      int complement = target - nums[i];
      if(map.containsKey(complement) && map.get(complement) != i)
        return new int[] {i, map.get(complement)};
    }
    return new int[]{};
  }
}
