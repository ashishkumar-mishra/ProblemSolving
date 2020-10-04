package com.algo.leet;

import java.util.ArrayList;
import java.util.List;

public class CeaserCipher {

	public String ceaserCipher(String text, int shift) {
		char[] result = new char[text.length()];

		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				int modifiedChar = (text.charAt(i) + shift - 65) % 26 + 'A';
				result[i] = (char) modifiedChar;
			} else {
				int modifiedChar = (text.charAt(i) + shift - 97) % 26 + 'a';
				result[i] = (char) modifiedChar;
			}
		}
		return String.valueOf(result);
	}

	// brute force solution
	public List<List<String>> getCeaserCipherGroups(String[] input) {

		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			List<String> list = new ArrayList<>();
			if (input[i] != "") {
				list.add(input[i]);
			}
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] == "") {
					continue;
				}
				if (isCeaserCipher(input[i], input[j])) {
					list.add(input[j]);
					input[j] = "";
				}
			}
			if (!list.isEmpty()) {
				result.add(new ArrayList<>(list));
			}
		}
		return result;
	}

	private boolean isCeaserCipher(String str1, String str2) {
		for (int i = 1; i < 26; i++) {
			String s1 = getCeaser(str1, i);
			if (s1.equals(str2)) {
				return true;
			}
		}
		return false;
	}

	private String getCeaser(String str, int shift) {
		char[] tmp = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				int m1 = (str.charAt(i) + shift - 'A') % 26 + 'A';
				tmp[i] = (char) m1;
			} else {
				int m1 = (str.charAt(i) + shift - 'a') % 26 + 'a';
				tmp[i] = (char) m1;
			}
		}
		return String.valueOf(tmp);
	}

	public static void main(String[] args) {
		CeaserCipher cipher = new CeaserCipher();
		// System.out.println(cipher.ceaserCipher("abc", 1));
		String[] arr = { "abc", "bcd", "acd", "dfg" };
		System.out.println(cipher.getCeaserCipherGroups(arr));
	}
}
