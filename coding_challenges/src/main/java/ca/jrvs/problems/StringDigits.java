package ca.jrvs.problems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if a string only contains digits problem.
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-1a6cc128f9de80bb9428c8c8a1349d52
 */
public class StringDigits {

  /**
   * This approach uses ASCII values to compare the chars in the string and see if they fall between
   * the ASCII ranges for 0 to 9.
   * Time complexity is O(n) to iterate through the string
   * Space complexity is O(1) for the constant number of variables.
   * @param s
   * @return boolean
   */
  public boolean checkAscii(String s){
    int n = s.length();

    for(int i = 0; i < n; i++){
      if(s.charAt(i) < '0' || s.charAt(i) > '9')
        return false;
    }
    return true;
  }

  /**
   * This approach uses the build-in api for the Integer class where it utilizes the parseInt()
   * method. If parseInt() is successful, the function returns true and if it cannot parse the
   * string into an int, it will return false.
   * Time Complexity is O(n) due to parseInt() having to iterate through the string.
   * Space complexity is O(1).
   * @param s
   * @return boolean
   */
  public boolean checkApi(String s){
    try{
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * This approach utilizes regular expressions to check the string.
   * Time complexity is O(n) to match the regular expression and the string
   * Space complexity is O(1)
   * @param s
   * @return boolean
   */
  public boolean checkRegex(String s){
    Pattern regex = Pattern.compile("^[0-9]+$");
    Matcher matcher = regex.matcher(s);
    return matcher.matches();
  }
}
