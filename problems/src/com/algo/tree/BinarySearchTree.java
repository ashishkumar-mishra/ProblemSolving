package com.algo.tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

	// method to construct all possible BSTs for keys 1 to N

	public List<TreeNode> constructTrees(int start, int end) {
		List<TreeNode> list = new ArrayList<>();
		if (start > end) {
			list.add(null);
			return list;
		}

		for (int i = start; i <= end; i++) {
			List<TreeNode> leftSubTree = constructTrees(start, i - 1);
			List<TreeNode> rightSubTree = constructTrees(i + 1, end);
			for (int j = 0; j < leftSubTree.size(); j++) {
				TreeNode leftNode = leftSubTree.get(j);
				for (int k = 0; k < rightSubTree.size(); k++) {
					TreeNode rightNode = rightSubTree.get(k);
					TreeNode root = new TreeNode(i);
					root.left = leftNode;
					root.right = rightNode;
					list.add(root);
				}
			}
		}
		return list;
	}

	public void preOrderTraversal(TreeNode node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}

	public void inOrderTraversal(TreeNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.print(node.key + " ");
			inOrderTraversal(node.right);
		}
	}

	public int countBinarySearchTreeDynamic(int n) {
		int[] array = new int[n + 1];
		array[0] = 1;
		array[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				array[i] += array[j - 1] * array[i - j];
			}
		}
		return array[n];
	}

	public int countBinarySearchTreeRecursive(int n) {
		int sum = 0;
		if (n <= 1) {
			return 1;
		}
		int left;
		int right;
		int root;

		for (root = 1; root <= n; root++) {
			left = countBinarySearchTreeRecursive(root - 1);
			right = countBinarySearchTreeRecursive(n - root);
			sum += left * right;
		}
		return sum;
	}

	public class TreeNode {
		int key;
		TreeNode left;
		TreeNode right;

		TreeNode(int key) {
			this.key = key;
		}

		TreeNode(int key, TreeNode leftNode, TreeNode rightNode) {
			this.key = key;
			this.left = leftNode;
			this.right = rightNode;
		}
	}

	public boolean isValidBST(TreeNode root) {
		return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	// This solution has wrong answer for [-2147483648,-2147483648]
	private boolean isValidBSTHelper(TreeNode node, int minValue, int maxValue) {
		if (node == null) {
			return true;
		}

		if (node.key < minValue || node.key > maxValue) {
			return false;
		}

		return isValidBSTHelper(node.left, minValue, node.key - 1)
				&& isValidBSTHelper(node.right, node.key + 1, maxValue);
	}

	// solution has wrong answer for [2147483647]

	public boolean isValidBSTHelper1(TreeNode node, int minValue, int maxValue) {
		if (node == null) {
			return true;
		}

		if (node.key <= minValue || node.key >= maxValue) {
			return false;
		}

		return isValidBSTHelper1(node.left, minValue, node.key) && isValidBSTHelper1(node.right, node.key, maxValue);
	}

	// This code is accepted
	public boolean isValidBSTInorder(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		isValidUtil(root, list);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1) >= list.get(i)) {
				return false;
			}
		}
		return true;
	}

	private void isValidUtil(TreeNode node, List<Integer> list) {
		if (node != null) {
			isValidUtil(node.left, list);
			list.add(node.key);
			isValidUtil(node.right, list);
		}
	}

	public boolean isValidBSTInorderNoExtraSpace(TreeNode root) {
		return isValidUtilNoExtraSpace(root);
	}

	TreeNode previousNode = null;

	private boolean isValidUtilNoExtraSpace(TreeNode root) {
		if (root != null) {
			if (!isValidUtilNoExtraSpace(root.left)) {
				return false;
			}
			if (previousNode != null && root.key <= previousNode.key) {
				return false;
			}
			previousNode = root;
			return isValidUtilNoExtraSpace(root.right);
		}
		return true;
	}

	int sum = 0;

	public int rangeSumBST(TreeNode root, int leftRange, int rightRange) {
		if (root != null) {
			if (root.key >= leftRange && root.key <= rightRange) {
				sum += root.key;
			}
			if (root.key > leftRange) {
				rangeSumBST(root.left, leftRange, rightRange);
			}
			if (root.key < rightRange) {
				rangeSumBST(root.right, leftRange, rightRange);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		System.out.println(binarySearchTree.countBinarySearchTreeDynamic(5));
		System.out.println(binarySearchTree.countBinarySearchTreeRecursive(5));

		List<TreeNode> trees = binarySearchTree.constructTrees(1, 4);
		for (int i = 0; i < trees.size(); i++) {
			binarySearchTree.preOrderTraversal(trees.get(i));
			System.out.println();
		}
		BinarySearchTree.TreeNode root = binarySearchTree.new TreeNode(3);
		root.left = binarySearchTree.new TreeNode(2);
		root.right = binarySearchTree.new TreeNode(5);
		root.left.left = binarySearchTree.new TreeNode(1);
		root.left.right = binarySearchTree.new TreeNode(4);
		System.out.println(binarySearchTree.isValidBSTInorder(root));
		System.out.println(binarySearchTree.isValidBSTInorderNoExtraSpace(root));
		// System.out.println(binarySearchTree.levelOrder(root));
		// System.out.println(binarySearchTree.levelOrderUsingQueue(root));
		// System.out.println(binarySearchTree.zigzagLevelOrder(root));
	}
}
