package com.dataw.leetcode;

import java.util.Stack;

/**
 * @author Kinyi_Chan
 * @since 2018-09-30
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c != ')' && top == '(') {
                    return false;
                }
                if (c != ']' && top == '[') {
                    return false;
                }
                if (c != '}' && top == '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("]"));
    }

    private void quickSort(int[] arr, int left, int right) {
        int i, j, t, temp;
        if (left > right) {
            return;
        }
        i = left;
        j = right;
        temp = arr[left];
        while (i != j) {
            while (arr[j] >= temp && i < j) {
                j--;
            }
            while (arr[i] <= temp && i < j) {
                i++;
            }
            t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
        arr[left] = arr[i];
        arr[i] = temp;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
