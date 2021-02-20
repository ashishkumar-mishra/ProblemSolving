package com.algo.dynamic;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		partitionHelper(new ArrayList<>(), s, result);
		return result;

	}

	private void partitionHelper(List<String> list, String str, List<List<String>> result) {
		if (str.isEmpty()) {
			result.add(new ArrayList<>(list));
			return;
		}
		for (int i = 1; i <= str.length(); i++) {
			if (isPalindrome(str.substring(0, i))) {
				//List<String> l = new ArrayList<>(list);
				list.add(str.substring(0, i));
				partitionHelper(list, str.substring(i), result);
				list.remove(list.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if(str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		PalindromePartitioning p = new PalindromePartitioning();
		System.out.println(p.partition("aab"));
	}
}
