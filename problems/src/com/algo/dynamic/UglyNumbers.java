package com.algo.dynamic;

public class UglyNumbers {

	public int getUglyNumber(int n) {

		int counter = 0;
		int i = 1;
		int uglyNumber = 0;

		while (n > counter) {
			if (isUgly(i)) {
				counter++;
				uglyNumber = i;
			}
			i++;
		}
		return uglyNumber;
	}

	public boolean isUgly(int number) {
		if (number == 1) {
			return true;
		}
		while (number % 2 == 0) {
			number = number / 2;
		}

		while (number % 3 == 0) {
			number = number / 3;
		}

		while (number % 5 == 0) {
			number = number / 5;
		}

		return number == 1 ? true : false;
	}

	public int getUglyNumberDynamic(int number) {
		int[] uglyNumbers = new int[number];

		int nextMultiplicationOfTwo = 2;
		int nextMultiplicationOfThree = 3;
		int nextMultiplicationOfFive = 5;
		
		int a2 = 0;
		int a3 = 0;
		int a5 = 0;
		uglyNumbers[0] = 1;

		for (int i = 1; i < number; i++) {
			
			int nextUglyNumber = Math.min(nextMultiplicationOfTwo,
					Math.min(nextMultiplicationOfThree, nextMultiplicationOfFive));
			uglyNumbers[i] = nextUglyNumber;
			
			if (nextUglyNumber == nextMultiplicationOfTwo) {
				a2 = a2 + 1;
				nextMultiplicationOfTwo = uglyNumbers[a2] * 2;
			}
			
			if (nextUglyNumber == nextMultiplicationOfThree) {
				a3 = a3 + 1;
				nextMultiplicationOfThree = uglyNumbers[a3] * 3;
			}
			
			if (nextUglyNumber == nextMultiplicationOfFive) {
				a5 = a5 + 1;
				nextMultiplicationOfFive = uglyNumbers[a5] * 5;
			}
		}
		return uglyNumbers[uglyNumbers.length - 1];
	}

	public static void main(String[] args) {
		UglyNumbers uglyNumbers = new UglyNumbers();
		System.out.println(uglyNumbers.getUglyNumber(15));
		System.out.println(uglyNumbers.getUglyNumberDynamic(15));
	}
}
