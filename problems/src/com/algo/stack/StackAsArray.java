package com.algo.stack;

public class StackAsArray {

	int top;
	static final int MAX_SIZE = 3;
	int[] elements;

	StackAsArray() {
		elements = new int[MAX_SIZE];
		top = -1;
	}

	// push, pop, top, isEmpty

	public boolean push(int element) {
		if (top >= MAX_SIZE - 1) {
			System.out.println("stack is overflow");
			return false;
		}
		elements[++top] = element;
		return true;
	}

	public int pop() {
		if (top < 0) {
			System.out.println("stack is underflow");
			return Integer.MIN_VALUE;
		}
		return elements[top--];
	}

	public boolean isEmpty() {
		if (top < 0) {
			return true;
		}
		return false;
	}

	public int peek() {
		if (top < 0) {
			System.out.println("stack is underflow");
			return Integer.MIN_VALUE;
		}
		return elements[top];
	}
	
	public static void main(String[] args) {
		StackAsArray stackAsArray = new StackAsArray();
		stackAsArray.push(1);
		stackAsArray.push(2);
		stackAsArray.push(3);
		stackAsArray.push(4);
	}
}
