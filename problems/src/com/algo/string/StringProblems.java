package com.algo.string;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		while (startIndex <= endIndex) {
			if (inputString.charAt(startIndex) != inputString.charAt(endIndex)) {
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
	public void permutate(String str, int startIndex, int endIndex) {
		// System.out.println("(" + str + "," + startIndex + "," + endIndex + ")");
		if (startIndex == endIndex) {
			System.out.println(str);
		} else {
			for (int i = startIndex; i <= endIndex; i++) {
				str = swap(str, startIndex, i);
				permutate(str, startIndex + 1, endIndex);
				// commenting below line will short the output in lexicographical order
				str = swap(str, startIndex, i);
			}
		}
	}

	public void permutate1(String str, String result) {
		// base case
		if (str.length() == 0) {
			System.out.print(result + " ");
			return;
		}

		for (int i = 0; i < str.length(); i++) {

			// ith character of str
			char ch = str.charAt(i);

			// Rest of the string after excluding
			// the ith character
			String ros = str.substring(0, i) + str.substring(i + 1);

			// Recurvise call
			permutate1(ros, result + ch);
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

	/**
	 * This method converts string to integer value, there are below cases - if
	 * string is null or empty (string = ""): return 0 ignore the leading or
	 * trailing whitespace (string = " 123 ") : return 123 case when there is + or -
	 * sign in beginning (string = "-123") : return -123 return 0 in case there is
	 * not leading numeric value (string = "abc" ) return 0; if value is more than
	 * Integer.MAX_VALUE return Integer.MAX_VALUE. if value is less than
	 * Integer.MIN_VALUE return Integer.MIN_VALUE.
	 * 
	 * @param string
	 * @return
	 */
	public int stringToInteger(String string) {
		boolean isNegative = false;
		int result = 0;
		int startIndex = 0;
		if (string == null || string.isEmpty()) {
			return result;
		}
		while (string.charAt(startIndex) == ' ') {
			startIndex++;
		}
		if (string.charAt(startIndex) == '-' || string.charAt(startIndex) == '+') {
			if (string.charAt(startIndex) == '-') {
				isNegative = true;
			}
			startIndex++;
		}
		while (startIndex < string.length() && (string.charAt(startIndex) >= '0' || string.charAt(startIndex) <= '9')) {
			if (result > Integer.MAX_VALUE / 10
					|| ((result == Integer.MAX_VALUE / 10) && string.charAt(startIndex) - '0' > 7)) {
				if (isNegative) {
					return Integer.MIN_VALUE;
				}
				return Integer.MAX_VALUE;
			}
			result = result * 10 + (string.charAt(startIndex++) - '0');
		}
		if (isNegative) {
			result = -result;
		}
		return result;
	}

	public int dynamicEditDistance(char[] str1, char[] str2) {
		int temp[][] = new int[str1.length + 1][str2.length + 1];

		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i] = i;
		}

		for (int i = 0; i < temp.length; i++) {
			temp[i][0] = i;
		}

		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					temp[i][j] = temp[i - 1][j - 1];
				} else {
					temp[i][j] = 1 + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
				}
			}
		}
		return temp[str1.length][str2.length];

	}

	private int min(int a, int b, int c) {
		int l = Math.min(a, b);
		return Math.min(l, c);
	}

	public String longestSubStringWithoutRepeatedChar(String inputString) {
		if (inputString.isEmpty()) {
			return "";
		}
		int i = 0;
		int j = 0;
		int maxLength = 0;
		int maxStart = 0;
		Set<Character> chars = new HashSet<>();
		while (i < inputString.length() && j < inputString.length()) {
			if (!chars.contains(inputString.charAt(j))) {
				chars.add(inputString.charAt(j));
				j++;
				if (maxLength < (j - i)) {
					maxLength = (j - i);
					maxStart = i;
				}
			} else {
				chars.remove(inputString.charAt(i));
				i++;
			}
			System.out.println("i = " + i + " j= " + j + " maxLength= " + maxLength);
			System.out.println("set=" + chars);
		}

		return inputString.substring(maxStart, maxLength + maxStart);
	}

	public void getAlltheSubSequences(final String inputString, String str, final List<String> result) {
		if (inputString.length() == 0) {
			result.add(str);
			return;
		}
		getAlltheSubSequences(inputString.substring(1), str + inputString.charAt(0), result);
		getAlltheSubSequences(inputString.substring(1), str, result);
	}

	public String addStrings(String num1, String num2) {
		int carry = 0;
		int i = num1.length() - 1;
		int j = num2.length() - 1;
		String result = "";
		while (i >= 0 && j >= 0) {
			int sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry;
			if (sum > 9) {
				result = sum % 10 + result;
				carry = sum / 10;
			} else {
				result = sum + result;
				carry = 0;
			}
			i--;
			j--;
		}
		// if there is anything pending in first number
		while (i >= 0) {
			int sum = num1.charAt(i) - '0' + carry;
			if (sum > 9) {
				result = sum % 10 + result;
				carry = sum / 10;
			} else {
				result = sum + result;
				carry = 0;
			}
			i--;
		}

		while (j >= 0) {
			int sum = num2.charAt(j) - '0' + carry;
			if (sum > 9) {
				result = sum % 10 + result;
				carry = sum / 10;
			} else {
				result = sum + result;
				carry = 0;
			}
			j--;
		}
		if (carry > 0) {
			result = carry + result;
		}

		return result;
	}

	public String addBinary(String str1, String str2) {
		if (str2.length() > str1.length()) {
			return addBinary(str2, str1);
		}

		int i = str1.length() - 1;
		int j = str2.length() - 1;
		String result = "";
		int carry = 0;

		while (i >= 0 && j >= 0) {
			int sum = str1.charAt(i) - '0' + str2.charAt(j) - '0' + carry;
			result = sum % 2 + result;
			carry = sum / 2;
			i--;
			j--;
		}

		while (i >= 0) {
			int sum = str1.charAt(i) - '0' + carry;
			result = sum % 2 + result;
			carry = sum / 2;
			i--;
		}
		if (carry > 0) {
			result = carry + result;
		}
		return result;
	}

	public static void main(String args[]) {
		StringProblems problems = new StringProblems();
		problems.isPalindrome("abba");
		// System.out.print("value = " + problems.stringToInteger("-2147483649"));

		//String str1 = "azced";
		//String str2 = "abcd";
		//System.out.println(problems.dynamicEditDistance(str1.toCharArray(), str2.toCharArray()));
		//problems.permutate("abcd", 0, 3);
		System.out.println(problems.addStrings("9", "99"));
		System.out.println(problems.addBinary("111", "111"));
		
	}
}
