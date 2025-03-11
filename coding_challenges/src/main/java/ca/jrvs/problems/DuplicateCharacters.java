package ca.jrvs.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Finding the duplicated characters in a string problem.
 *
 */
public class DuplicateCharacters {

  /**
   * This approach utilizes a hashmap to store the count of all chars that appear in a string. Any
   * chars that appear more than once are returned.
   * Time complexity is O(n) to iterate through the string and go through the hashmap.
   * Space complexity is O(n) for the hashmap and output string array.
   * @param s
   * @return String[]
   */
  public String[] findDuplicates(String s){
    Map<Character, Integer> duplicates = new HashMap<>();
    int n = s.length();
    int size = 0;

    for(int i=0; i < n ;i++){
      char c = s.charAt(i);
      if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
        if (duplicates.containsKey(s.charAt(i))) {
          duplicates.merge(s.charAt(i), 1, Integer::sum);
          size++;
        } else {
          duplicates.put(s.charAt(i), 1);
        }
      }
    }

    String[] output = new String[size];
    int i = 0;
    for(char c : duplicates.keySet()){
      if(duplicates.get(c) > 1) {
        output[i] = ""+c;
        i++;
      }
    }
    return output;
  }
}
