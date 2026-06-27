class Solution {
    public int lengthOfLongestSubstring(String s) {
        int st=0,end=0, longestString=0;
        HashSet<Character> window = new HashSet<>();
        for(end=0; end<s.length(); end++){
            
            while(window.contains(s.charAt(end))){
                window.remove(s.charAt(st));
                st++;
            }
            int currentLength = end-st+1;
            longestString= Math.max(longestString,currentLength);

            window.add(s.charAt(end));
        }
        return longestString; 
    }
}