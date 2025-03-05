package ca.jrvs.problems;

/**
 * Swap two numbers without a third variable problem.
 * https://www.notion.so/jarvisdev/Swap-two-numbers-1a8cc128f9de80989fafdd4eeccf7eed
 */
public class SwapTwoNumbers {

  /**
   * This approach utilizes bit manipulation, specifically XOR.
   * Time complexity is O(1) as there are a constant number of steps.
   * Space complexity is O(1) as this approach utilizes in place swapping.
   * @param array
   */
  public void swapBits(int[] array){
    array[0] = array[0] ^ array[1];
    array[1] = array[0] ^ array[1];
    array[0] = array[0] ^ array[1];
  }
  /**
   * This approach utilizes arithmetic operations.
   * Time complexity is O(1) as there are a constant number of steps.
   * Space complexity is O(1) as this approach utilizes in place swapping.
   * @param array
   */
  public void swapArithmetic(int[] array){
    array[0] = array[0] + array[1];
    array[1] = array[0] - array[1];
    array[0] = array[0] - array[1];
  }
}
