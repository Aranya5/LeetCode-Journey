class Solution {
    public void reverseString(char[] s) {
        reverseHelper(s, 0);
    }

    private void reverseHelper(char[] s, int i) {
        if (i >= s.length / 2) {
            return;
        }

        swap(s, i, s.length - i - 1);

        reverseHelper(s, i + 1);
    }

    private void swap(char[] s, int left, int right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}