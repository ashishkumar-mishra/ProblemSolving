package com.algo.leet;

public class ArrayRotationProblems {
	/**
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown
	 * to you beforehand. (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). You
	 * are given a target value to search. If found in the array return its index,
	 * otherwise return -1. You may assume no duplicate exists in the array. Your
	 * algorithm's runtime complexity must be in the order of O(log n).
	 */

	public int searchInRotatedArray(int[] nums, int target) {
		int index = -1;
		if (nums.length == 0) {
			return index;
		}
		int rotatedIndex = find_rotate_index(nums, 0, nums.length - 1);
		if (nums[rotatedIndex] == target) {
			return rotatedIndex;
		}
		// if first number is greater than target search in right side
		if (target < nums[0]) {
			index = binarySearch(nums, target, rotatedIndex, nums.length - 1);
		} else {
			index = binarySearch(nums, target, 0, rotatedIndex);
		}
		System.out.println(index);
		return index;
	}

	public int find_rotate_index(int[] nums, int left, int right) {
		if (nums[left] < nums[right]) {
			return 0;
		}
		while (left <= right) {
			int pivot = (left + right) / 2;
			if (nums[pivot] > nums[pivot + 1]) {
				return pivot + 1;
			} else {
				if (nums[pivot] < nums[left]) {
					right = pivot - 1;
				} else {
					left = pivot + 1;
				}
			}

		}
		return 0;
	}

	public int binarySearch(int nums[], int target, int left, int right) {
		while (left <= right) {
			int midIndex = (left + right) / 2;
			if (nums[midIndex] == target) {
				return midIndex;
			}
			if (nums[midIndex] < target) {
				left = midIndex + 1;
			} else {
				right = midIndex - 1;
			}
		}
		return -1;
	}

	public int[] rotateLeft(int[] nums, int dist) {
		int[] tmp = new int[nums.length];
		for (int i = dist; i < nums .length; i++) {
			tmp[i - dist] = nums[i];
		}
		for(int i = 0; i < dist; i++) {
			tmp[nums.length - dist + i] = nums[i];
		}
		return tmp;
	}

	public int[] rotateRight(int[] nums, int dist) {
		return nums;
	}

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 8, 1, 2 };
		ArrayRotationProblems problem = new ArrayRotationProblems();
		problem.searchInRotatedArray(nums, 2);
		int[] nums1 = {1,2,3,4,5,6};
		int[] rotatedArray = problem.rotateLeft(nums1, 2);
		for(int element : rotatedArray) {
			System.out.println("ele=" + element);
		}
	}
}
