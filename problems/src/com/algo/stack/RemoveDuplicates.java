package com.algo.stack;

import java.util.Stack;

public class RemoveDuplicates {

	/**
	 * Given a string s, a k duplicate removal consists of choosing k adjacent and
	 * equal letters from s and removing them causing the left and the right side of
	 * the deleted substring to concatenate together.We repeatedly make k duplicate
	 * removals on s until we no longer can.Return the final string after all such
	 * duplicate removals have been made.It is guaranteed that the answer is unique.
	 */
	public String removeDuplicates(String str, int k) {
		StringBuilder s = new StringBuilder(str);
		int len = -1;
		while (len != s.length()) {
			len = s.length();
			int counter = 1;
			for (int i = 1; i < s.toString().length(); i++) {
				counter = s.charAt(i) == s.charAt(i - 1) ? ++counter : 1;
				if (counter == k) {
					s.delete(i - k + 1, i + 1);
					counter = 1;
					break;
				}
			}
		}
		return s.toString();
	}

	public String removeDuplicateUsingStack(String str, int k) {
		StringBuilder s = new StringBuilder(str);
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		while (i < s.length()) {
			if (i != 0 && s.charAt(i) == s.charAt(i - 1)) {
				int j = stack.pop() + 1;
				if (j == k) {
					s.delete(i - k + 1, i + 1);
					i = i - k + 1;
				} else {
					stack.push(j);
					i++;
				}
			} else {
				stack.push(1);
				i++;
			}
		}
		return s.toString();
	}

	public static void main(String[] args) {
		RemoveDuplicates removeDuplicates = new RemoveDuplicates();
		System.out.println(removeDuplicates.removeDuplicates("pbbcggttciiippooaais", 2));
		System.out.println(removeDuplicates.removeDuplicateUsingStack("pbbcggttciiippooaais", 2));
	}
}
