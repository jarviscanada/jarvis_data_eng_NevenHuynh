package ca.jrvs.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-1a0cc128f9de80029cceea4cb397e4ed
 */
public class Fibonacci {
  Map<Integer, Integer> map = new HashMap<>();
  /**
   * The recursion (naive) approach to implementing the Fibonacci sequence. The time complexity is
   * O(2^n) as the number of method calls grows exponentially (doubles) for an input n. This is due
   * to the fact that recursion computes the same computation repeatedly. Space complexity is O(n)
   * since recursion(n-1) gets evaluated before recursion(n-2), there are at most n levels of
   * recursion.
   * @param n
   * @return Fibonacci number
   */
  public int recursion(int n){
    if(n == 0 || n == 1)
      return n;
    return recursion(n-1) + recursion(n-2);
  }

  /**
   * The top-down (memoization) dynamic programming approach to implementing the Fibonacci sequence. The time
   * complexity is O(n) as we reduce the number of steps to compute the sequence by storing the
   * redundant computations in a hashmap. Space complexity is also O(n) for the same reason as
   * recursion.
   * @param n
   * @return Fibonacci number
   */
  public int topDown(int n){
    if(n == 0 || n == 1)
      return n;
    if(map.containsKey(n))
      return map.get(n);
    int fibonacci = topDown(n-1) + topDown(n-2);
    map.put(n, fibonacci);
    return fibonacci;
  }

  /**
   * The bottom-up (tabulation) dynamic programming approach to implementing the Fibonacci sequence.
   * Time complexity and Space complexity are O(n) for the same reasons as top-down.
   * @param n
   * @return Fibonacci number
   */
  public int bottomUp(int n){
    if( n == 0 || n == 1)
      return n;
    int[] arr = new int[n+1];
    arr[0] = 0;
    arr[1] = 1;
    for(int i = 2; i <= n; i++){
      arr[i] = arr[i-1] + arr[i-2];
    }
    return arr[n];
  }
}
