class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int i = 0;
        int ones = 0;
        String res = "";

        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                ones++;
            }

            // Shrink window from the left if we have excess ones or leading zeros
            while (i <= j && (ones > k || s.charAt(i) == '0')) {
                if (s.charAt(i) == '1') {
                    ones--;
                }
                i++;
            }

            // When exactly k ones are present, evaluate the candidate substring
            if (ones == k) {
                String temp = s.substring(i, j + 1);

                if (res.isEmpty() || temp.length() < res.length() ||
                        (temp.length() == res.length() && temp.compareTo(res) < 0)) {
                    res = temp;
                }
            }
        }

        return res;
    }
}