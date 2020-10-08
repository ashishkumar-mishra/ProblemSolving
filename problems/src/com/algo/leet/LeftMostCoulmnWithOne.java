package com.algo.leet;

import java.util.List;

public class LeftMostCoulmnWithOne {

	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

		int rows = binaryMatrix.getDimenstions().get(0);
		int cols = binaryMatrix.getDimenstions().get(1);

		int minIndex = Integer.MAX_VALUE;

		// looping all the rows

		for (int row = 0; row < rows; row++) {

			// do binary search to find out min index of 1
			int high = cols - 1;
			int low = 0;

			while (high > low) {
				int mid = (high + low) / 2;
				// if mid is 0, 1 will be at right
				if (binaryMatrix.get(row, mid) == 0) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			if (binaryMatrix.get(row, low) == 1) {
				minIndex = Math.min(minIndex, low);
			}
		}
		return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
	}

	public int leftMostColumnWithOneOptimal(BinaryMatrix binaryMatrix) {

		int rows = binaryMatrix.getDimenstions().get(0);
		int cols = binaryMatrix.getDimenstions().get(1);

		int col = cols - 1;
		int row = 0;

		while (row < rows && col >= 0) {
			if (binaryMatrix.get(row, col) == 0) {
				row++;
			} else {
				col--;
			}
		}
		return col == cols - 1 ? -1 : col + 1;
	}
}

interface BinaryMatrix {

	public int get(int row, int col);

	public List<Integer> getDimenstions();
}