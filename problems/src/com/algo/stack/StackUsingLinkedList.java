package com.algo.stack;

public class StackUsingLinkedList {

	Node root;

	class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}

	public boolean push(int data) {
		if (root == null) {
			root = new Node(data);
		} else {
			Node node = new Node(data);
			node.next = root;
			root = node;
		}
		return true;
	}

	public int pop() {
		if (root == null) {
			System.out.println("stack is underflow");
			return Integer.MIN_VALUE;
		}
		Node node = root;
		root = root.next;
		return node.data;
	}

	public int peek() {
		if (root == null) {
			System.out.println("stack is underflow");
			return Integer.MIN_VALUE;
		}
		return root.data;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		StackUsingLinkedList stack = new StackUsingLinkedList();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("peek= "+ stack.peek());
		System.out.println(stack.pop());
		System.out.println("peek= "+ stack.peek());
		System.out.println(stack.pop());
		System.out.println("peek= "+ stack.peek());
		System.out.println(stack.isEmpty());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty());
	}
}
