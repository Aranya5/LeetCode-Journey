/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode h1 = headA, h2 = headB;

        while (h1 != h2) {
            h1 = h1.next;
            h2 = h2.next;

            if (h1 == h2)
                return h1;

            if (h1 == null)
                h1 = headB;
            if (h2 == null)
                h2 = headA;
        }

        return h1;
    }
}