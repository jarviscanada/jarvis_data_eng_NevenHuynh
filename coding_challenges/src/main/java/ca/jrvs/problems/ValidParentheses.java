package ca.jrvs.problems;

import java.util.Stack;

/**
 * https://www.notion.so/jarvisdev/Valid-Parentheses-1a4cc128f9de80c3b214cd4ebaa50834
 */
public class ValidParentheses {

  /**
   * This method checks if a given string contains valid parentheses.
   * https://leetcode.com/problems/valid-parentheses/description/
   * Time complexity is O(n) as this approach uses a single pass loop to check the
   * @param s
   * @return boolean
   */
  public boolean isValid(String s){
    Stack<Character> stack = new Stack<>();
    int i=0;
    while(i<s.length()){
      if(s.charAt(i)=='(')
        stack.push(')');
      else if (s.charAt(i)=='[')
        stack.push(']');
      else if(s.charAt(i)=='{')
        stack.push('}');
      else if (stack.isEmpty() || stack.pop() != s.charAt(i))
        return false;
      i++;
    }
    return stack.isEmpty();
  }
}
