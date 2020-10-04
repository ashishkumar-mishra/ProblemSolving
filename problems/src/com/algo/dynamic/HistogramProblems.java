package com.algo.dynamic;

import java.util.Stack;

public class HistogramProblems {

	/**
	 * Find the largest rectangular area possible in a given histogram where the
	 * largest rectangle can be made of a number of contiguous bars. For simplicity,
	 * assume that all bars have same width and the width is 1 unit.
	 */

	public int largestRectangleArea(int[] histograms) {
		int maxArea = Integer.MIN_VALUE;
		for (int i = 0; i < histograms.length; i++) {
			int minhistogram = histograms[i];
			for (int j = i + 1; j < histograms.length; j++) {
				minhistogram = Math.min(minhistogram, histograms[j]);
				maxArea = Math.max(maxArea, minhistogram * (j - i + 1));
			}
		}
		return maxArea;
	}

	public int getMaxRectangleArea(int[] histogram) {
		int[] segmentTree = getSegmentTree(histogram);
		return getMaxAreaUtil(histogram, segmentTree, 0, histogram.length - 1);
	}

	private int getMaxAreaUtil(int[] histogram, int[] segmentTree, int startIndex, int endIndex) {

		// base cases
		if (startIndex > endIndex) {
			return Integer.MIN_VALUE;
		}
		if (startIndex == endIndex) {
			return histogram[startIndex];
		}

		int minIndex = getMinIndex(histogram, segmentTree, startIndex, endIndex);

		return getMax(getMaxAreaUtil(histogram, segmentTree, startIndex, minIndex - 1),
				getMaxAreaUtil(histogram, segmentTree, minIndex + 1, endIndex),
				(endIndex - startIndex + 1) * histogram[minIndex]);
	}

	int[] getSegmentTree(int[] input) {
		int inputLength = input.length;
		int i = (int) Math.ceil(Math.log(inputLength) / Math.log(2));
		int segmentTreeLength = (int) (2 * Math.pow(2, i)) - 1;
		int[] minSegmentTree = new int[segmentTreeLength];
		constructMinSegmentTree(input, minSegmentTree, 0, inputLength - 1, 0);
		return minSegmentTree;
	}

	int constructMinSegmentTree(int[] input, int[] minSegmentTree, int startIndex, int endIndex, int currentIndex) {
		// if there is only one element in array, store it in current index of segment
		// tree and return
		if (startIndex == endIndex) {
			minSegmentTree[currentIndex] = startIndex;
			return startIndex;
		}
		int mid = (startIndex + endIndex) / 2;
		return minSegmentTree[currentIndex] = minVal(input,
				constructMinSegmentTree(input, minSegmentTree, startIndex, mid, 2 * currentIndex + 1),
				constructMinSegmentTree(input, minSegmentTree, mid + 1, endIndex, 2 * currentIndex + 2));
	}

	int getMinIndex(int[] histogram, int[] minSegmentTree, int startIndexRange, int endIndexRange) {
		if (startIndexRange < 0 || endIndexRange > histogram.length - 1) {
			return -1;
		}
		return getMinIndexUtil(histogram, minSegmentTree, 0, histogram.length - 1, startIndexRange, endIndexRange, 0);
	}

	private int getMinIndexUtil(int[] histogram, int[] minSegmentTree, int startIndex, int endIndex,
			int startIndexRange, int endIndexRange, int currentIndex) {

		// there are three cases
		// case 1: if there is complete overlap
		if (startIndex >= startIndexRange && endIndex <= endIndexRange) {
			return minSegmentTree[currentIndex];
		}
		// case 2: if there is no overlap
		if (startIndex > endIndexRange || startIndexRange > endIndex) {
			return -1;
		}

		// case 3: if there is partial overlap
		int mid = (startIndex + endIndex) / 2;
		return minVal(histogram,
				getMinIndexUtil(histogram, minSegmentTree, startIndex, mid, startIndexRange, endIndexRange,
						2 * currentIndex + 1),
				getMinIndexUtil(histogram, minSegmentTree, mid + 1, endIndex, startIndexRange, endIndexRange,
						2 * currentIndex + 2));
	}

	int minVal(int[] input, int i, int j) {
		if (i == -1)
			return j;
		if (j == -1)
			return i;
		return (input[i] < input[j]) ? i : j;
	}

	private int getMax(int x, int y, int z) {
		if (x >= y && x >= z) {
			return x;
		} else if (y >= x && y >= z) {
			return y;
		} else {
			return z;
		}
	}

	public int maxRectangleAreaUsingStack(int[] histogram) {
		Stack<Integer> stack = new Stack<>();
		int maxArea = Integer.MIN_VALUE;
		int currentIndex = 0;
		while (currentIndex < histogram.length) {
			// add the element to stack in case stack is empty or element at the top of
			// stack the greater than current element
			if (stack.empty() || histogram[stack.peek()] <= histogram[currentIndex]) {
				stack.push(currentIndex++);
			} else {
				int peek = stack.peek();
				stack.pop();
				int width = stack.isEmpty() ? currentIndex : (currentIndex - stack.peek() - 1);
				int area = histogram[peek] * width;
				if (area > maxArea) {
					maxArea = area;
				}
			}
		}
		while (!stack.isEmpty()) {
			int peek = stack.peek();
			stack.pop();
			int width = stack.isEmpty() ? currentIndex : (currentIndex - stack.peek() - 1);
			int area = histogram[peek] * width;
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}

	public void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] agrs) {
		int[] input = { 6, 3, 7,7 };
		HistogramProblems histogramProblems = new HistogramProblems();
		System.out.println(histogramProblems.getMaxRectangleArea(input));
		System.out.println(histogramProblems.largestRectangleArea(input));
		System.out.println(histogramProblems.maxRectangleAreaUsingStack(input));
	}
}
