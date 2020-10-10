package com.algo.dynamic;

public class HouseRobber {

	/**
	 * There are n houses build in a line, each of which contains some value in it.
	 * A thief is going to steal the maximal value of these houses, but he can’t
	 * steal in two adjacent houses because the owner of the stolen houses will tell
	 * his two neighbors left and right side. What is the maximum stolen value?
	 * 
	 */

	public int rob(int[] nums) {
		return robHelper(nums, 0);
	}

	private int robHelper(int[] nums, int index) {
		if (index >= nums.length) {
			return 0;
		}
		return Math.max(nums[index] + robHelper(nums, index + 2), robHelper(nums, index + 1));
	}

	public int robDynamic(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] result = new int[nums.length];
		result[0] = nums[0];
		result[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			result[i] = Math.max(nums[i] + result[i - 2], result[i - 1]);
		}
		return result[result.length - 1];
	}

	public int robDynamicConstantSpace(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int previousMax = nums[0];
		int currentMax = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			int tmp = currentMax;
			currentMax = Math.max(previousMax + nums[i], currentMax);
			previousMax = tmp;
		}
		return currentMax;
	}

	public int robCircular(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int max1 = robCircularHelper(nums, 0, nums.length - 2);
		int max2 = robCircularHelper(nums, 1, nums.length - 1);

		return Math.max(max1, max2);
	}

	private int robCircularHelper(int[] nums, int startIndex, int endInex) {

		int previousMax = nums[startIndex];
		int currentMax = Math.max(nums[startIndex], nums[startIndex + 1]);
		for (int i = startIndex + 2; i <= endInex; i++) {
			int tmp = currentMax;
			currentMax = Math.max(previousMax + nums[i], currentMax);
			previousMax = tmp;
		}
		return currentMax;
	}

	public static void main(String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		int[] nums = { 2, 7, 9, 3, 1 };
		System.out.println(houseRobber.rob(nums));
		System.out.println(houseRobber.robDynamic(nums));
		System.out.println(houseRobber.robDynamicConstantSpace(nums));
	}
}
