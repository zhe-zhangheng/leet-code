package cn.zhe.leetcode;

import cn.zhe.leetcode.CommonDomain.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LeetCodeMainTests {
    public static void main(String[] args) {
        testLengthOfLongestSubstring();
        // testAddTwoNumbers();
        // testTwoSum();
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
            subStrMap.entrySet().removeIf(item-> item.getValue() < finalHead);

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
