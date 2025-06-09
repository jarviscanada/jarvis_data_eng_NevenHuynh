package ca.jrvs.problems;

import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-1a0cc128f9de805b93e8d793298477c9
 */
public class CompareTwoMaps {

  /**
   * The time complexity to compare two maps depends on the Map used. For a Hashmap, it will take
   * O(n) time on average to compare the key-value pairs of two maps. Each entry lookup takes O(1)
   * time without hash collisions and in the worst case, O(n) time with many hash collisions. With n
   * entries, the overall time complexity is O(n), O(n^2) in the worst case.
   * @param m1
   * @param m2
   * @return The boolean value of whether the maps are equal or not
   * @param <K>
   * @param <V>
   */
  public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){
    return m1.equals(m2);
  }
}
