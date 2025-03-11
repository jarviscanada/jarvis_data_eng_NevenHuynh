package ca.jrvs.problems;

/**
 * https://www.notion.so/jarvisdev/Valid-Palindrome-1a4cc128f9de8094b96ecf3cb8c55990
 */
public class ValidPalindrome {

  /**
   * Two pointers approach to validating a string as a palindrome.
   * https://leetcode.com/problems/valid-palindrome/description/
   * Time Complexity O(n) as this approach uses a loop that will run at most n/2 times with n being
   * the size of the string.
   * Space Complexity O(n) for the new strings created for preprocessing.
   * @param s
   * @return boolean
   */
  public boolean twoPointers(String s){
    s = s.replaceAll("[^a-zA-Z0-9]", "");
    s = s.toLowerCase();
    int start = 0;
    int end = s.length() - 1;
    if(start == end)
      return true;

    while(start < end){
      if(s.charAt(start) != s.charAt(end))
        return false;
      start++;
      end--;
    }
    return true;
  }

  /**
   * Recursive approach to validating a string as a palindrome.
   * Time complexity is O(n) as we are calling the recursion function at most n/2 times with n being
   * the size of the string and preprocessing the string takes O(n) time.
   * Space complexity is O(n) as the recursive call stack grows to O(n/2).
   * @param s
   * @return
   */
  public boolean recursion(String s){
    s = s.replaceAll("[^a-zA-Z0-9]", "");
    s = s.toLowerCase();
    int start = 0;
    int end = s.length()-1;

    if(s.isEmpty() || s.length()==1)
      return true;
    if(s.charAt(start) == s.charAt(end))
      return recursionHelper(s, ++start, --end);
    else
      return false;
  }
  public boolean recursionHelper(String s, int start, int end){
    if(start > end)
      return true;
    if(s.charAt(start) == s.charAt(end))
      return recursionHelper(s, ++start, --end);
    else
      return false;
  }
}
