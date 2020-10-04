package com.algo.dynamic;

public class LongestValidParantheses {

	public static void main(String[] args) {
		LongestValidParantheses longestValidParantheses = new LongestValidParantheses();
		System.out.println(longestValidParantheses.longestValidParantheses("))"));
	}

	public int longestValidParantheses(final String str) {
		int result = 0;
		int[] maxLength = new int[str.length()];
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == ')' && str.charAt(i - 1) == '(') {
				maxLength[i] = (i >= 2) ? maxLength[i - 2] + 2 : 2;
			} else if (str.charAt(i) == ')' && str.charAt(i - 1) == ')' && (i - maxLength[i - 1]) > 0
					&& str.charAt(i - maxLength[i - 1] - 1) == '(') {
				if (i - maxLength[i - 1] >= 2) {
					maxLength[i] = maxLength[i - 1] + maxLength[i - maxLength[i - 1] - 2] + 2;
				} else {
					maxLength[i] = maxLength[i - 1] + 2;
				}
			}
			result = Math.max(result, maxLength[i]);
		}
		return result;
	}

}
