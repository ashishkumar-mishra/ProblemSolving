package com.algo.tree;

public class MinSegmentTree {

	public int[] minSegmentTree;

	public MinSegmentTree(int[] input) {
		int inputLength = input.length;
		int i = (int) Math.ceil(Math.log(inputLength) / Math.log(2));
		int segmentTreeLength = (int) (2 * Math.pow(2, i)) - 1;
		minSegmentTree = new int[segmentTreeLength];
		constructMinSegmentTree(input, 0, inputLength - 1, 0);
	}

	int constructMinSegmentTree(int[] input, int startIndex, int endIndex, int currentIndex) {

		// if there is only one element in array, store it in current index of segment
		// tree and return
		if (startIndex == endIndex) {
			minSegmentTree[currentIndex] = input[startIndex];
			return input[startIndex];
		}
		int mid = (startIndex + endIndex) / 2;
		return minSegmentTree[currentIndex] = Math.min(
				constructMinSegmentTree(input, startIndex, mid, 2 * currentIndex + 1),
				constructMinSegmentTree(input, mid + 1, endIndex, 2 * currentIndex + 2));
	}

	public int getMinimum(int inputLength, int startIndex, int endIndex) {
		if (startIndex < 0 || endIndex > inputLength - 1) {
			System.out.println("invalid input");
			return Integer.MAX_VALUE;
		}
		return getMinimunUtil(0, inputLength - 1, startIndex, endIndex, 0);
	}

	int getMinimunUtil(int startIndex, int endIndex, int startIndexRange, int endIndexRange, int currentIndex) {
		System.out.println("(" + startIndex + ", " + endIndex + ", " + startIndexRange + ", "+ endIndexRange + ", "+ currentIndex + ")");
		// there are three cases, complete overlap, partial overlap and no overlap
		// case 1 : complete overlap, return the value of current node
		if (startIndex >= startIndexRange && endIndex <= endIndexRange) {
			return minSegmentTree[currentIndex];
		}

		// case 2: if segment of the node is outside of the given range, return the
		// really big number

		if (startIndex > endIndexRange || endIndex < startIndexRange) {
			return Integer.MAX_VALUE;
		}

		// case 3: partial overlap

		int mid = (startIndex + endIndex) / 2;
		return Math.min(getMinimunUtil(startIndex, mid, startIndexRange, endIndexRange, 2 * currentIndex + 1),
				getMinimunUtil(mid + 1, endIndex, startIndexRange, endIndexRange, 2 * currentIndex + 2));
	}

	public void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] input = { 1, 3, 2, 5 };
		MinSegmentTree minSegmentTree = new MinSegmentTree(input);
		minSegmentTree.printArray(minSegmentTree.minSegmentTree);
		int min = minSegmentTree.getMinimum(input.length, 1, 1);
		System.out.println("minimum value= " + min);
	}
}
