package com.algo.email;

public class CharacterFlip {

	/**
	 * You are given a string consisting of the letters x and y, such as xyxxxyxyy.
	 * In addition, you have an operation called flip, which changes a single x to y
	 * or vice versa. Determine how many times you would need to apply this
	 * operation to ensure that all x's come before all y's. In the preceding
	 * example, it suffices to flip the second and sixth characters, so you should
	 * return 2.
	 */

	public static int minFlips(String s) {
		int n = s.length();
		int[][] dp = new int[n][2];
		dp[0][0] = (s.charAt(0) == 'x' ? 0 : 1);
		dp[0][1] = (s.charAt(0) == 'y' ? 0 : 1);

		for (int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + (s.charAt(i) == 'x' ? 0 : 1);
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (s.charAt(i) == 'x' ? 1 : 0);
		}
		return Math.min(dp[n - 1][0], dp[n - 1][1]);
	}

	public static int minFlipsAlternative(String s) {
		int n = s.length();
		int[] leftY = new int[n];
		int rightX[] = new int[n];
		leftY[0] = 0;
		rightX[n - 1] = 0;
		int result = n;

		for (int i = 1; i < n; i++) {
			leftY[i] = leftY[i - 1] + (s.charAt(i - 1) == 'y' ? 1 : 0);
		}

		for (int j = n - 2; j >= 0; j--) {
			rightX[j] = rightX[j + 1] + (s.charAt(j + 1) == 'x' ? 1 : 0);
		}

		for (int i = 0; i < n; i++) {
			result = Math.min(result, leftY[i] + rightX[i]);
		}

		return result;
	}

	/**
	 * Given a string consisting only of 1’s and 0’s. In one flip we can change any
	 * continuous sequence of this string. Find this minimum number of flips so the
	 * string consist of same characters only.
	 */
	public static int minFlipsTomakesamechars(String str) {
		int result = 0;
		
		char lastchar = ' ';
		
		for(int i = 0 ; i < str.length(); i++) {
			if(lastchar != str.charAt(i)) {
				result++;
			}
			lastchar = str.charAt(i);
		}
		return result/2;
	}
	
	public static int minFlipToMakeBinaryStringAlternative(String str) {
		return Math.min(minFlipToMakeBinaryStringAlternativeHelper(str,'0'), minFlipToMakeBinaryStringAlternativeHelper(str,'1'));
	}
	
	private static int minFlipToMakeBinaryStringAlternativeHelper(String str, char expectedChar) {
		 int count = 0;		 
		 for(int i = 0; i < str.length(); i++) {
			 if(str.charAt(i) != expectedChar) {
				 count++;
			 }
			 expectedChar = expectedChar == '0' ? '1' : '0';
		 }
		 return count;
	}

	public static void main(String[] args) {
		// String s1 = "yyx";
		// String s2 = "xyxxxyxyyy";
		// String s3 = "xyxxxyxyyx";
		// String s4 = "xyxxxyxyyxxx";
		String s5 = "yyxxx";

		System.out.println(minFlips(s5));
		System.out.println(minFlipsAlternative(s5));
		System.out.println(minFlipsTomakesamechars("010"));
		System.out.println(minFlipToMakeBinaryStringAlternative("001"));
	}
}
