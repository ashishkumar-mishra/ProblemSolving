

package com.algo.leet;


public class DivisionProblem {

    public static void main(String[] args) {
        DivisionProblem problem = new DivisionProblem();
        System.out.println("result = " + problem.divide(-2147483648, -1));
    }

    // 1. subtraction O(n)
    public int divide(int dividend, int divisor) {

        // edge case
        // if overflow happens, return Integer.MAX_VALUE;
        // -2^31 ~ 2^31-1, -2147483648 ~ 2147483647
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // sign
        boolean isNegative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            isNegative = true;
        }
        long ldividend = dividend;
        ldividend = Math.abs(ldividend);
        divisor = Math.abs(divisor);

        long lquotient = 0;
        while (ldividend >= divisor) {
            ldividend -= divisor;
            lquotient++;
        }

        if (isNegative) {
            lquotient = ~lquotient + 1;
        }
        return (int)lquotient;
    }
}
