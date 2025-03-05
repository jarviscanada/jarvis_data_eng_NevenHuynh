package ca.jrvs.problems;

public class RotateString {

  /**
   * The rotateString problem checks if a string 's', can become the goal string after a number of
   * shifts on s. This approach concatenates the string s to itself and checks if it contains the
   * goal string.
   * https://leetcode.com/problems/rotate-string/description/
   * Time complexity is O(n) as checking the lengths of the string, creating a new string and the
   * contains method take O(n) each.
   * Space complexity is O(n) due to the creation of a string that is double the size of s which
   * takes O(2n) = O(n) space.
   * @param s
   * @param goal
   * @return boolean
   */
  public boolean rotate(String s, String goal) {
    if (s.length() != goal.length())
      return false;

    String doubledS = s + s;
    return doubledS.contains(goal);
  }
}
