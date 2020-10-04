package com.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	// Zigzag level traversal of binary tree

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Stack<TreeNode> currentNodes = new Stack<>();
		Stack<TreeNode> nextNodes = new Stack<>();
		boolean isLefttoRight = true;
		currentNodes.push(root);
		result.add(new ArrayList<>());
		int currentIndex = 0;
		while (!currentNodes.isEmpty()) {
			TreeNode node = currentNodes.pop();
			result.get(currentIndex).add(node.key);
			if (isLefttoRight) {
				if (node.left != null) {
					nextNodes.push(node.left);
				}
				if (node.right != null) {
					nextNodes.push(node.right);
				}
			} else {
				if (node.right != null) {
					nextNodes.push(node.right);
				}
				if (node.left != null) {
					nextNodes.push(node.left);
				}
			}
			// swapping the stacks in case currentNodes is empty
			if (currentNodes.isEmpty() && !nextNodes.isEmpty()) {
				Stack<TreeNode> tmp = nextNodes;
				nextNodes = currentNodes;
				currentNodes = tmp;
				isLefttoRight = !isLefttoRight;
				result.add(new ArrayList<>());
				currentIndex++;
			}
		}
		return result;
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		levelOrderHelper(root, 0, result);
		return result;
	}

	private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> result) {
		if (result.size() == level) {
			result.add(new ArrayList<>());
		}
		List<Integer> list = result.get(level);
		list.add(node.key);

		if (node.left != null) {
			levelOrderHelper(node.left, level + 1, result);
		}
		if (node.right != null) {
			levelOrderHelper(node.right, level + 1, result);
		}
	}

	public List<List<Integer>> levelOrderUsingQueue(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int level = 0;

		while (!queue.isEmpty()) {
			// creating a list to hold the result
			result.add(new ArrayList<>());
			// Imp : we cann't use queue.size is loop as it will lead to wrong result
			int levelLength = queue.size();
			for (int i = 0; i < levelLength; i++) {
				TreeNode node = queue.remove();
				result.get(level).add(node.key);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			level++;
		}

		return result;
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p != null && q != null) {
			if (p.key != q.key) {
				return false;
			}
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
		return false;
	}

	public boolean isSymmetric(TreeNode root) {
		return isSymmetricHelper(root, root);
	}

	private boolean isSymmetricHelper(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		}

		if (node1 != null && node2 != null && node1.key == node2.key) {
			return isSymmetricHelper(node1.left, node2.right) && isSymmetricHelper(node1.right, node2.left);
		}
		return false;
	}

	List<Integer> result = new ArrayList<>();

	/**
	 * Given a binary tree, imagine yourself standing on the right side of it,
	 * return the values of the nodes you can see ordered from top to bottom.
	 */
	public List<Integer> rightSideView(TreeNode root) {
		viewHelper(root, 0);
		return result;
	}

	public void viewHelper(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (level == result.size()) {
			result.add(root.key);
		}
		viewHelper(root.right, level + 1);
		viewHelper(root.left, level + 1);
	}
}
