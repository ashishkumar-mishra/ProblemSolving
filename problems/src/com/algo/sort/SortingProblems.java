package com.algo.sort;

public class SortingProblems {

	/**
	 * method to sort an array in O(n^2) time using optimized bubble sort.
	 * 
	 * @param array array to be sorted
	 * @return sorted array
	 */
	public int[] bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			boolean swapped = false;
			for (int j = 0; j < array.length - (i + 1); j++) {
				if (array[j] > array[j + 1]) {
					int tmp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = tmp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		return array;
	}

	/**
	 * method to search an element in sorted array, if element is found it will
	 * return the index otherwise it will return -1.
	 * 
	 */

	public int bindarySearch(int[] array, int elementToSearch, int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}
		int mid = (startIndex + endIndex) / 2;
		if (array[mid] == elementToSearch) {
			return mid;
		}
		if (array[mid] > elementToSearch) {
			return bindarySearch(array, elementToSearch, startIndex, mid - 1);
		} else {
			return bindarySearch(array, elementToSearch, mid + 1, endIndex);
		}
	}

	public int iterativeBindarySearch(int[] array, int elementToSearch) {
		int startIndex = 0;
		int endIndex = array.length - 1;
		while (startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) / 2;
			if (array[midIndex] == elementToSearch) {
				return midIndex;
			}
			if (elementToSearch > array[midIndex]) {
				startIndex = midIndex + 1;
			} else {
				endIndex = midIndex - 1;
			}
		}
		return -1;
	}

	public void mergeSort(int[] array, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(array, start, mid);
			mergeSort(array, mid + 1, end);
			merge(array, start, mid, end);
		}
	}

	private void merge(int[] array, int start, int mid, int end) {

		int l1 = mid - start + 1;
		int l2 = end - mid;
		int[] left = new int[l1];
		int[] right = new int[l2];

		for (int i = 0; i < l1; i++) {
			left[i] = array[start + i];
		}
		for (int j = 0; j < l2; j++) {
			right[j] = array[mid + 1 + j];
		}
		int i = 0, j = 0;
		int k = start;

		while (i < l1 && j < l2) {
			if (left[i] > right[j]) {
				array[k] = right[j];
				j++;
			} else {
				array[k] = left[i];
				i++;
			}
			k++;
		}
		while (i < l1) {
			array[k] = left[i];
			i++;
			k++;
		}

		while (j < l2) {
			array[k] = right[j];
			j++;
			k++;
		}

	}

	public int getSecondMin(int[] array) {
		int min = Integer.MAX_VALUE;
		int minSecond = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (min > array[i]) {
				min = array[i];
			}
		}
		for(int i = 0; i < array.length; i++) {
			if(array[i] > min && minSecond > array[i]) {
				minSecond = array[i];
			}
		}
		if(minSecond == Integer.MAX_VALUE) {
			return 0;
		}
		return minSecond;
	}
	
	public void quickSort(int[] array, int low, int high) {
		
	}

	public static void main(String args[]) {
		int[] array = { 7, 9, 5, -1, 40, 2,-4 };
		SortingProblems sortingProblems = new SortingProblems();
		//sortingProblems.mergeSort(array, 0, array.length - 1);
		// sortingProblems.bubbleSort(array);
		System.out.println("second min="+sortingProblems.getSecondMin(array));
		printArray(array);
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
}
