package com.dataw.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2018-09-29
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                List<Integer> stepRes = new ArrayList<>();
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    stepRes.add(nums[i]);
                    stepRes.add(nums[l]);
                    stepRes.add(nums[r]);
                    if (!res.contains(stepRes)) {
                        res.add(stepRes);
                    }
                }
                if (sum <= 0) {
                    ++l;
                } else {
                    --r;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
