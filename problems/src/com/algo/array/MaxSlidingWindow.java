package com.algo.array;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {

	public int[] maxSlidingWindow(int[] nums, int k) {
		Deque<Integer> dQueue = new LinkedList<>();
		int[] result = new int[nums.length - k + 1];
		int start = 0;
		for (int i = 0; i < k; i++) {
			while (!dQueue.isEmpty() && nums[i] >= nums[dQueue.peekLast()]) {
				dQueue.removeLast();
			}
			dQueue.addLast(i);
		}
		for (int i = k; i < nums.length; i++) {
			result[start++] = nums[dQueue.peek()];
			// remove the element which is out of the window
			while (!dQueue.isEmpty() && dQueue.peek() <= (i - k)) {
				dQueue.removeFirst();
			}

			while (!dQueue.isEmpty() && nums[i] >= nums[dQueue.peekLast()]) {
				dQueue.removeLast();
			}
			dQueue.addLast(i);
		}
		result[start] = nums[dQueue.peek()];
		return result;
	}

	/**
	 * Given an array of integers nums and an integer limit, return the size of the
	 * longest non-empty subarray such that the absolute difference between any two
	 * elements of this subarray is less than or equal to limit.
	 */

	public int longestSubarray(int[] nums, int limit) {
		Deque<Integer> minQ = new LinkedList<>();
		Deque<Integer> maxQ = new LinkedList<>();
		int start = 0;
		int end = 0;
		int maxLength = 0;

		while (end < nums.length) {
			while (!minQ.isEmpty() && nums[minQ.peekLast()] >= nums[end]) {
				minQ.removeLast();
			}
			minQ.addLast(end);

			while (!maxQ.isEmpty() && nums[end] >= nums[maxQ.peekLast()]) {
				maxQ.removeLast();
			}
			maxQ.addLast(end);

			int min = nums[minQ.peekFirst()];
			int max = nums[maxQ.peekFirst()];

			if ((max - min) <= limit) {
				maxLength = Math.max(end - start + 1, maxLength);
				end++;
			} else {
				start++;
				if (maxQ.peek() < start) {
					maxQ.removeFirst();
				}
				if (minQ.peek() < start) {
					minQ.removeFirst();
				}
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int[] nums = { 12, 1, 78, 90, 57, 89, 56 };
		MaxSlidingWindow w = new MaxSlidingWindow();
		int[] result = w.maxSlidingWindow(nums, 3);
		int[] nums1 = { 4, 2, 2, 2, 4, 4, 2, 2 };
		System.out.println(w.longestSubarray(nums1, 0));
		for (int i : result) {
			System.out.print(i + " ");
		}
	}

}
