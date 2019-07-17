package com.zqw.math;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Classname NumberCollections
 * @Description 一些简单的数字算法题
 * @Date 2019/7/9 13:32
 * @Created by zqw
 * @Version 1.0
 */
public class NumberCollections {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        int tmp = 0;
        ListNode listNode = new ListNode(0);
        ListNode ln = listNode;
        while (Objects.nonNull(p) || Objects.nonNull(q)){
            int ip = Objects.isNull(p) ? 0 : p.val;
            int iq = Objects.isNull(q) ? 0 : q.val;
            int sum = ip + iq + tmp;
            tmp = sum / 10;
            sum = sum % 10;
            ln.next = new ListNode(sum);
            ln = ln.next;
            p = p.next;
            q = q.next;
        }
        if(tmp >= 1){
            ln.next = new ListNode(tmp);
        }
        return listNode.next;
    }

    /**
     *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Queue<Integer> queue = new LinkedList<>();
        int start = 0;
        int i=0;
        int max = 0;
        while(i<chars.length){
            int c = (int) chars[i];
            if(queue.contains(c)){
                max = Math.max(max, i - start);
                do{
                    start++;
                }while (c != queue.poll());
            }
            queue.offer(c);
            i++;
        }
        return Math.max(max, i - start);
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0, j=0;
        int sum = nums1.length + nums2.length;
        boolean flag = (sum % 2 == 0);
        int midIndex = sum / 2;
        double mid = 0;
        int p = 0;
        int q = 0;
        int x = 0, y = 0;
        while (i<nums1.length || j<nums2.length){

            if(p == midIndex){
                if(flag){
                    return (p + q) / 2.0;
                } else {
                    return p;
                }
            } else {
                if(nums1[i] < nums2[j]){
                    x = nums1[i];
                    i++;
                } else if(nums1[i] > nums2[j]){
                    y = nums2[j];
                    j++;
                }
            }
            p++;


            q = p;


            if(i == nums1.length){
                i--;
            }
            if(j == nums2.length){
                j--;
            }
            if(nums1[i] < nums2[j]){
                i++;
            } else {
                j++;
            }

        }
        return 0.0;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     */
    public String longestPalindrome(String s) {


        return null;
    }


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     *
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 示例 1:
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     *
     * 示例 2:
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();

        return null;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3, 4}));
    }
}
