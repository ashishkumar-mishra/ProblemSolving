package com.algo.leet;

public class PartitionKSubset {

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		boolean[] visited = new boolean[nums.length];
		if (k == 0 || (sum % k != 0)) {
			return false;
		}

		return partition(nums, 0, visited, 0, k, sum/k);
	}

	private boolean partition(int[] nums, int start, boolean[] visited, int currentSum, int k, int targetSum) {
		// if only one bucket is left
		if (k == 1) {
			return true;
		}
		if (currentSum == targetSum) {
			return partition(nums, 0, visited, 0, k - 1, targetSum);
		}
		for (int i = start; i < nums.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (partition(nums, i + 1, visited, currentSum + nums[i], k, targetSum)) {
					return true;
				}
				visited[i] = false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int nums[] = {2, 1, 4, 5, 3, 3};
		int k = 6;
		PartitionKSubset partition = new PartitionKSubset();
		System.out.println(partition.canPartitionKSubsets(nums, k));
	}

}
