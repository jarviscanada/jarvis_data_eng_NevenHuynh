package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringToInt_Test {

  private StringToInt stringToInt;

  @Before
  public void setUp(){
    stringToInt = new StringToInt();
  }

  @Test
  public void atoiBuiltIn(){
    assertEquals(5432, stringToInt.atoiBuiltIn("5432"));
    assertEquals(-5432, stringToInt.atoiBuiltIn("-5432"));
  }

  @Test
  public void atoiCustom(){
    assertEquals(5432, stringToInt.atoiCustom("5432"));
    assertEquals(-5432, stringToInt.atoiCustom("-5432"));
  }
}
