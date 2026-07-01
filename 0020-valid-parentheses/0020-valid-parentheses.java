class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 

            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char openBracket = stack.pop();

                if (ch == ')' && openBracket != '(') return false;
                if (ch == '}' && openBracket != '{') return false;
                if (ch == ']' && openBracket != '[') return false;
            }
        }

        return stack.isEmpty();
    }
}