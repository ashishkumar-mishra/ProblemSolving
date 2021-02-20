package com.algo.leet;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

	public int longestConsecutive(int[] nums) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (num > max) {
				max = num;
			}
			if (num < min) {
				min = num;
			}
			set.add(num);
		}
		int start = min;
		int end = start;
		int maxlen = 0;
		while (start <= end && end <= max) {
			if (!set.contains(end)) {
				maxlen = Math.max(maxlen, (end - start));
				while (!set.contains(end)) {
					end++;
				}
				start = end;
			} else {
				end++;
			}
		}
		maxlen = Math.max(maxlen, end - start);
		return maxlen;
	}

	public static void main(String[] args) {
		int[] nums = { 9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6 };
		LongestConsecutiveSequence s = new LongestConsecutiveSequence();
		System.out.println(s.longestConsecutive(nums));
	}
}
