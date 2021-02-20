package com.algo.dynamic;

import java.util.ArrayList;
import java.util.List;

public class InterleavingStringProblems {

	public List<String> interleavingStrings(String str1, String str2) {
		if ((str1 == null || str1.isEmpty()) && (str2 == null || str2.isEmpty())) {
			return null;
		}
		List<String> result = new ArrayList<>();
		interleavingStringsHelper(str1, str2, "", result);
		return result;
	}

	private void interleavingStringsHelper(String str1, String str2, String interleavingString, List<String> result) {
		//System.out.println("("+str1 + "," + str2 + "," + interleavingString + ")");
		if (str1 == null) {
			result.add(str2);
			return;
		}
		if (str2 == null) {
			result.add(str1);
			return;
		}
		if (str1.isEmpty() && str2.isEmpty()) {
			result.add(interleavingString);
			return;
		}
		if (!str1.isEmpty()) {
			interleavingStringsHelper(str1.substring(1), str2, interleavingString + str1.charAt(0), result);
		}

		if (!str2.isEmpty()) {
			interleavingStringsHelper(str1, str2.substring(1), interleavingString + str2.charAt(0), result);
		}
	}

	public boolean isInterleavingWithoutrepeatingcharacters(String str1, String str2, String str3) {
		if (str3 == null || str3.isEmpty()) {
			return true;
		}
		// base case
		if (str1.isEmpty() && str2.isEmpty() && str3.isEmpty()) {
			return true;
		}

		if (str1 != null && !str1.isEmpty() && str1.charAt(0) == str3.charAt(0)) {
			return isInterleavingWithoutrepeatingcharacters(str1.substring(1), str2, str3.substring(1));
		}

		if (str2 != null && !str2.isEmpty() && str2.charAt(0) == str3.charAt(0)) {
			return isInterleavingWithoutrepeatingcharacters(str1, str2.substring(1), str3.substring(1));
		}
		return false;
	}

	public boolean isInterleavingWithoutrepeatingcharactersIterative(String str1, String str2, String str3) {
		if (str3 == null || str3.isEmpty()) {
			return true;
		}
		// base case
		if (str1.isEmpty() && str2.isEmpty() && str3.isEmpty()) {
			return true;
		}

		if (str1.length() + str2.length() != str3.length()) {
			return false;
		}

		int i = 0;
		int j = 0;
		int k = 0;

		while (str3.length() > 0) {
			if (i < str1.length() && str1.charAt(i) == str3.charAt(k)) {
				i++;
				k++;
			} else if (j < str2.length() && str2.charAt(j) == str3.charAt(k)) {
				i++;
				k++;
			} else {
				return false;
			}
		}
		if (i == str1.length() - 1 && j == str2.length() - 1) {
			return true;
		}
		return false;
	}

	// this will work in case there are repeating characters in strings
	public boolean isInterleavingDynamic(String str1, String str2, String str3) {
		if (str1.length() + str2.length() != str3.length()) {
			return false;
		}
		boolean[][] resultArray = new boolean[str1.length() + 1][str2.length() + 1];
		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				// base case
				int k = i + j - 1;
				if (i == 0 && j == 0) {
					resultArray[i][j] = true;
				} else if (i == 0) {
					if (str2.charAt(j - 1) == str3.charAt(k)) {
						resultArray[i][j] = resultArray[i][j - 1];
					}
				} else if (j == 0) {
					if (str1.charAt(i - 1) == str3.charAt(k)) {
						resultArray[i][j] = resultArray[i - 1][j];
					}
				} else if(str1.charAt(i - 1) == str3.charAt(k) && str2.charAt(j - 1) != str3.charAt(k)) {
					resultArray[i][j] = resultArray[i - 1][j];
				} else if(str2.charAt(j - 1) == str3.charAt(k) && str1.charAt(i - 1) != str3.charAt(k)) {
					resultArray[i][j] = resultArray[i][j - 1];
				} else if(str1.charAt(i - 1) == str3.charAt(k) && str2.charAt(j - 1) == str3.charAt(k)){
					resultArray[i][j] = resultArray[i][j - 1] | resultArray[i - 1][j];
				}
			}
		}
		return resultArray[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		InterleavingStringProblems interleavingStringProblems = new InterleavingStringProblems();
		List<String> result = interleavingStringProblems.interleavingStrings("AB", "XY");
		System.out.println(result);
		System.out.println(interleavingStringProblems.isInterleavingWithoutrepeatingcharacters("AB", "XY", "XAYB"));
		System.out.println(interleavingStringProblems.isInterleavingWithoutrepeatingcharacters("AB", "XY", "XAYB"));
		System.out.println(interleavingStringProblems.isInterleavingDynamic("ab", "cd", "abcd"));
	}
}
