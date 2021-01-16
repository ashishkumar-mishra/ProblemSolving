package com.algo.dynamic;

public class PerfectSquare {

	/**
	 * A number can always be represented as a sum of squares of other numbers. Note
	 * that 1 is a square and we can always break a number as (1*1 + 1*1 + 1*1 + …).
	 * Given a number n, find the minimum number of squares that sum to number.
	 */

	public int minSquareCount(int num) {
		// base cases
		if (num <= 3) {
			return num;
		}

		int result = num;

		for (int i = 1; i <= num; i++) {
			int square = i * i;
			if (square > num) {
				break;
			}
			result = Math.min(result, 1 + minSquareCount(num - square));
		}
		return result;
	}

	public int minSquareCountDynamic(int num) {
		if (num <= 3) {
			return num;
		}

		int[] dp = new int[num + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;

		for (int i = 4; i <= num; i++) {
			dp[i] = i;
			for (int j = 1; j <= Math.sqrt(i); j++) {
				int tmp = j * j;
				dp[i] = Math.min(dp[i], 1 + dp[i - tmp]);
			}
		}

		return dp[num];
	}

	public static void main(String[] args) {
		PerfectSquare perfectSquare = new PerfectSquare();
		//System.out.println(perfectSquare.minSquareCount(25));
		System.out.println(perfectSquare.minSquareCountDynamic(100));
	}
}
