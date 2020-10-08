package com.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ArrayProblems {

	public int findMaxSum(int[] array) {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < array.length; i = i + 2) {
			sum1 = Math.max(sum1, sum1 + array[i]);
		}
		for (int i = 1; i < array.length; i = i + 2) {
			sum2 = Math.max(sum2, sum2 + array[i]);
		}
		return Math.max(sum1, sum2);
	}

	/**
	 * method to reverse the number, we are assuming that we are dealing with
	 * environment where we can store 32 bit signed integer [-2^31 to 2^31 -1] In
	 * case reversing the number going beyond the limit 0 is returned.
	 */

	public int reverseInteger(int number) {
		int reverse = 0;
		while (number != 0) {
			int pop = number % 10;
			number = number / 10;
			if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}
			if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			reverse = reverse * 10 + pop;
		}
		return reverse;
	}

	/**
	 * method to check whether a given number is palindrome
	 * 
	 * @param args
	 */

	public boolean isPalindrome(int number) {
		if (number < 0 || (number != 0 && number % 10 == 0)) {
			return false;
		}
		int firstHalf = number;
		int secondHalf = 0;

		while (firstHalf > secondHalf) {
			secondHalf = secondHalf * 10 + firstHalf % 10;
			firstHalf = firstHalf / 10;
		}

		return ((firstHalf == secondHalf) || (firstHalf == secondHalf / 10));
	}

	public int maxSubArray(int[] nums) {
		int maxSum = nums[0];
		int currSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currSum = Math.max(nums[i], currSum + nums[i]);
			maxSum = Math.max(maxSum, currSum);
		}
		return maxSum;
	}

	public int romanToInt(String roman) {
		int result = 0;
		int previousDigit = 1000;
		for (int i = 0; i < roman.length(); i++) {
			int val = getDigit(roman.charAt(i));
			if (val > previousDigit) {
				result = result + val - 2 * previousDigit;
			} else {
				result = result + val;
			}
			previousDigit = val;
		}
		return result;
	}

	public boolean backSpaceCompare(String str1, String str2) {
		return build(str1).equals(build(str2));
	}

	private String build(String str) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '#') {
				stack.push(str.charAt(i));
			} else if (!stack.isEmpty()) {
				stack.pop();
			}
		}
		return String.valueOf(stack);
	}

	private int getDigit(char character) {
		switch (character) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;

		}
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
		Arrays.sort(nums);

		// fix the first element and find others
		for (int i = 0; i < nums.length - 2; i++) {
			int startIndex = i + 1;
			int endIndex = nums.length - 1;
			while (startIndex < endIndex) {
				if (nums[i] + nums[startIndex] + nums[endIndex] == 0) {
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[startIndex]);
					list.add(nums[endIndex]);
					result.add(list);
				}
				if (nums[i] + nums[startIndex] + nums[endIndex] < 0) {
					startIndex++;
				} else {
					endIndex--;
				}
			}
		}
		return new ArrayList<>(result);
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int min = Integer.MAX_VALUE;
		int result = 0;

		for (int i = 0; i < nums.length - 2; i++) {
			int start = i + 1;
			int end = nums.length - 1;
			while (start < end) {
				int sum = nums[i] + nums[start] + nums[end];
				int difference = Math.abs(sum - target);
				if (difference == 0) {
					return sum;
				}
				if (difference < min) {
					min = difference;
					result = sum;
				}
				if (sum <= target) {
					start++;
				} else {
					end--;
				}
			}
		}
		return result;
	}

	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int result = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int start = i + 1;
			int end = nums.length - 1;
			while (start < end) {
				if (nums[i] + nums[start] + nums[end] >= target) {
					end--;
				} else {
					result = result + (end - start);
					start++;

				}
			}
		}
		return result;
	}

	public List<List<Integer>> fourSum(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
		Arrays.sort(nums);

		// fix the first element and find others
		for (int i = 0; i < nums.length - 3; i++) {
			for (int j = i + 1; j < nums.length - 2; j++) {
				int startIndex = j + 1;
				int endIndex = nums.length - 1;
				while (startIndex < endIndex) {
					if (nums[i] + nums[j] + nums[startIndex] + nums[endIndex] == 0) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[startIndex]);
						list.add(nums[endIndex]);
						result.add(list);
					}
					if (nums[i] + nums[j] + nums[startIndex] + nums[endIndex] < 0) {
						startIndex++;
					} else {
						endIndex--;
					}
				}
			}
		}
		return new ArrayList<>(result);
	}

	public double medianOfSortedArrays(int nums1[], int nums2[]) {
		if (nums1.length > nums2.length) {
			return medianOfSortedArrays(nums2, nums1);
		}

		int x = nums1.length;
		int y = nums2.length;

		int start = 0;
		int end = x;
		while (start <= end) {
			int positionX = (start + end) / 2;
			int positionY = (x + y + 1) / 2 - positionX;
			int maxOfLeftX = 0;
			int minRightX = 0;
			int maxLeftY = 0;
			int minRightY = 0;
			// setting x values
			if (positionX == 0) {
				maxOfLeftX = Integer.MIN_VALUE;
			} else {
				maxOfLeftX = nums1[positionX - 1];
			}
			if (positionX == x) {
				minRightX = Integer.MAX_VALUE;
			} else {
				minRightX = nums1[positionX];
			}
			// setting y values

			if (positionY == 0) {
				maxLeftY = Integer.MIN_VALUE;
			} else {
				maxLeftY = nums2[positionY - 1];
			}
			if (positionY == y) {
				minRightY = Integer.MAX_VALUE;
			} else {
				minRightY = nums1[positionY];
			}

			// in case condition is met
			if ((maxOfLeftX <= minRightY) && (maxLeftY <= minRightX)) {
				if ((x + y) % 2 == 0) {
					return (double) (Math.max(maxOfLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) (Math.max(maxOfLeftX, maxLeftY));
				}
			} else if (maxOfLeftX > minRightY) { // we are too far on right
				end = end - 1;
			} else {
				start = start + 1;
			}
		}
		return 0;
	}

	/**
	 * Given an array of integers nums sorted in ascending order, find the starting
	 * and ending position of a given target value. Your algorithm's runtime
	 * complexity must be in the order of O(log n).If the target is not found in the
	 * array, return [-1, -1].
	 */

	public int[] searchRange(int[] nums, int target) {
		int[] indexes = { -1, -1 };

		int lowIndex = findLowHiIndex(nums, target, true);
		if (nums[lowIndex] != target) {
			return indexes;
		}
		int highIndex = findLowHiIndex(nums, target, false) - 1;
		indexes[0] = lowIndex;
		indexes[1] = highIndex;
		return indexes;
	}

	private int findLowHiIndex(int[] nums, int target, boolean left) {
		int low = 0;
		int high = nums.length;
		while (low < high) {
			int mid = (low + high) / 2;
			if (nums[mid] > target || (left && nums[mid] == target)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public int[] zigzag(int[] array) {

		// true is expecting <
		boolean flag = true;
		for (int i = 0; i < array.length - 1; i++) {
			if (flag) {
				if (array[i] > array[i + 1]) {
					int tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
				}
			} else {
				// > expected
				if (array[i] < array[i + 1]) {
					int tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
				}
			}
			flag = !flag;
		}
		return array;
	}

	// method for the intersection of two arrays
	public int[] intersect(int[] nums1, int[] nums2) {

		if (nums1.length > nums2.length) {
			intersect(nums2, nums1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int counter = 0;
		for (int num : nums1) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (int num : nums2) {
			int count = map.getOrDefault(num, 0);
			if (count > 0) {
				nums1[counter++] = num;
				map.put(num, count - 1);
			}
		}
		return Arrays.copyOfRange(nums1, 0, counter);
	}

	// method for intersecting the array using sorting
	public int[] intersectSorting(int[] nums1, int[] nums2) {

		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				nums1[k++] = nums1[i++];
				j++;
			}
		}
		return Arrays.copyOfRange(nums1, 0, k);
	}

	/**
	 * Given an array of integers and an integer k, you need to find the total
	 * number of continuous subarrays whose sum equals to k.
	 * 
	 * @return
	 */
	public int subarraysum(int[] nums, int k) {
		int count = 0;
		for (int start = 0; start < nums.length; start++) {
			int sum = 0;
			for (int end = start; end < nums.length; end++) {
				sum += nums[end];
				if (sum == k) {
					count++;
				}
			}
		}
		return count;
	}

	public int subarraysumOptimal(int nums[], int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		int sum = 0;
		map.put(0,1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.getOrDefault(sum - k, 0) != 0) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		ArrayProblems problems = new ArrayProblems();
		int[] array = { 5, 5, 10, 40, -50 };
		System.out.println("max sum=" + problems.findMaxSum(array));
		System.out.print("reverse number=" + problems.reverseInteger(-4578548));
		System.out.println("isPalindrome = " + problems.isPalindrome(1225221));
		System.out.println(problems.romanToInt("IX"));
		System.out.println(problems.backSpaceCompare("a##c", "#a#c"));

		int[] array1 = { -1, 0, 1, 2, -1, -4 };
		System.out.println("result= " + problems.threeSum(array1));
		int[] nums = { 5, 6, 7, 8, 8, 10 };
		int[] result = problems.searchRange(nums, 7);
		System.out.println("low index= " + result[0] + " high Index= " + result[1]);
		int[] array2 = { 10, 40, 5, 5 };
		int[] result1 = problems.intersect(array, array2);
		for (int num : result1) {
			System.out.print(num + " ");
		}
		int[] array3 = { 1,1,1};
		System.out.println(problems.subarraysumOptimal(array3,2));
	}
}
