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
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node to handle edge cases where head itself has duplicates
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        
        while (head != null) {
            // Check if current node is a start of duplicates
            if (head.next != null && head.val == head.next.val) {
                // Move head forward till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Skip all duplicates
                prev.next = head.next;
            } else {
                // No duplicate found, move prev pointer forward
                prev = prev.next;
            }
            // Move head forward
            head = head.next;
        }
        
        return dummy.next;
    }
}