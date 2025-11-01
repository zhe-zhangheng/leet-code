package cn.zhe.leetcode;

import cn.zhe.leetcode.CommonDomain.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LeetCodeMainTests {
    /**
     * testTwoSum();
     * testAddTwoNumbers();
     * testLengthOfLongestSubstring();
     *
     * @param args args
     */
    public static void main(String[] args) {
        testFindMedianSortedArrays();
    }

    public static void testFindMedianSortedArrays() {
        // 测试数据
        // 示例1
        // int[] nums1 = {1, 3}, nums2 = {2};
        // 示例2
        // int[] nums1 = {1, 2}, nums2 = {3, 4};
        // 示例3
        int[] nums1 = {1, 3, 5, 6, 7}, nums2 = {2, 3, 5};
        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] nums = new int[totalLength];
        int i = 0, j = 0;
        while (i + j < totalLength / 2 + 2) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    nums[i + j] = nums1[i];
                    i++;
                } else {
                    nums[i + j] = nums2[j];
                    j++;
                }
                continue;
            }

            if (i < nums1.length) {
                nums[i + j] = nums1[i];
                i++;
                continue;
            }

            if (j < nums2.length) {
                nums[i + j] = nums2[j];
                j++;
                continue;
            }

            break;
        }

        if ((totalLength & 1) == 0) {
            return (double) (nums[totalLength / 2 - 1] + nums[totalLength / 2]) / 2;
        }

        return nums[totalLength / 2];
    }

    public static void testLengthOfLongestSubstring() {
        // String str = "abcabcbb";  // 3
        // String str = "bbbbb";  // 1
        // String str = "pwwkew";  // 3
        // String str = "cc";  // 1
        // String str = "abc";  // 3
        String str = "dvdf";  // 3
        int maxSubLength = lengthOfLongestSubstring(str);
        System.out.println(maxSubLength);
    }

    public static int lengthOfLongestSubstring(String s) {
        int head = 0, tail = 0, maxSubLength = 0, strLength = s.length();
        Map<String, Integer> subStrMap = new HashMap<>();
        while (tail < strLength) {
            String headChar = String.valueOf(s.charAt(head));
            if (head == tail) {
                subStrMap.put(headChar, head);
                tail++;
                continue;
            }

            String tailChar = String.valueOf(s.charAt(tail));
            if (!subStrMap.containsKey(tailChar)) {
                subStrMap.put(tailChar, tail);
                tail++;
                continue;
            }

            maxSubLength = Math.max(tail - head, maxSubLength);

            head = subStrMap.get(tailChar) + 1;

            int finalHead = head;
            subStrMap.entrySet().removeIf(item -> item.getValue() < finalHead);

            subStrMap.put(tailChar, tail);

            if (tail == strLength - 1) {
                break;
            }

            tail++;
        }

        return Math.max(s.substring(head, tail).length(), maxSubLength);
    }

    public static void testAddTwoNumbers() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        // ListNode l1 = new ListNode(0);
        // ListNode l2 = new ListNode(0);

        // ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        // ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode lSum = addTwoNumbers(l1, l2);
        System.out.println(lSum);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + sum;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail = tail.next = new ListNode(sum % 10);
            }

            sum = sum / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (sum > 0) {
            tail.next = new ListNode(1);
        }

        return head;
    }

    public static void testTwoSum() {
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
