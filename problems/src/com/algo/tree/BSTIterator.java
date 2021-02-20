package com.algo.tree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
	List<Integer> sortedArray;
	int index = 0;

	public BSTIterator(TreeNode root) {
		sortedArray = new ArrayList<>();
		inorder(root);
	}

	private void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			sortedArray.add(root.key);
			inorder(root.right);
		}

	}

	/** @return the next smallest number */
	public int next() {
		return sortedArray.get(index++);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		if (index == sortedArray.size()) {
			return false;
		}
		return true;
	}
}
