import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Precompute matching parenthesis indices
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIdx = stack.pop();
                pair[openIdx] = i;
                pair[i] = openIdx;
            }
        }

        // Step 2: Traverse with direction reversals
        StringBuilder result = new StringBuilder();
        int curr = 0;
        int step = 1;

        while (curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr]; // Jump to matching bracket
                step = -step;      // Flip direction
            } else {
                result.append(c);
            }
            curr += step;
        }

        return result.toString();
    }
}