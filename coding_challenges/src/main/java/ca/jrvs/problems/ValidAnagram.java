package ca.jrvs.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/Valid-Anagram-1a5cc128f9de80b68900ff1aa4db950f
 */
public class ValidAnagram {

  /**
   * The sorting approach to the ValidAnagram problem.
   * https://leetcode.com/problems/valid-anagram/
   * This approach sorts the given strings and checks if they are equal.
   * Time complexity is O(nlogn) due to sorting the strings.
   * Space complexity is O(n) to store the strings as char arrays.
   * @param s
   * @param t
   * @return boolean
   */
  public boolean sorting(String s, String t){
    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();
    Arrays.sort(sArray);
    Arrays.sort(tArray);

    return Arrays.equals(sArray, tArray);
  }

  /**
   * The counter approach to the ValidAnagram problem.
   * This approach loops through the first string to add the chars into a hashmap and increments a
   * counter for each char. The second loop then finds the char in the hashmap and decrements the
   * counter. Lastly, we check if the values in the hashmap are all equal to 0 (same quantity of
   * chars encountered in the first and second string).
   * Time complexity is O(n) has this approach loops through each string and the hashmap once.
   * Space complexity is O(n) to store the counter in the hashmap.
   * @param s
   * @param t
   * @return boolean
   */
  public boolean optimal(String s, String t){
    Map<Character, Integer> map = new HashMap<>();

    for(char c : s.toCharArray()){
      if(map.containsKey(c))
        map.put(c, map.get(c)+1);
      else
        map.put(c, 1);
    }

    for(char c : t.toCharArray()){
      if(map.containsKey(c))
        map.put(c, map.get(c)-1);
      else
        return false;
    }
    for(int count: map.values()){
      if( count != 0)
        return false;
    }
    return true;
  }
}
