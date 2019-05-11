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
}
