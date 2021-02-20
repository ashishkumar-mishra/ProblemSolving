package com.algo.dynamic;

public class NumberDecoding {

	public int numDecodings(final String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			return 0;
		}
		return numDecodingHelper(inputString, 0);
	}

	private int numDecodingHelper(String inputString, int startIndex) {
		
		if (startIndex == inputString.length()) {
			return 1;
		}

		if (inputString.charAt(startIndex) == '0') {
			return 0;
		}

		if (startIndex == inputString.length() - 1) {
			return 1;
		}

		int result = numDecodingHelper(inputString, startIndex + 1);

		if (Integer.parseInt(inputString.substring(startIndex, startIndex + 2)) <= 26) {
			result += numDecodingHelper(inputString, startIndex + 2);
		}
		// System.out.println("result returned" + result);
		return result;
	}

	public int numDecodingsDynamic(final String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			return 0;
		}
		int[] results = new int[inputString.length() + 1];
		// base cases
		results[0] = 1;
		results[1] = inputString.charAt(0) == '0' ? 0 : 1;

		for (int i = 2; i < results.length; i++) {
			if (inputString.charAt(i - 1) != '0') {
				results[i] += results[i - 1];
			}
			// checking two digit
			int numberWithTwoDigit = Integer.valueOf(inputString.substring(i - 2, i));

			if (numberWithTwoDigit >= 10 && numberWithTwoDigit <= 26) {
				results[i] += results[i - 2];
			}
		}

		return results[results.length - 1];
	}

	public static void main(String[] agrs) {
		NumberDecoding numberOfDecoding = new NumberDecoding();
		System.out.println(numberOfDecoding.numDecodings("26"));
		System.out.println(numberOfDecoding.numDecodingsDynamic("2222"));
	}
}
