class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);
        
        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            // If valid, add to the answer and stop generating new levels
            if (isValid(curr)) {
                result.add(curr);
                found = true;
            }

            // If we already found a valid string at this depth, skip generating children
            if (found) continue;

            // Generate all possible states by removing one parenthesis
            for (int i = 0; i < curr.length(); i++) {
                char c = curr.charAt(i);
                
                // We only care about removing parentheses, ignore letters
                if (c != '(' && c != ')') continue;

                String nextState = curr.substring(0, i) + curr.substring(i + 1);
                
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }

        return result;
    }

    // Helper method to check if the string has perfectly balanced parentheses
    private boolean isValid(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) return false; // More closing than opening
            }
        }
        return balance == 0;
    }
}