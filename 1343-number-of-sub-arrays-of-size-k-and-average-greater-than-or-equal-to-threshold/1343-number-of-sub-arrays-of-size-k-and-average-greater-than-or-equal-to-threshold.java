class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int [] presumArr = new int[n];
        int currSum = 0;
        for(int i=0; i<n; i++){
            currSum+=arr[i];
            presumArr[i]=currSum;
        }
        int dev=0, minus =0, count=0;
        for(int i=k-1; i<n; i++){
            dev = presumArr[i]-minus;
            if(dev/k >= threshold){
                count++;
            }
            minus=presumArr[i-k+1];
        }
        return count;
        
    }
}