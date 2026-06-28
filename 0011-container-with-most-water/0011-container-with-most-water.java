class Solution {
    public int maxArea(int[] height) {
        int left=0, right=height.length-1;
        int maxWater=0;
        while(left<right){
            int currentWater = (right-left)*Math.min(height[left],height[right]);
            maxWater = Math.max(maxWater,currentWater);

            if(height[right]<height[left]){
                right--;
            }else{
                left++;
            }
        }
        return maxWater;
        
    }
}