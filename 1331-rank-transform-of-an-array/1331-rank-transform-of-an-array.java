class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sortedArray = arr.clone();
        Arrays.sort(sortedArray);

        HashMap<Integer, Integer> sortMap = new HashMap<>();
        int rank=1;
        for(int num:sortedArray){
            if(!(sortMap.containsKey(num))){
                sortMap.put(num,rank);
                rank++;
            }
        }
        for(int i=0; i<arr.length; i++){
            arr[i] = sortMap.get(arr[i]);
        }

        return arr;
        
    }
}