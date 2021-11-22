package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareTwoMapsTest {

  @Test
  public void compareMapsTest() {
    CompareTwoMaps twoMapComparison = new CompareTwoMaps();
    Map<Integer, String> map1 = new HashMap<Integer, String>();
    map1.put(1, "A");
    map1.put(2, "B");
    map1.put(3, "C");
    Map<Integer, String> map2 = new HashMap<Integer, String>();
    map2.put(3, "C");
    map2.put(2, "B");
    map2.put(1, "A");
    assertEquals(true, twoMapComparison.compareMap1WithMap2(map1, map2));
  }

  @Test
  public void equalMaps() {
    CompareTwoMaps twoMapComparison = new CompareTwoMaps();
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("Canada", "Ottawa");
    map1.put("Iran", "Tehran");
    map1.put("Turkey", "Istanbul");
    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("Turkey", "Istanbul");
    map2.put("Iran", "Tehran");
    map2.put("Canada", "Ottawa");
    assertEquals(false, twoMapComparison.equalMaps(map1, map2));
  }
}