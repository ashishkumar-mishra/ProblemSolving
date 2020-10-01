package com.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class ValidParentheis {

	private Map<Character, Character> mappings;

	public ValidParentheis() {
		mappings = new HashMap<>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');
	}

	public boolean isValid(String str) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			// if current element is closing bracket
			if (mappings.containsKey(c)) {
				// get the top element
				char topChar = stack.isEmpty() ? '#' : stack.pop();
				if (topChar != mappings.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	public static void main(String args[]) {
		ValidParentheis sol = new ValidParentheis();
		System.out.println(sol.isValid("{[}]"));
	}
}