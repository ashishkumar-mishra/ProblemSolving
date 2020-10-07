package com.algo.leet;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTime {

	public int[] exclusiveTime(int n, List<String> logs) {
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		String[] arr = logs.get(0).split("\\:");
		int start = 1;
		int previous = Integer.valueOf(arr[2]);
		stack.push(Integer.valueOf(arr[0]));
		while (start < logs.size()) {
			String[] tmp = logs.get(start).split("\\:");
			if (tmp[1].equals("start")) {
				if (!stack.isEmpty()) {
					result[stack.peek()] += Integer.valueOf(tmp[2]) - previous;
				}
				previous = Integer.valueOf(tmp[2]);
				stack.push(Integer.valueOf(tmp[0]));
			} else {
				result[stack.peek()] += Integer.valueOf(tmp[2]) - previous + 1;
				stack.pop();
				previous = Integer.valueOf(tmp[2]) + 1;
			}
			start++;
		}
		return result;
	}

	public static void main(String[] args) {
		String[] arr = { "0:start:0", "1:start:2", "1:end:5", "0:end:6" };
		List<String> logs = Arrays.asList(arr);
		ExclusiveTime exclusiveTime = new ExclusiveTime();
		int[] result = exclusiveTime.exclusiveTime(2, logs);
		for (int a : result) {
			System.out.print(a + ",");
		}
	}
}
