package com.dataw.leetcode;

import java.util.Arrays;

/**
 * @author Kinyi_Chan
 * @since 2018-09-29
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int i = new SearchInsert().searchInsert(nums, 2);
        System.out.println(i);
    }
}
