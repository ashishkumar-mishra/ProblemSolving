package com.algo.leet;

public class ContainerWithMostWater {

	/**
	 * Given n non-negative integers a1, a2, ..., an , where each represents a point
	 * at coordinate (i, ai). n vertical lines are drawn such that the two end
	 * points of line i is at (i, ai) and (i, 0). Find two lines, which together
	 * with x-axis forms a container, such that the container contains the most
	 * water.
	 */

	public int maxArea(int[] height) {
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int area = Math.min(height[i], height[j]) * (j - i);
				if (area > maxArea) {
					maxArea = area;
				}
			}
		}
		return maxArea;
	}

	public int maxAreaLinearComplexity(int[] height) {
		int maxArea = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			int area = Math.min(height[left], height[right]) * (right - left);
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[left] < height[right]) {
				left++;
			} else {
				right --;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[] heights = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		ContainerWithMostWater cont = new ContainerWithMostWater();
		System.out.println(cont.maxArea(heights));
		System.out.println(cont.maxAreaLinearComplexity(heights));
	}
}
