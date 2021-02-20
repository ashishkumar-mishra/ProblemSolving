package com.algo.dynamic;

public class CoinChangeProblem {

	// Given a value N, if we want to make change for N cents, and we have infinite
	// supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we
	// make the change? The order of coins doesn’t matter.
	// For example, for N = 4 and S = {1,2,3}, there are four solutions:
	// {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For N = 10 and S = {2,
	// 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5}
	// and {5,5}. So the output should be 5.

	public int countNumberOfWays(int[] coins, int m, int value) {

		// in case value is 0, there is one solution to include nothing
		if (value == 0) {
			return 1;
		}

		// in case value is
		if (value < 0) {
			return 0;
		}

		// if there is no coin and value is > 0 there is no solution

		if (m <= 0 && value >= 1) {
			return 0;
		}

		return countNumberOfWays(coins, m - 1, value) + countNumberOfWays(coins, m, value - coins[m - 1]);
	}

	public int countNumberOfWaysDynamic(int[] coins, int m, int value) {
		int[] table = new int[value + 1];
		table[0] = 1;

		for (int i = 0; i < m; i++) {
			for (int j = coins[i]; j <= value; j++) {
				table[j] = table[j] + table[j - coins[i]];
			}
		}
		return table[value];
	}

	public int countNumberOfWaysDynamic1(int[] coins, int value) {
		int[][] table = new int[coins.length + 1][value + 1];
		// 0 sum column
		for (int i = 0; i <= coins.length; i++) {
			table[i][0] = 1;
		}
		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= value; j++) {
				if (j - coins[i - 1] >= 0) {
					table[i][j] = table[i - 1][j] + table[i][j - coins[i - 1]];
				} else {
					table[i][j] = table[i - 1][j];
				}
			}
		}
		return table[coins.length][value];
	}
	
	public int countMinmumNumberOfCoins(int[] coins, int target) {
		if(target == 0) {
			return 0;
		}
		int count = Integer.MAX_VALUE;
		for(int i = 0; i < coins.length; i++) {
			if(coins[i] <= target) {
				int res =  1 + countMinmumNumberOfCoins(coins,target - coins[i]);
				count = Math.min(count, res);
			}
		}
		return count;
	}

	public int countMinmumNumberOfCoinsDynamic(int[] coins, int coinsCount, int value) {
		int[] table = new int[value + 1];
		table[0] = 0;

		for (int i = 1; i < table.length; i++) {
			table[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i <= value; i++) {
			for (int j = 0; j < coinsCount; j++) {
				if (coins[j] <= i) {
					table[i] = Math.min(table[i - coins[j]] + 1, table[i]);
				}
			}
		}

		return table[value];
	}

	public static void main(String[] args) {
		CoinChangeProblem coinChangeProblem = new CoinChangeProblem();
		int[] coins = { 9, 6, 5, 1 };
		System.out.println(coinChangeProblem.countNumberOfWays(coins, coins.length, 16));
		System.out.println(coinChangeProblem.countNumberOfWaysDynamic(coins, coins.length, 16));
		System.out.println(coinChangeProblem.countNumberOfWaysDynamic1(coins, 16));
		System.out.println(coinChangeProblem.countMinmumNumberOfCoins(coins, 12));
		System.out.println(coinChangeProblem.countMinmumNumberOfCoinsDynamic(coins, coins.length, 12));
	}
}
