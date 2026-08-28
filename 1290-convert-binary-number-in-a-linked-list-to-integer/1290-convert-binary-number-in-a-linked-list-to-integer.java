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
    public int getDecimalValue(ListNode head) {
        int result = 0;
        ListNode curr = head;

        while (curr != null) {
            // Shift current result left by 1 bit and append the new bit
            result = (result << 1) | curr.val;
            curr = curr.next;
        }

        return result;
    }
}