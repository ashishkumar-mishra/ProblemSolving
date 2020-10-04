package com.algo.leet;

public class FirstBadVersion {
	public int firstBadVersion(int n) {
		int start = 1;
		int end = n;
		while (start < end) {
			// int mid = start + (end - start)/2;
			int mid = (start + end) >>> 1;
			if (isBadVersion(mid)) {
				end = mid;
			} else {
				start = mid + 1;
			}
			if (isBadVersion(start)) {
				return start;
			}
		}
		return start;
	}
	
	// This is part of API in leet code program, here dummy method is added
	private boolean isBadVersion(int num) {
		return true;
	}
}
