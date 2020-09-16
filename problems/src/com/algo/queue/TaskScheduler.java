package com.algo.queue;

import java.util.Arrays;

public class TaskScheduler {

	/**
	 * Given a characters array tasks, representing the tasks a CPU needs to do,
	 * where each letter represents a different task. Tasks could be done in any
	 * order. Each task is done in one unit of time. For each unit of time, the CPU
	 * could complete either one task or just be idle.
	 * 
	 * However, there is a non-negative integer n that represents the cool down
	 * period between two same tasks (the same letter in the array), that is that
	 * there must be at least n units of time between any two same tasks.
	 * 
	 * Return the least number of units of times that the CPU will take to finish
	 * all the given tasks.
	 */

	public int totalTime(char[] tasks, int n) {
		int[] frequencies = new int[26];
		for (char c : tasks) {
			frequencies[c - 'A']++;
		}

		Arrays.sort(frequencies);

		int maxFrequency = frequencies[25];
		int maxIdleTime = (maxFrequency - 1) * n;

		for (int i = frequencies.length - 2; i >= 0 && maxIdleTime > 0; i--) {
			maxIdleTime = maxIdleTime - Math.min(maxFrequency - 1, frequencies[i]);
		}
		maxIdleTime = Math.max(0, maxIdleTime);
		return maxIdleTime + tasks.length;
	}
	
	// other method using Math need to be added

	public static void main(String[] args) {
		TaskScheduler taskScheduler = new TaskScheduler();
		char[] tasks = { 'A', 'B', 'A', 'B' };
		taskScheduler.totalTime(tasks, 2);
	}
}
