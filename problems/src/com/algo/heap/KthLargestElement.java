package com.algo.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElement {

	public int findKthLargest(int[] nums, int k) {
		Queue<Integer> queue = new PriorityQueue<Integer>(k, (n1, n2) -> n1 - n2);
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.poll();
	}

	public static void main(String[] args) {
		KthLargestElement findKthLargest = new KthLargestElement();
		int[] nums = { 3, 2, 1, 5, 6, 4 };
		System.out.println(findKthLargest.findKthLargest(nums, 3));
	}
}
