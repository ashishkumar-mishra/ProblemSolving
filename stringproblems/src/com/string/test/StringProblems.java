package com.string.test;

public class StringProblems {
	/**
	 * method to print all the substring of given string
	 */
	public void printSubStrings(final String inputString) {
		int length = inputString.length();
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j <= length; j++) {
				System.out.println(inputString.substring(i, j));
			}
		}
	}

	/**
	 * method to find out if a given string is palindrome
	 */

	public boolean isPalindrome(final String inputString) {
		int startIndex = 0;
		int endIndex = inputString.length() - 1;
		char[] charArray = inputString.toCharArray();
		while (startIndex <= endIndex) {
			if (charArray[startIndex] != charArray[endIndex]) {
				System.out.println("input string is not a palindrome string");
				return false;
			}
			startIndex++;
			endIndex--;
		}
		System.out.println("Input string is palindrome");
		return true;
	}

	/**
	 * method to compare two strings, this method returns 0 if string is equal to
	 * some string, returns negative if string is smaller than some string, returns
	 * positive number if string is greater than some string, this comparator works
	 * as standard comparator except below case : in case numeric value is
	 * encountered we need to compare with actual value. For exp : in standard
	 * comparison "a11b" comes before "a2b" but we have to reverse this. in this
	 * comparison "a2b" should come before as 11 > 2.
	 * 
	 */
	public int compareStrings(String str1, String str2) {
		char[] charArray1 = str1.toCharArray();
		char[] charArray2 = str2.toCharArray();
		int i = 0;
		int j = 0;

		while (i < charArray1.length && j < charArray2.length) {
			String num1 = "";
			String num2 = "";
			while (charArray1[i] >= 48 && charArray1[i] <= 57) {
				num1 = num1 + charArray1[i];
				i++;
			}
			while (charArray2[j] >= 48 && charArray2[j] <= 57) {
				num2 = num2 + charArray2[j];
				j++;
			}
			if (num1 != "" && num2 != ""
					&& (Integer.valueOf(num1).intValue() - Integer.valueOf(num2).intValue()) != 0) {
				return (Integer.valueOf(num1).intValue() - Integer.valueOf(num2).intValue());
			}
			if (charArray1[i] != charArray1[j]) {
				return (charArray1[i] - charArray1[j]);
			}
			i++;
			j++;
		}
		return (charArray1.length - charArray2.length);
	}

	/**
	 * method to find out whether a string is one edit apart from other string
	 */

	public boolean isOneEditApart(String str1, String str2) {
		boolean isOneEditAart = false;
		int length1 = str1.length();
		int length2 = str2.length();
		int count = 0;
		// if length difference is more than 1, return false
		if (Math.abs(length1 - length2) > 1) {
			return false;
		}
		int i = 0;
		int j = 0;
		while (i < length1 && j < length2) {
			if (count > 1) {
				return false;
			}
			if (str1.charAt(i) != str2.charAt(j)) {
				// case when length difference is one between strings
				count++;
				if (length1 > length2) {
					i++;
				}
				if (length2 > length1) {
					j++;
				}
				if (length1 == length2) {
					i++;
					j++;
				}
			} else {
				i++;
				j++;
			}
		}
		// checking if last character is extra
		if (i < length1 || j < length2) {
			count++;
		}
		if (count == 1) {
			return true;
		}
		return isOneEditAart;
	}

	/**
	 * method to print the permutations of a given string
	 */
	public void permutate(String inputString, int startIndex, int endIndex) {
		if (startIndex == endIndex) {
			System.out.println(inputString);
		} else {
			for (int i = startIndex; i <= endIndex; i++) {
				String str = swap(inputString, startIndex, i);
				permutate(str, startIndex + 1, endIndex);
				System.out.println(inputString);
				System.out.println(str);
			}
		}
	}

	private String swap(String str, int i, int j) {
		char[] charArray = str.toCharArray();
		char tmp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = tmp;
		return String.valueOf(charArray);
	}

	/**
	 * method to find out smallest string need to be appended to make a string
	 * palindrome
	 */
	public String getMinSubStringToMakePalindrome(String inputString) {
		char[] charArray = inputString.toCharArray();
		String subString = "";
		int i = 0;
		while (!isPalindrome(inputString + subString)) {
			subString = charArray[i++] + subString;
		}
		return subString;
	}

}
