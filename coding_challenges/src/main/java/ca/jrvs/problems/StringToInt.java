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
   * @param s
   * @return
   */
  public int atoiCustom(String s){
    int sign = 1;
    int i = 0;
    long number = 0;
    if(s.length() == 0)
      return 0;
    if(s.charAt(0) == ' '){
      while(i < s.length()){
        if(s.charAt(i) == ' ')
          i++;
        else
          break;
      }
    }
    if( i < s.length()){
      if(s.charAt(i) == '-'){
        sign *= -1;
        i++;
      }else if(s.charAt(i) == '+'){
        i++;
      }
    }
    while(i < s.length()){
      if(s.charAt(i) < '0' || s.charAt(i) > '9')
        break;
      number *= 10;
      number += s.charAt(i) - '0';
      i++;
      if(number * sign > Integer.MAX_VALUE)
        return Integer.MAX_VALUE;
      else if(number * sign < Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
    }

    return (int)number * sign;
  }
}
