package com.algo.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 */
public class MovingAverage {

	Queue<Integer> queue = new LinkedList<>();
	int size;
	int currentSum = 0;

	public MovingAverage(int size) {
		this.size = size;
	}

	public double next(int val) {
		int sum = 0;
		queue.add(val);
		if (queue.size() > this.size) {
			queue.poll();
		}
		for (int element : queue) {
			sum += element;
		}
		return sum * 1.0 / queue.size();
	}

	// working code with O(1) time

	public double next1(int val) {
		currentSum += val;
		queue.add(val);
		if (queue.size() > this.size) {
			currentSum -= queue.poll();
		}
		return currentSum * 1.0 / queue.size();
	}

	public static void main(String[] args) {
		MovingAverage movingAverage = new MovingAverage(3);
		System.out.println(movingAverage.next1(1));
		System.out.println(movingAverage.next1(10));
		System.out.println(movingAverage.next1(3));
		System.out.println(movingAverage.next1(5));
	}
}
