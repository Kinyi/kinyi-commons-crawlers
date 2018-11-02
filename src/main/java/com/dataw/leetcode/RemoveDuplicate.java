package com.dataw.leetcode;

import java.util.Arrays;

/**
 * @author Kinyi_Chan
 * @since 2018-09-28
 */
public class RemoveDuplicate {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[i] != nums[j] && i++ + 1 != j) {
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {};
        int length = new RemoveDuplicate().removeDuplicates(nums);
        System.out.println(length);
        System.out.println(Arrays.toString(nums));
    }
}
