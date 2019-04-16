package com.string.test;

public class KnightsTourProblem {
	// define move
	private static int MOVE = 8;

	/*
	 * A utility function to check if i,j are valid indexes for N*N chess board
	 */
	static boolean isSafe(int x, int y, int sol[][]) {
		return (x >= 0 && x < MOVE && y >= 0 && y < MOVE && sol[x][y] == -1);
	}

	/*
	 * A utility function to print the solution matrix sol[MOVE][MOVE]
	 */

	static void printSolution(int sol[][]) {
		for (int x = 0; x < MOVE; x++) {
			for (int y = 0; y < MOVE; y++) {
				System.out.print(sol[x][y] + " ");
			}
			System.out.println();
		}
	}

	/*
	 * This function solve the KnightTour problem using backtracking.
	 */

	static boolean solveKT() {
		int sol[][] = new int[MOVE][MOVE];
		for (int x = 0; x < MOVE; x++) {
			for (int y = 0; y < MOVE; y++) {
				sol[x][y] = -1;
			}
		}
		int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
		sol[0][0] = 0;

		if (!solveKnightTourUtil(0, 0, 1, sol, xMove, yMove)) {
			System.out.println("Solution does not exist");
			return false;
		} else {
			printSolution(sol);
		}
		return true;
	}
	/*
	 * A recursive function utility function to solve KnightTour problem.
	 */

	static boolean solveKnightTourUtil(int x, int y, int movei, int sol[][], int xMove[], int yMove[]) {
		int k, next_x, next_y;
		if (movei == MOVE * MOVE) {
			return true;
		}

		for (k = 0; k < MOVE; k++) {
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if (isSafe(next_x, next_y, sol)) {
				sol[next_x][next_y] = movei;
				if (solveKnightTourUtil(next_x, next_y, movei + 1, sol, xMove, yMove)) {
					return true;
				} else {
					sol[next_x][next_y] = -1;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		solveKT();
	}
}
