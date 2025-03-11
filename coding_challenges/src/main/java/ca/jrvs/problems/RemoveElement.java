package ca.jrvs.problems;

/**
 * Remove element from array problem.
 * https://www.notion.so/jarvisdev/Remove-Element-1accc128f9de800b86d8ccaaba3ee351
 * https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {

  /**
   * This approach first iterates through the array to count the number of elements that are not
   * equal to the target value. It then iterates through the array again to swap all elements that
   * are equal to the target value to the end of the array with elements not equal to the target
   * value.
   * Time complexity is O(n) to iterate through the array
   * Space complexity is O(1) as the swapping is done in-place and a constant number of variables
   * are used.
   * @param nums
   * @param val
   * @return int
   */
  public int removeElement(int[] nums, int val){
    int n = nums.length -1;
    int k = 0;

    for(int i = 0; i <= n; i++){
      if(nums[i] != val){
        k++;
      }
    }
    for(int i = 0; i < k; i++){
      if(nums[i] == val){
        while(n > i){
          if(nums[n] == val){
            n--;
          }else{
            break;
          }
        }
        nums[i] = nums[n];
        nums[n] = val;
        n--;
      }
    }
    return k;
  }
}
