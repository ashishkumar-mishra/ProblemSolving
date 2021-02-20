package com.algo.array;

import java.util.TreeMap;

public class DivideinSetOfKConsecutive {

	/**
	 * Given an array of integers nums and a positive integer k, find whether it's
	 * possible to divide this array into sets of k consecutive numbers Return True
	 * if it is possible. Otherwise, return False.
	 * 
	 */
	public boolean isPossibleDivide(int[] nums, int k) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		while (map.size() > 0) {
			int startKey = map.firstKey();
			for (int i = startKey; i < startKey + k; i++) {
				if (!map.containsKey(i)) {
					return false;
				}
				if (map.get(i) == 1) {
					map.remove(i);
				} else {
					map.put(i, map.getOrDefault(i, 0) - 1);
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		DivideinSetOfKConsecutive div = new DivideinSetOfKConsecutive();
		int[] nums = { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
		System.out.println(div.isPossibleDivide(nums, 3));
	}

}
