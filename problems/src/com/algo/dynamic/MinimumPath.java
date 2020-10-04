package com.algo.dynamic;

public class MinimumPath {

	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its
	 * path.You can only move either down or right at any point in time.
	 */

	public int minPathSum(int[][] grid) {
		return getMinPathSum(grid, grid.length - 1, grid[0].length - 1);
	}

	private int getMinPathSum(int[][] grid, int m, int n) {
		if (m < 0 || n < 0) {
			return Integer.MAX_VALUE;
		} else if (m == 0 && n == 0) {
			return grid[m][n];
		} else {
			return grid[m][n] + Math.min(getMinPathSum(grid, m - 1, n), getMinPathSum(grid, m, n - 1));
		}
	}

	public int minPathSumDp(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int[][] resultArray = new int[rows][cols];
		resultArray[0][0] = grid[0][0];
		
		for(int i = 1; i < rows; i++) {
			resultArray[i][0] = resultArray[i - 1][0] + grid[i][0];
		}
		
		for(int j = 1; j < cols; j++) {
			resultArray[0][j] = resultArray[0][j - 1] + grid[0][j];
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				resultArray[i][j] = grid[i][j] + Math.min(resultArray[i - 1][j], resultArray[i][j - 1]);
			}
		}
		return resultArray[rows - 1][cols - 1];
	}

	public static void main(String[] args) {
		//int[][] paths = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int[][] paths = {{1,3,5,1},{3,2,5,3}};
		MinimumPath minimumPath = new MinimumPath();
		System.out.println(minimumPath.minPathSum(paths));
		System.out.println(minimumPath.minPathSumDp(paths));
	}
}
