class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // End index of the last chosen palindrome

        for (int i = 0; i < n; i++) {
            // Check for odd-length palindrome centered at i (length k)
            // or even-length palindrome centered at (i, i+1) (length k+1, etc.)
            for (int len : new int[]{k, k + 1}) {
                int start = i - (len - 1) / 2;
                int end = i + len / 2;

                if (start > lastEnd && end < n && isPalindrome(s, start, end)) {
                    count++;
                    lastEnd = end;
                    break; // Move forward to find the next non-overlapping palindrome
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}