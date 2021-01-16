package com.algo.leet;

public class SparseMatrixMultiplication {

	public static int[][] multiply(int[][] A, int[][] B) {
		int[][] result = new int[A.length][B[0].length];

		int r = 0;
		for (int[] a : A) {
			int col = 0;
			while (col < B[0].length) {
				int row = 0;
				int sum = 0;
				while (row < B.length) {
					sum += a[row] * B[row][col];
					row++;
				}
				result[r][col++] = sum;
			}
			r++;
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] a = { { 1, 0, 0 }, { -1, 0, 3 } };
		int[][] b = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		int[][] result = multiply(a, b);

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

	}
}
