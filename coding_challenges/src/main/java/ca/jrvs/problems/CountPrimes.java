package ca.jrvs.problems;

import java.util.Arrays;

/**
 * Count primes problem
 * https://www.notion.so/jarvisdev/Count-Primes-1a8cc128f9de80c480d0cc0018171a83
 * https://leetcode.com/problems/count-primes/description/
 */
public class CountPrimes {

  /**
   * This approach uses the Sieve of Eratosthenes algorithm to mark each multiple of a prime number
   * as false to avoid checking non-prime numbers.
   * Time complexity of Sieve of Eratosthenes is O(n log(log n))
   * Space complexity is O(n) for the boolean array
   * @param n
   * @return int
   */
  public int countPrimes(int n) {
    int counter = 0;
    if(n <= 1)
      return 0;
    boolean[] arr = new boolean[n];
    Arrays.fill(arr, true);
    arr[0] = false;
    arr[1] = false;

    for(int i=2; i*i < n; i++){
      if(arr[i]){
        for(int j = i*i; j < n; j+=i)
          arr[j] = false;
      }
    }

    for(boolean value : arr){
      if(value)
        counter++;
    }
    return counter;
  }
}
