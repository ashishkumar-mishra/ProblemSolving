package com.algo.leet;

/**
 * There are N students in a class. Some of them are friends, while some are
 * not. Their friendship is transitive in nature. For example, if A is a direct
 * friend of B, and B is a direct friend of C, then A is an indirect friend of
 * C. And we defined a friend circle is a group of students who are direct or
 * indirect friends.
 * 
 * Given a N*N matrix M representing the friend relationship between students in
 * the class. If M[i][j] = 1, then the ith and jth students are direct friends
 * with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 * 
 */
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
