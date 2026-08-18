class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if(k == n){
            int max = -1;
            for(int i=0;i<n;i++){
                max = Math.max(max,nums[i]);
            }
            return max;
        }

        if(k == 1){
            Map<Integer,Integer> mp = new HashMap<>();
            for(int i=0;i<n;i++){
                mp.put(nums[i],mp.getOrDefault(nums[i],0) + 1);
            }

            int max = -1;
            for(int i=0;i<n;i++){
                int c = mp.get(nums[i]);
                if(c == 1){
                    max = Math.max(max,nums[i]);
                }
            }

            return max;
        }

        int start = nums[0];
        int end = nums[n-1];

        if(start == end){
            return -1;
        }

        for(int i=1;i<n-1;i++){
            if(start == nums[i]){
                start = -1;
            }
            if(end == nums[i]){
                end = -1;
            }
        }

        return Math.max(start,end);
    }
}