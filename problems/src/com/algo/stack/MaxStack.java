package com.algo.stack;

import java.util.Stack;

public class MaxStack {
	Stack<Node> stack = new Stack<>();

	class Node {
		int currentValue;
		int maxValue;

		Node(int currentValue, int maxValue) {
			this.currentValue = currentValue;
			this.maxValue = maxValue;
		}
	}

	public void push(int x) {
		Node node = null;
		if (!stack.isEmpty()) {
			Node currentNode = stack.peek();
			int max = Math.max(x, Math.max(currentNode.currentValue, currentNode.maxValue));
			node = new Node(x, max);
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

	public int popMax() {
		Stack<Node> tmpStack = new Stack<>();
		int max = stack.peek().maxValue;
		int maxValue = Integer.MIN_VALUE;
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (node.currentValue == max) {
				maxValue = node.currentValue;
				break;
			} else {
				tmpStack.push(node);
			}
		}
		while (!tmpStack.isEmpty()) {
			push(tmpStack.pop().currentValue);
		}
		return maxValue;
	}

	public int peekMax() {
		if (stack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stack.peek().maxValue;
	}

	public int top() {
		if (stack.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stack.peek().currentValue;
	}
	
	public static void main(String[] args) {
		MaxStack maxStack = new MaxStack();
		maxStack.push(5);
		maxStack.push(1);
		System.out.println(maxStack.popMax());
		System.out.println(maxStack.peekMax());
	}
}
