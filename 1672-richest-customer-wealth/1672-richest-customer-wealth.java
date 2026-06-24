class Solution {
    public int maximumWealth(int[][] accounts) {
        int m = accounts.length;
        int n = accounts[0].length;
        int MAX=0;
        for(int j=0; j<n; j++){
            MAX += accounts[0][j];
        }

        for(int i=0; i<m; i++){
            int sum=0;
            for(int j=0; j<n; j++){
                sum += accounts[i][j];
            }
            if(sum >= MAX){
                MAX = sum;
            }
        }
        return MAX;
        
    }
}