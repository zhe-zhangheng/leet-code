package cn.zhe.leetcode.page1;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * </p>
 *
 * <pre>
 *
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * </pre>
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters">https://leetcode.cn/problems/longest-substring-without-repeating-characters</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 *
 * @author zhe
 */
public class No3LengthOfLongestSubstring {
    public static void main(String[] args) {
        // 测试数据
        // 示例1
        String s1 = "abcabcbb";
        // 示例2
        String s2 = "bbbbb";
        // 示例3
        String s3 = "pwwkew";

        String s4 = "pwwkezdwpw";

        No3LengthOfLongestSubstringSolution no3LengthOfLongestSubstringSolution = new No3LengthOfLongestSubstringSolution();

        System.out.println("个人答案：");
        System.out.println(no3LengthOfLongestSubstringSolution.lengthOfLongestSubstring(s1));
        System.out.println(no3LengthOfLongestSubstringSolution.lengthOfLongestSubstring(s2));
        System.out.println(no3LengthOfLongestSubstringSolution.lengthOfLongestSubstring(s3));
        System.out.println(no3LengthOfLongestSubstringSolution.lengthOfLongestSubstring(s4));

        System.out.println();

        System.out.println("官方答案：");
        System.out.println(no3LengthOfLongestSubstringSolution.officialAnswer(s1));
        System.out.println(no3LengthOfLongestSubstringSolution.officialAnswer(s2));
        System.out.println(no3LengthOfLongestSubstringSolution.officialAnswer(s3));
        System.out.println(no3LengthOfLongestSubstringSolution.officialAnswer(s4));
    }
}

class No3LengthOfLongestSubstringSolution {
    public int lengthOfLongestSubstring(String s) {
        int sLength = s.length();
        Set<Character> characterSet = new HashSet<>(128);
        int result = 1;
        for1:
        for (int i = 0; i < sLength; i++) {
            char head = s.charAt(i);
            characterSet.add(head);
            for (int j = i + 1; j < sLength; j++) {
                char tail = s.charAt(j);

                if (head == tail || characterSet.contains(tail)) {
                    result = Math.max(j - i, result);
                    characterSet.clear();
                    continue for1;
                }

                characterSet.add(tail);
            }
        }
        return result;
    }

    public int officialAnswer(String s) {
        int n = s.length();
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>(n << 1);
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}