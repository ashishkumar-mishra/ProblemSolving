package com.algo.leet;

public class ProductOfArrayExceptSelf {

	/**
	 * Given an array nums of n integers where n > 1, return an array output such
	 * that output[i] is equal to the product of all the elements of nums except
	 * nums[i]. This is done without using division.
	 * 
	 * @param nums input array
	 * @return array containing the product
	 */
	public int[] productExceptSelf(int[] nums) {
		int[] leftProduct = new int[nums.length];
		int[] rightProduct = new int[nums.length];
		int[] result = new int[nums.length];

		leftProduct[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
		}

		rightProduct[nums.length - 1] = 1;

		for (int i = nums.length - 2; i <= 0; i--) {
			rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
		}

		// storing the result
		for (int i = 0; i < nums.length; i++) {
			result[i] = leftProduct[i] * rightProduct[i];
		}
		return result;
	}

	public int[] productExceptSelfSpaceOtimized(int[] nums) {
		int[] leftProduct = new int[nums.length];
		int[] result = new int[nums.length];

		leftProduct[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
		}

		int rightProduct = 1;

		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] = leftProduct[i] * rightProduct;
			rightProduct = rightProduct * nums[i];
		}
		return result;
	}
}
