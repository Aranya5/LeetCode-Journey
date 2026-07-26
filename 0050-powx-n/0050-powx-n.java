class Solution {
    public double myPow(double x, int n) {
        // Convert n to a long to prevent overflow when negating Integer.MIN_VALUE
        long N = n;
        
        // If the power is negative, invert the base and make the power positive
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        while (N > 0) {
            // If the current power is odd, multiply the base into our result
            if (N % 2 == 1) {
                result = result * currentProduct;
            }
            
            // Square the base and halve the power
            currentProduct = currentProduct * currentProduct;
            N = N / 2;
        }
        
        return result;
    }
}