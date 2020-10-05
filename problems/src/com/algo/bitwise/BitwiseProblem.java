package com.algo.bitwise;

public class BitwiseProblem {

	public boolean checkSparse(int num) {
		if (((num >> 1) & num) >= 1) {
			return false;
		}
		return true;
	}
	
    public static int addOne(int number) {
        int m = 1;
        // find all the set bits until we find 0
        while ((int)(number & m) == 1) {
            number = number ^ m;
            m <<= 1;
        }
        number = number ^ m;
        return number;
    }
	
	public static void main(String[] args) {
		BitwiseProblem problem = new BitwiseProblem();
		System.out.println(problem.checkSparse(5));
	}
}
