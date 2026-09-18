import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Generate valid intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int start = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = start; i <= right; i++) {

                int current = s.charAt(i) - 'a';

                // Character occurs before start
                if (first[current] < start) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}