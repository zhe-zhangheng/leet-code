package cn.zhe.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LeetCodeMainTests {
    public static void main(String[] args) {
        // int[] nums = {2, 7, 11, 15};
        // int target = 9;

        // int[] nums = {3, 2, 4};
        // int target = 6;

        int[] nums = {3, 3};
        int target = 6;
        int[] answer = twoSum(nums, target);

        System.out.println(Arrays.toString(answer));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> anotherNumMap = new HashMap<>();
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            if (anotherNumMap.containsKey(num)) {
                return new int[]{anotherNumMap.get(num), i};
            }
            anotherNumMap.put(target - num, i);
        }
        return null;
    }
}
