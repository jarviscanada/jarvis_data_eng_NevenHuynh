package ca.jrvs.problems;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class DuplicateCharacters_Test {

  private DuplicateCharacters duplicateCharacters;

  @Before
  public void setUp(){
    duplicateCharacters = new DuplicateCharacters();
  }

  @Test
  public void findDuplicates(){
    String[] expected = {"a", "c"};
    String input = "A black cat";

    assertArrayEquals(expected, duplicateCharacters.findDuplicates(input));
  }
}
