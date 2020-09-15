package com.algo.stack;

import java.util.Stack;

public class MinStack {

	Stack<Node> stack = new Stack<>();

	class Node {
		int currentValue;
		int minValue;

		Node(int currentValue, int minValue) {
			this.currentValue = currentValue;
			this.minValue = minValue;
		}
	}

	public void push(int x) {
		Node node = null;
		if (!stack.isEmpty()) {
			Node currentNode = stack.peek();
			int min = Math.min(x, Math.min(currentNode.currentValue, currentNode.minValue));
			node = new Node(x, min);
		} else {
			node = new Node(x, x);
		}
		stack.push(node);
	}

	public int pop() {
		if (stack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stack.pop().currentValue;
	}

	public int getMin() {
		if (stack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stack.peek().minValue;
	}

	public int top() {
		if (stack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stack.peek().currentValue;
	}

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.pop());
		System.out.println(minStack.top());
	}
}
