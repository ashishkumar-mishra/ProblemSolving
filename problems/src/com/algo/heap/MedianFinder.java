package com.algo.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
	Queue<Integer> maxheap; //lo
	Queue<Integer> minheap; //hi

	MedianFinder() {
		maxheap = new PriorityQueue<>((a1, a2) -> (a2 - a1));
		minheap = new PriorityQueue<>((a1, a2) -> (a1 - a2));
	}

	public void addNum(int num) {
		maxheap.add(num);
		// balancing
		minheap.add(maxheap.poll());

		if (minheap.size() > maxheap.size()) {
			maxheap.add(minheap.poll());
		}
	}

	public double findMedian() {
		if (maxheap.size() > minheap.size()) {
			return maxheap.peek();
		} else {
			return 1.0 * (maxheap.peek() + minheap.peek()) / 2;
		}
	}
}
