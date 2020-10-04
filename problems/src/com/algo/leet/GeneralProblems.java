package com.algo.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralProblems {
	Map<String, String> phone = new HashMap<String, String>();

	GeneralProblems() {
		phone.put("2", "abc");
		phone.put("3", "def");
		phone.put("4", "ghi");
		phone.put("5", "jkl");
		phone.put("6", "mno");
		phone.put("7", "pqrs");
		phone.put("8", "tuv");
		phone.put("9", "wxyz");
	}

	/**
	 * method to calculate the power
	 * 
	 * @param x base
	 * @param n power
	 * @return x^n
	 */
	public double myPow(double x, int n) {
		if (n < 0) {
			x = 1 / x;
			n = -n;
		}
		return power(x, n);
	}

	private double power(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		double half = power(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}

	public void traverseMatrix(int martrix[][], int m, int n, int i, int j, int[] path, int currentIndex) {
		path[currentIndex] = martrix[i][j];

		// we reached last row
		if (i == m - 1) {
			for (int k = j + 1; k < n; k++) {
				path[currentIndex + k - j] = martrix[i][k];
			}

			for (int l = 0; l < currentIndex + n - j; l++) {
				System.out.print(path[l] + " ");
			}
			System.out.println();
			return;
		}
		// when we reached last column
		if (j == n - 1) {
			for (int k = i + 1; k < m; k++) {
				path[currentIndex + k - i] = martrix[k][j];
			}

			for (int l = 0; l < currentIndex + m - i; l++) {
				System.out.print(path[l] + " ");
			}
			System.out.println();
			return;
		}
		// Print all the paths that are possible after moving down
		traverseMatrix(martrix, m, n, i + 1, j, path, currentIndex + 1);

		// Print all the paths that are possible after moving right
		traverseMatrix(martrix, m, n, i, j + 1, path, currentIndex + 1);
	}

	/**
	 * Given a string containing digits from 2-9 inclusive, return all possible
	 * letter combinations that the number could represent. A mapping of digit to
	 * letters (just like on the telephone buttons) is given below. Note that 1 does
	 * not map to any letters.
	 */

	public List<String> letterCombination(String digits) {
		List<String> output = new ArrayList<>();
		if (!digits.isEmpty()) {
			backTrack("", digits, output);
		}
		return output;
	}

	private void backTrack(String combination, String nextDigits, List<String> output) {
		if (nextDigits.length() == 0) {
			output.add(combination);
		} else {
			String currentDigit = nextDigits.substring(0, 1);
			String letters = phone.get(currentDigit);
			for (int i = 0; i < letters.length(); i++) {
				backTrack(combination + letters.substring(i, i + 1), nextDigits.substring(1), output);
			}
		}
	}

	public String longestPalindrome(String str) {
		if (str.isEmpty()) {
			return "";
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < str.length(); i++) {
			int len1 = expandAroundCenter(str, i, i);
			int len2 = expandAroundCenter(str, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return str.substring(start, end + 1);
	}

	private int expandAroundCenter(String str, int left, int right) {
		int l = left;
		int r = right;
		while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
			l--;
			r++;
		}
		return r - (l + 1);
	}

	public static void main(String args[]) {
		GeneralProblems problems = new GeneralProblems();
		// System.out.println("power value is == " + problems.myPow(3, 2));

		int m = 3;
		int n = 3;
		int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int maxLengthOfPath = m + n - 1;
		problems.traverseMatrix(mat, m, n, 0, 0, new int[maxLengthOfPath], 0);

		String digits = "234";
		System.out.println(problems.letterCombination(digits));
		System.out.println(problems.longestPalindrome("abba"));
	}
}
