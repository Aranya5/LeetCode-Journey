class Solution {
    public List<Integer> genEachRow(int n){
        int ans = 1;
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        for(int i=1; i<n; i++){
            ans=ans*(n-i);
            ans=ans/i;
            temp.add(ans);
        }
        return temp;
    }
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> finalAns = new ArrayList<>();
        for(int i=1; i<=numRows; i++){
            finalAns.add(genEachRow(i));
        }
        return finalAns;
    }
}