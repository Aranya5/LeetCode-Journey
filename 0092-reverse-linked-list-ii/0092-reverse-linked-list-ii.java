/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode leftPrev = dummy;

        ListNode curr = dummy.next;

        for (int i = 0; i < left - 1; i++) {
            leftPrev = leftPrev.next;
            curr = curr.next;
        }

        ListNode prev = null;
        for (int i = 0; i < right - left+1; i++) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        leftPrev.next.next = curr;
        leftPrev.next = prev;

        return dummy.next;
    }
}