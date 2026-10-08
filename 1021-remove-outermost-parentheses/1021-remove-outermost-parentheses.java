class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Only append if it's not the first (outermost) opening bracket
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else if (c == ')') {
                depth--;
                // Only append if it's not the last (outermost) closing bracket
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}