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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prevLast = null;

        while (temp != null) {
            // 1. Find the k-th node to mark the end of the current group
            ListNode kthNode = getKthNode(temp, k);
            
            // 2. If there are fewer than k nodes left, link the remaining list and break
            if (kthNode == null) {
                if (prevLast != null) {
                    prevLast.next = temp;
                }
                break;
            }
            
            // 3. Sever the group from the rest of the list
            ListNode nextNode = kthNode.next;
            kthNode.next = null;

            // 4. Reverse the isolated k-group
            reverseLL(temp);

            // 5. Connect the reversed group back to the main list
            if (temp == head) {
                head = kthNode; // Update the main head if this is the first group
            } else {
                prevLast.next = kthNode; // Link the previous group's tail to this group's new head
            }
            
            // 6. Update pointers for the next iteration
            prevLast = temp; // After reversal, 'temp' is now the tail of this group
            temp = nextNode; // Move to the start of the next group
        }

        return head;
    }

    // Helper method to find the k-th node from a given starting node
    private ListNode getKthNode(ListNode temp, int k) {
        k -= 1; 
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

    // Helper method to reverse a standalone linked list
    private void reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
    }
}