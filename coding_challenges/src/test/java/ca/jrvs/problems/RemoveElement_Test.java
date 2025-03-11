package ca.jrvs.problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RemoveElement_Test {
  private RemoveElement removeElement;

  @Before
  public void setUp(){
    removeElement = new RemoveElement();
  }

  @Test
  public void removeElement(){
    assertEquals(2, removeElement.removeElement(new int[] {3,2,2,3}, 3));
    assertEquals(5, removeElement.removeElement(new int[] {0,1,2,2,3,0,4,2}, 2));
  }
}
