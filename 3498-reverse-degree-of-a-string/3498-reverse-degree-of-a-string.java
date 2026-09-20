class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            // 'z' gives 1, 'y' gives 2, ..., 'a' gives 26
            int revAlphabetPos = 'z' - s.charAt(i) + 1;
            int stringPos = i + 1;
            
            totalDegree += revAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}