package ca.jrvs.problems;

/**
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-1a4cc128f9de808baf45f42213925b8d
 */
public class StringToInt {

  /**
   * Atoi function to parse a string into an int. This uses the built-in parseInt method of the
   * Integer class. Time complexity is O(n) as the method reads the string char by char and
   * converts it into an int. Space complexity is O(1) as it uses a constant number of variables.
   * @param string
   * @return
   */
  public int atoiBuiltIn(String string){
    int result;
    try {
      result = Integer.parseInt(string);
    } catch (NumberFormatException e) {
      result = 0;
    }
    return result;
  }

  /**
   * Custom Atoi function to parse a string into an int. This approach reads the string char by char
   * and converts it into an int. Time complexity is O(n) and Space complexity is O(1).
   * @param string
   * @return
   */
  public int atoiCustom(String string){
    int result = 0;
    int sign = 1;
    int i = 0;
    if(string.charAt(0)=='-') {
      sign = -1;
      i++;
    }
    while(i < string.length()){
      result *= 10;
      result += string.charAt(i) - 48;
      i++;
    }
    return result * sign;
  }
}
