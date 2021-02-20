package com.algo.dynamic;

public class CountPartition {
	// Given two numbers n and k where n represents a number of elements in a set,
	// find a number of ways to partition the set into k subsets.

	public int countPartition(int n, int k) {
		// base cases
		if (n == 0 || k == 0 || k > n) {
			return 0;
		}
		if (n == k || k == 1) {
			return 1;
		}

		return k * countPartition(n - 1, k) + countPartition(n - 1, k - 1);
	}

	public int countPartitionDynamic(int n, int k) {
		int[][] result = new int[n + 1][k + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				// case only one partition or elements equal to partition
				if (j == 1 || i == j) {
					result[i][j] = 1;
				} else {
					result[i][j] = j * result[i - 1][j] + result[i - 1][j - 1];
				}
			}
		}
		return result[n][k];
	}
	
	public static void main(String[] args) {
		CountPartition countPartition = new CountPartition();
		System.out.println(countPartition.countPartition(3, 2));
		System.out.println(countPartition.countPartitionDynamic(4, 2));
	}
}
