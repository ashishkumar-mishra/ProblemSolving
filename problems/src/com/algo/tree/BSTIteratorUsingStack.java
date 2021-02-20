package com.algo.tree;

import java.util.Stack;

public class BSTIteratorUsingStack {

	Stack<TreeNode> stack;

	public BSTIteratorUsingStack(TreeNode root) {
		stack = new Stack<>();
		leftmostInorder(root);
	}

	private void leftmostInorder(TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public int next() {
		TreeNode node = stack.pop();
		if (node != null) {
			leftmostInorder(node.right);
		}
		return node.key;
	}

	public boolean hasNext() {
		return stack.size() > 0;
	}
}
