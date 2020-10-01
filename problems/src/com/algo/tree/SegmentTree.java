package com.algo.tree;

public class SegmentTree {

	int[] segmentTree;

	SegmentTree(int[] input) {
		int inputLength = input.length;
		int i = (int) Math.ceil(Math.log(inputLength) / Math.log(2));
		int segmentTreeLength = 2 * (int) Math.pow(2, i) - 1;
		segmentTree = new int[segmentTreeLength];
		constructSegmentTree(input, 0, inputLength - 1, 0);
	}

	private int constructSegmentTree(int[] input, int startIndex, int endIndex, int currentIndex) {

		// if there is one element in array, store it in currentIndex of segmentTree and
		// return.

		if (startIndex == endIndex) {
			segmentTree[currentIndex] = input[startIndex];
			return input[startIndex];
		}

		int mid = (startIndex + endIndex) / 2;
		segmentTree[currentIndex] = constructSegmentTree(input, startIndex, mid, currentIndex * 2 + 1)
				+ constructSegmentTree(input, mid + 1, endIndex, currentIndex * 2 + 2);
		return segmentTree[currentIndex];
	}

	public int getSum(int inputLength, int startIndex, int endIndex) {
		if ((startIndex > endIndex) || startIndex < 0 || endIndex > inputLength - 1) {
			System.out.println("Invalid input");
			return -1;
		}
		return getSumUtil(0, inputLength - 1, startIndex, endIndex, 0);
	}

	private int getSumUtil(int startIndex, int endIndex, int startRangeIndex, int endRangeIndex, int currentIndex) {

		// there are three cases, complete overlap, partial overlap and no overlap
		// case 1 : complete overlap, return the value of current node

		if (startIndex >= startRangeIndex && endIndex <= endRangeIndex) {
			return segmentTree[currentIndex];
		}

		// case 2: if segment of the node is outside of the given range

		if (startIndex > endRangeIndex || endIndex < startRangeIndex) {
			return 0;
		}

		int mid = (startIndex + endIndex) / 2;

		return getSumUtil(startIndex, mid, startRangeIndex, endRangeIndex, 2 * currentIndex + 1)
				+ getSumUtil(mid + 1, endIndex, startRangeIndex, endRangeIndex, 2 * currentIndex + 2);

	}

	public void updateValue(int[] input, int indexToBeUpdated, int newValue) {
		if (indexToBeUpdated < 0 || indexToBeUpdated > input.length) {
			return;
		}
		int difference = newValue - input[indexToBeUpdated];
		input[indexToBeUpdated] = newValue;
		updateValueUtil(0,input.length - 1,indexToBeUpdated,difference,0);
	}

	private void updateValueUtil(int startIndex, int endIndex, int indexToBeUpdated, int difference, int currentIndex) {
		// if indexToBeUpdated lies outside of segment

		if (startIndex > indexToBeUpdated || endIndex < indexToBeUpdated) {
			return;
		}
		
		segmentTree[currentIndex] = segmentTree[currentIndex] + difference;
		if (startIndex != endIndex) {
			int mid = (startIndex + endIndex) / 2;
			updateValueUtil(startIndex,mid,indexToBeUpdated,difference,2 * currentIndex + 1);
			updateValueUtil(mid + 1,endIndex,indexToBeUpdated,difference,2 * currentIndex + 2);
		}
	}

	public void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] input = { 1, 3, 5, 7, 9, 10 };
		SegmentTree segmentTree = new SegmentTree(input);
		segmentTree.printArray(segmentTree.segmentTree);
		System.out.println("sum in given range is= " + segmentTree.getSum(input.length, 0, 2));
		segmentTree.updateValue(input, 1, 10);
		segmentTree.printArray(segmentTree.segmentTree);
		System.out.println("sum in given range after update is= " + segmentTree.getSum(input.length, 0, 2));
	}

}
