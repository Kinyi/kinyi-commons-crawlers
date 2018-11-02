package com.dataw.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kinyi_Chan on 04/01/2017.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    //时间复杂度为 O(N^2)
    public int[] twoSum(int[] nums, int target) {

        int size = nums.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] arr = {i, j};
                    return arr;
                }
            }
        }
        return null;
    }

    //时间复杂度为 O(N)
    public int[] twoSumOptimize(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value == null)
                map.put(nums[i], i);
            int diff = target - nums[i];
            Integer diff_index = map.get(diff);
            if (diff_index != null && diff_index < i) {
                int[] rst = {diff_index, i};
                return rst;
            }
        }
        return null;
    }

}
