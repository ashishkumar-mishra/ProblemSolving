package com.algo.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuSolver {

	List<Map<Integer, Integer>> rows = null;
	List<Map<Integer, Integer>> cols = null;
	List<Map<Integer, Integer>> boxes = null;
	boolean solved = false;

	SudokuSolver() {
		rows = new ArrayList<>();
		cols = new ArrayList<>();
		boxes = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			rows.add(new HashMap<>());
			cols.add(new HashMap<>());
			boxes.add(new HashMap<>());
		}
	}

	public void solveSudoku(char[][] board) {
		createValidationList(board);
		sudokuHelper(board, 0, 0);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void sudokuHelper(char[][] board, int row, int col) {
		if (board[row][col] == '.') {
			for (int num = 1; num <= 9; num++) {
				if (canPlace(num, row, col)) {
					placeNum(board, num, row, col);
					placeNextNumber(board, row, col);
					if (!solved) {
						removeNum(board, num, row, col);
					}
				}
			}
		} else {
			placeNextNumber(board, row, col);
		}
	}

	private void placeNextNumber(char[][] board, int row, int col) {
		if (row == board.length - 1 && col == board[0].length - 1) {
			solved = true;
		} else {
			if (col == board[0].length - 1) {
				sudokuHelper(board, row + 1, 0);
			} else {
				sudokuHelper(board, row, col + 1);
			}
		}
	}

	private void placeNum(char[][] board, int num, int row, int col) {
		int boxIndex = (row / 3) * 3 + col / 3;
		board[row][col] = (char) (num + '0');
		rows.get(row).put(num,1);
		cols.get(col).put(num,1);
		boxes.get(boxIndex).put(num,1);
	}

	private void removeNum(char[][] board, int num, int row, int col) {
		int boxIndex = (row / 3) * 3 + col / 3;
		board[row][col] = '.';
		rows.get(row).remove(num);
		cols.get(col).remove(num);
		boxes.get(boxIndex).remove(num);
	}

	private boolean canPlace(int num, int row, int col) {
		int boxIndex = (row / 3) * 3 + col / 3;
		if (!rows.get(row).containsKey(num) && !cols.get(col).containsKey(num)
				&& !boxes.get(boxIndex).containsKey(num)) {
			return true;
		}
		return false;
	}

	private void createValidationList(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					int boxIndex = (i / 3) * 3 + j / 3;
					int num = board[i][j] - '0';
					rows.get(i).put(num, rows.get(i).getOrDefault(num, 0) + 1);
					cols.get(j).put(num, cols.get(j).getOrDefault(num, 0) + 1);
					boxes.get(boxIndex).put(num, boxes.get(boxIndex).getOrDefault(num, 0) + 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		SudokuSolver solver = new SudokuSolver();
		solver.solveSudoku(board);
	}
}
