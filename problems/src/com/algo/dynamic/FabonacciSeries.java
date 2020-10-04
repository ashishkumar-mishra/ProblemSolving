package com.algo.dynamic;

public class FabonacciSeries {

	public int[] getFabonacci(int numberOfNumbers) {
		int[] fabonacciNumbers = new int[numberOfNumbers];
		fabonacciNumbers[0] = 0;
		fabonacciNumbers[1] = 1;
		for (int i = 2; i < fabonacciNumbers.length; i++) {
			fabonacciNumbers[i] = fabonacciNumbers[i - 1] + fabonacciNumbers[i - 2];
		}
		return fabonacciNumbers;
	}
	
	public static void main(String[] args) {
		FabonacciSeries problems = new FabonacciSeries();
		int[] array = problems.getFabonacci(10);
		for(int i =0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
