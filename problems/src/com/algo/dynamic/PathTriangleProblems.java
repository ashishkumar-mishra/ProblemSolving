package com.algo.dynamic;

import java.util.List;

public class PathTriangleProblems {

	public int minSumPath(int[][] inputArray) {
		return minSumPathHelper(inputArray, 0, 0);
	}

	private int minSumPathHelper(int[][] inputArray, int m, int n) {
		System.out.println(m + ", " + n);
		if (m > inputArray.length - 1 || n > inputArray[0].length - 1) {
			return Integer.MAX_VALUE;
		}
		if (m == inputArray.length - 1) {
			return inputArray[m][n];
		} else {
			return inputArray[m][n]
					+ Math.min(minSumPathHelper(inputArray, m + 1, n), minSumPathHelper(inputArray, m + 1, n + 1));
		}
	}

	public int minPathSumDynamic(List<List<Integer>> triangle) {
		int triangleLength = triangle.size();
		int[] results = new int[triangle.get(triangleLength - 1).size()];

		// setting the value for bottom rows
		for (int i = 0; i < results.length; i++) {
			results[i] = triangle.get(triangleLength - 1).get(i);
		}

		// traversing the upper rows

		for (int i = triangleLength - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				results[j] = triangle.get(i).get(j) + Math.min(results[j],results[j + 1]);
			}
		}
		return results[0];
	}

	public static void main(String[] args) {
		PathTriangleProblems pathTriangleProblems = new PathTriangleProblems();
		int[][] array = { { 2, 0, 0 }, { 3, 2, 0 }, { 1, 6, 7 } };
		System.out.println(pathTriangleProblems.minSumPath(array));
	}
}
