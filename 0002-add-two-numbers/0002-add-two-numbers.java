class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create a dummy head to easily anchor our new list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        
        // Loop as long as there are nodes to process or a leftover carry
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            // Add the value from l1 if it exists
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            // Add the value from l2 if it exists
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Calculate the new carry (e.g., 14 / 10 = 1)
            carry = sum / 10;
            
            // Create a new node with the 1s digit of the sum (e.g., 14 % 10 = 4)
            curr.next = new ListNode(sum % 10);
            
            // Move our pointer forward safely!
            curr = curr.next;
        }
        
        // Return the actual head of the list, which sits right after the dummy
        return dummy.next;
    }
}