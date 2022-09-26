package cn.zhe.leetcode.page1;

/**
 * <p>
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * </p>
 *
 * <pre>
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * </pre>
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/add-two-numbers">https://leetcode.cn/problems/add-two-numbers</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 *
 * @author zhe
 */
public class No2AddTwoNumbers {
    public static void main(String[] args) {
        // 测试数据
        // 示例1
        ListNode l11 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l21 = new ListNode(5, new ListNode(6, new ListNode(4)));
        // 示例2
        ListNode l12 = new ListNode(0);
        ListNode l22 = new ListNode(0);
        // 示例3
        ListNode l13 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l23 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));

        No2AddTwoNumbersSolution no2AddTwoNumbersSolution = new No2AddTwoNumbersSolution();

        System.out.println("个人答案：");
        System.out.println(no2AddTwoNumbersSolution.addTwoNumbers(l11, l21));
        System.out.println(no2AddTwoNumbersSolution.addTwoNumbers(l12, l22));
        System.out.println(no2AddTwoNumbersSolution.addTwoNumbers(l13, l23));

        System.out.println();

        System.out.println("官方答案：");
        System.out.println(no2AddTwoNumbersSolution.officialAnswer(l11, l21));
        System.out.println(no2AddTwoNumbersSolution.officialAnswer(l12, l22));
        System.out.println(no2AddTwoNumbersSolution.officialAnswer(l13, l23));
    }
}

class No2AddTwoNumbersSolution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Head = l1;
        ListNode l2Head = l2;

        ListNode result = new ListNode(0);
        ListNode rHead = result;

        int addend = 0;
        while (l1Head != null || l2Head != null) {
            int sum = rHead.val + addend;

            if (l1Head != null) {
                sum += l1Head.val;
                l1Head = l1Head.next;
            }

            if (l2Head != null) {
                sum += l2Head.val;
                l2Head = l2Head.next;
            }

            addend = sum / 10;

            rHead.val = sum % 10;
            if (l1Head != null || l2Head != null) {
                rHead.next = new ListNode(0);
                rHead = rHead.next;
            } else if (addend != 0) {
                rHead.next = new ListNode(addend);
                rHead = rHead.next;
            }
        }

        return result;
    }

    public ListNode officialAnswer(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[" + this.val);
        ListNode next = this.next;
        while (next != null) {
            str.append(", ").append(next.val);
            next = next.next;
        }
        return str.append("]").toString();
    }
}
