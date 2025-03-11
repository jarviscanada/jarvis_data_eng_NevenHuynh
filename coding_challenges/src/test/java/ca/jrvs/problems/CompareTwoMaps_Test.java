package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CompareTwoMaps_Test {

  private CompareTwoMaps compareTwoMaps;
  Map<String, String> m1;
  Map<String, String> m2;
  @Before
  public void setUp(){
    compareTwoMaps = new CompareTwoMaps();
  }
  @Test
  public void compareMaps1(){
    m1 = new HashMap<>();
    m2 = new HashMap<>();
    m1.put("one", "chicken");
    m1.put("two", "beef");
    m2.put("one", "chicken");
    m2.put("two", "beef");
    assertTrue(compareTwoMaps.compareMaps(m1, m2));
  }

  @Test
  public void compareMaps2(){
    m1 = new HashMap<>();
    m2 = new HashMap<>();
    m1.put("one", "apple");
    m2.put("one", "banana");
    assertFalse(compareTwoMaps.compareMaps(m1, m2));
  }

  @Test
  public void compareMaps3(){
    m1 = new HashMap<>();
    m2 = new HashMap<>();
    m1.put("one", "apple");
    m2.put("two", "apple");
    assertFalse(compareTwoMaps.compareMaps(m1, m2));
  }
}
