package com.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {

	Map<Character, Character> parenthesisMap = null;

	ValidParenthesis() {
		parenthesisMap = new HashMap<>();
		parenthesisMap.put(')', '(');
		parenthesisMap.put('}', '{');
		parenthesisMap.put(']', '[');
	}

	public boolean isValid(String str) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (parenthesisMap.containsKey(str.charAt(i))) {
				char c = stack.isEmpty() ? '#' : stack.pop();
				if (parenthesisMap.get(str.charAt(i)) != c) {
					return false;
				}
			} else {
				stack.push(str.charAt(i));
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		ValidParenthesis validParenthesis = new ValidParenthesis();
		System.out.println(validParenthesis.isValid("(]"));
	}
}
