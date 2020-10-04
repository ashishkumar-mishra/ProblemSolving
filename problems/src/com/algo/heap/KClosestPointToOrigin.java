package com.algo.heap;

import java.util.Arrays;

public class KClosestPointToOrigin {
	public int[][] kClosest(int[][] points, int k) {

		int[][] result = new int[k][2];
		int[] lengthsFromOrigin = new int[points.length];

		for (int i = 0; i < points.length; i++) {
			lengthsFromOrigin[i] = distance(points[i][0], points[i][1]);
		}

		Arrays.sort(lengthsFromOrigin);
		int kthDistance = lengthsFromOrigin[k - 1];
		int t = 0;
		for (int i = 0; i < points.length; i++) {
			if (distance(points[i][0], points[i][1]) <= kthDistance) {
				result[t++] = points[i];
			}
		}

		return result;
	}

	private int distance(int x, int y) {
		return x * x + y * y;
	}

}
