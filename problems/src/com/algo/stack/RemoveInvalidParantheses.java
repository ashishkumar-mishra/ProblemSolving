package com.algo.stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParantheses {

	/**
	 * Given a string s of '(' , ')' and lower case English characters. Your task is
	 * to remove the minimum number of parentheses ( '(' or ')', in any positions )
	 * so that the resulting parentheses string is valid and return any valid
	 * string.
	 */

	public String minRemoveToMakeValid(String s) {
		int open = 0;
		int close = 0;
		Stack<Character> stack = new Stack<>();

		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				open += 1;
				stack.push(ch);
			} else if (ch == ')') {
				if (open > close) {
					close += 1;
					stack.push(ch);
				}
			} else {
				stack.push(ch);
			}
		}
		open = 0;
		close = 0;
		String result = "";
		while (!stack.isEmpty()) {
			char ch = stack.pop();
			if (ch == ')') {
				result = ch + result;
				close += 1;
			} else if (ch == '(') {
				if (open < close) {
					result = ch + result;
					open += 1;
				}
			} else {
				result = ch + result;
			}
		}
		return result;
	}

	public String minRemoveToMakeValidOther(String s) {
		Stack<Integer> stack = new Stack<>();
		char[] array = s.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '(') {
				stack.push(i);
			} else if (array[i] == ')') {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					array[i] = '#';
				}
			}
		}
		while (!stack.isEmpty()) {
			int index = stack.pop();
			array[index] = '#';
		}
		StringBuilder result = new StringBuilder();
		for (char ch : array) {
			if (ch == '#') {
				continue;
			}
			result.append(ch);
		}
		return result.toString();
	}

	public List<String> minRemoveToMakeValidAll(String s) {
		Queue<String> queue = new LinkedList<String>();
		List<String> result = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		boolean level = false;
		queue.add(s);
		visited.add(s);
		while (!queue.isEmpty()) {
			String top = queue.remove();
			if (isValidString(top)) {
				result.add(top);
				level = true;
			}
			if (level) {
				continue;
			}
			String str = "";
			for (int i = 0; i < top.length(); i++) {
				if (top.charAt(i) == '(' || top.charAt(i) == ')') {
					str = top.substring(0, i) + top.substring(i + 1);
					if (!visited.contains(str)) {
						queue.add(str);
						visited.add(str);
					}
				}
			}
		}
		return result;
	}

	private boolean isValidString(String str) {
		int open = 0;
		int close = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				open++;
			} else if (str.charAt(i) == ')') {
				close++;
				if (close > open) {
					return false;
				}
			}
		}
		return (open == close);
	}

	public static void main(String[] args) {
		RemoveInvalidParantheses r = new RemoveInvalidParantheses();
		// System.out.println(r.minRemoveToMakeValid("()))"));
		// System.out.println(r.minRemoveToMakeValidOther("()))"));
		System.out.println(r.minRemoveToMakeValidAll("()())()"));
	}
}
