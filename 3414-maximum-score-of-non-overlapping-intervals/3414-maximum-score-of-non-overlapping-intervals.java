import java.util.*;

class Solution {
    private class Interval {
        int start, end, weight, id;
        public Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    private class State {
        long score;
        List<Integer> indices;
        public State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        
        // Parse the List of Lists into our custom Interval objects
        for (int i = 0; i < n; i++) {
            List<Integer> current = intervals.get(i);
            arr[i] = new Interval(current.get(0), current.get(1), current.get(2), i);
        }
        
        Arrays.sort(arr, (a, b) -> Integer.compare(a.start, b.start));
        
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0L, new ArrayList<>());
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            int nextIdx = n;
            int left = i + 1, right = n - 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2; 
                if (arr[mid].start > arr[i].end) {
                    nextIdx = mid;
                    right = mid - 1; 
                } else {
                    left = mid + 1;
                }
            }
            
            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                
                long takeScore = (long) arr[i].weight + dp[nextIdx][k - 1].score; 
                List<Integer> takeIndices = new ArrayList<>();
                takeIndices.add(arr[i].id);
                takeIndices.addAll(dp[nextIdx][k - 1].indices);
                Collections.sort(takeIndices); 
                
                if (takeScore > skip.score) {
                    dp[i][k] = new State(takeScore, takeIndices);
                } else if (takeScore == skip.score) {
                    if (isLexicographicallySmaller(takeIndices, skip.indices)) {
                        dp[i][k] = new State(takeScore, takeIndices);
                    } else {
                        dp[i][k] = skip;
                    }
                } else {
                    dp[i][k] = skip;
                }
            }
        }
        
        List<Integer> best = dp[0][4].indices;
        int[] result = new int[best.size()];
        for (int i = 0; i < best.size(); i++) {
            result[i] = best.get(i);
        }
        return result;
    }
    
    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() > b.size(); 
    }
}