package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-e179fa3736334137b7ab81b1d047a381
 *
 */
class CompareTwoMaps {

  /**
   *
   * @param map1, map2, <k>, <v>
   *  Big-O: O(n)
   *  Justification: Compare every key and value pairs
   * @return
   */
  public <k,v> boolean compareMap1WithMap2(Map<k,v> map1, Map<k,v> map2) {
    if (map1.equals(map2))
      return true;
    else
      return false;
  }

  /**
   * Big-O: O(n)
   * Justification: Compare the size and key-value pairs
   * @return
   */
  public <K,V> boolean equalMaps(Map<K,V> map1, Map<K,V> map2) {
    if (map1.size() != map2.size()) {
      for (K key : map1.keySet())
        if (!map1.get(key).equals(map2.get(key)))
          return false;
      return true;
    }
    return false;
  }
}
