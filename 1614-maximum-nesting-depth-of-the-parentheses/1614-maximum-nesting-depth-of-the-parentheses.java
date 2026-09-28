class Solution {
    public int maxDepth(String s) {
        int maxDpt=0,count=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
                if(count>maxDpt) maxDpt++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
        }
        return maxDpt;
    }
}