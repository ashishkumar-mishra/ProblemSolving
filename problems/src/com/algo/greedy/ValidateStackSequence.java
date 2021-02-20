package com.algo.greedy;

import java.util.Stack;

public class ValidateStackSequence {

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Stack<Integer> stack = new Stack<>();
		int start = 0;
		for (int element : pushed) {
			stack.push(element);
			while (!stack.isEmpty() && stack.peek() == popped[start]) {
				stack.pop();
				start++;
			}
		}
		return (start == popped.length);
	}

	public static void main(String[] args) {
		ValidateStackSequence validate = new ValidateStackSequence();
		int[] pushed = {1,2,3,4,5};
		int[] popped = {4,3,5,1,2};
		System.out.println(validate.validateStackSequences(pushed, popped));
	}
}
