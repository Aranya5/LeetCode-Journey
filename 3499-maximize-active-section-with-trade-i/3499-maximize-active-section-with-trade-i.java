class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        String t = "1" + s + "1";

        // Run-length encoding
        java.util.ArrayList<Character> chars = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> lens = new java.util.ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == ch) j++;

            chars.add(ch);
            lens.add(j - i);
            i = j;
        }

        int maxGain = 0;
        int m = chars.size();

        // Interior '1' runs only
        for (int k = 1; k < m - 1; k++) {
            if (chars.get(k) == '1') {
                maxGain = Math.max(maxGain,
                        lens.get(k - 1) + lens.get(k + 1));
            }
        }

        return initialOnes + maxGain;
    }
}