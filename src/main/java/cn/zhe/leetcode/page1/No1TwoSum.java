package cn.zhe.leetcode.page1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target ，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * </p>
 *
 * <pre>
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * </pre>
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/two-sum">https://leetcode.cn/problems/two-sum</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 *
 * @author zhe
 */
public class No1TwoSum {
    public static void main(String[] args) {
        // 测试数据
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] nums3 = {3, 3};
        int target3 = 6;

        No1TwoSumSolution no1TwoSumSolution = new No1TwoSumSolution();

        // 个人答案测试
        System.out.println("个人答案：");
        no1TwoSumSolution.test1(nums1, target1);
        no1TwoSumSolution.test1(nums2, target2);
        no1TwoSumSolution.test1(nums3, target3);

        // 官方答案测试
        System.out.println("官方答案：");
        no1TwoSumSolution.test2(nums1, target1);
        no1TwoSumSolution.test2(nums2, target2);
        no1TwoSumSolution.test2(nums3, target3);
    }
}

class No1TwoSumSolution {
    /**
     * 个人题解
     *
     * @param nums   数组
     * @param target 目标值
     * @return 结果
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 官方答案
     *
     * @param nums   数组
     * @param target 目标值
     * @return 结果
     */
    public int[] officialAnswer(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public void test1(int[] nums, int target) {
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public void test2(int[] nums, int target) {
        System.out.println(Arrays.toString(officialAnswer(nums, target)));
    }
}
