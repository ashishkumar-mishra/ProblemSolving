package com.algo.leet;

public class FriendCircles {

	// This problem is similar to find out the number of components in graph
	public int findCircleNum(int[][] matrix) {
		int row = matrix.length;
		int count = 0;
		boolean[] visited = new boolean[row];
		for (int i = 0; i < row; i++) {
			if (!visited[i]) {
				dfs(matrix, visited, i);
				count++;
			}
		}
		return count;
	}

	private void dfs(int[][] matrix, boolean[] visited, int index) {
		visited[index] = true;
		for (int j = 0; j < matrix.length; j++) {
			if (matrix[index][j] == 1 && !visited[j]) {
				dfs(matrix, visited, j);
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 1, 0, 0, 0, 0 },

				{ 1, 1, 0, 0, 0, 0 },

				{ 0, 0, 1, 1, 1, 0 },

				{ 0, 0, 1, 1, 0, 0 },

				{ 0, 0, 1, 0, 1, 0 },

				{ 0, 0, 0, 0, 0, 1 } };
		
		FriendCircles friendCircles = new FriendCircles();
		System.out.println(friendCircles.findCircleNum(matrix));
	}
}
