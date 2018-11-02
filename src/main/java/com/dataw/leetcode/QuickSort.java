package com.dataw.leetcode;

import java.util.Arrays;

/**
 * @author Kinyi_Chan
 * @since 2018-09-13
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 8, 10, 5};
        sort(arr, 0, arr.length - 1);
//        bubble(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        int i, j, t, temp;
        if (left > right) {
            return;
        }
        //temp中存的就是基准数
        temp = arr[left];
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (arr[j] >= temp && i < j) {
                j--;
            }
            //再找左边的
            while (arr[i] <= temp && i < j) {
                i++;
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        //最终将基准数归位
        arr[left] = arr[i];
        arr[i] = temp;
        //继续处理左边的，这里是一个递归的过程
        sort(arr, left, i - 1);
        //继续处理右边的，这里是一个递归的过程
        sort(arr, i + 1, right);
    }

    private static void bubble(int[] arr, int size) {
        int t;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }
}
