package ca.jrvs.practice.codingChallenge;

import java.util.*;

/**
 * Two Sum https://www.notion.so/jarvisdev/Two-Sum-865ec0fc843f4401aa20b48c2ff1ecb4 //
 */
public class TwoSum {

  /**
   * The complexity is O(n^2) using two loops
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    if (nums.length == 0) {
      throw new IllegalArgumentException("Invalid Input");
    }
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (i != j && nums[i] + nums[j] == target) {
          result[0] = i;
          result[1] = j;
          return result;
        }
      }
    }
    return result;
  }

  /**
   * The complexity is O(n) using one loop and Hashmap
   */
  public static int[] twoSumWithHash(int[] nums, int target) {
    int[] arr = new int[2];
    Map<Integer, Integer> visitedNums = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      Integer value = visitedNums.get(target - nums[i]);
      if (value == null) { /* no match found */
        visitedNums.put(nums[i], i);
      } else {     /* pair found, updating index */
        arr[0] = value;
        arr[1] = i;
        break; //  loop exit
      }
    }
    return arr;
  }
}



