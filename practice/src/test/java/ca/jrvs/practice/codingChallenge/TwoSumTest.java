package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoSumTest {
  TwoSum twoSum = new TwoSum();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void twoSum() {
    int[] nums = {1,2,3,5};
    int target = 3;
    TwoSum twoSum = new TwoSum();
    int[] answer = {0, 1};
    assertArrayEquals(answer, twoSum.twoSum(nums, target));
  }

  @Test
  public void twoSumWithHash() {
    int[] nums = {1,2,3,5};
    int target= 3;
    int[] answer = {0,1};
    assertArrayEquals(answer, twoSum.twoSumWithHash(nums,target));
  }
}
