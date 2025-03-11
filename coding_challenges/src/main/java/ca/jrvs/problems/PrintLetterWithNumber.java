package ca.jrvs.problems;

/**
 * Print letter with number problem
 * https://www.notion.so/jarvisdev/Print-letter-with-number-1a8cc128f9de8057a915d110c03017a9
 */
public class PrintLetterWithNumber {

  /**
   * This approach compares the ASCII values of the chars to determine whether the char is an
   * upper case letter or lower case. It then subtracts the ASCII value of either 'A' or 'a'
   * accordingly and builds the string by appending the corresponding numeric value.
   * Time complexity is O(n) to loop through the string
   * Space complexity is O(n) to return a new string based on an input string.
   * @param s
   * @return string
   */
  public String solution(String s){
    StringBuilder result = new StringBuilder();

    for(char c : s.toCharArray()){
      if(c < 'a')
        result.append(c).append(c-('A'-1)+26);
      else
        result.append(c).append(c-('a'-1));
    }
    return result.toString();
  }
}
