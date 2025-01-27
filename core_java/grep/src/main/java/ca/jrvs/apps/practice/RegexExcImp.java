package ca.jrvs.apps.practice;

import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc{

  @Override
  public boolean matchJpeg(String filename) {
    Pattern jpegMatch = Pattern.compile(".*\\.jpe?g$", Pattern.CASE_INSENSITIVE);
    return jpegMatch.matcher(filename).matches();
  }

  @Override
  public boolean matchIp(String ip) {
    Pattern ipMatch = Pattern.compile("^([0-9]{1,3}.){3}[0-9]{1,3}$");
    return ipMatch.matcher(ip).matches();
  }

  @Override
  public boolean isEmptyLine(String line) {
    Pattern emptyLineMatch = Pattern.compile("^\\s*$");
    return emptyLineMatch.matcher(line).matches();
  }

  public static void main(String[] args){
    RegexExcImp regex = new RegexExcImp();
    System.out.println("Test if 'test.jpeg' is a jpg file: "+regex.matchJpeg("test.jpeg")); //true
    System.out.println("Test if 'test5.JPG' is a jpg file: "+regex.matchJpeg("test5.JPG")); //true

    System.out.println("\nTest if '111.2.23.9' is a valid IP: "+regex.matchIp("111.2.23.9")); //true
    System.out.println("Test if '111.2.23.9' is a valid IP: "+regex.matchIp("1111.2.23.9")); //false

    System.out.println("\nTest if ' ' is an empty space: "+regex.isEmptyLine(" ")); //true
    System.out.println("Test if 'false' is an empty space: "+regex.isEmptyLine("false")); //false
  }
}
