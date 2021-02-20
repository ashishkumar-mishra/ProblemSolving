package com.algo.dynamic;

public class DiceThrow {

	/**
	 * Given n dice, with m faces, find the number of ways to get the sum as x, this
	 * method will recursively solve the problem
	 */

	public int getNumberOfWaysRecursive(int faceCount, int diceNum, int sum) {
		// Getting less than 1 sum is not possible
		if (sum < 1) {
			return 0;
		}
		if (diceNum == 1 && sum <= faceCount) {
			return 1;
		}
		int numberOfWays = 0;
		for (int i = 1; i <= faceCount; i++) {
			numberOfWays += getNumberOfWaysRecursive(faceCount, diceNum - 1, sum - i);
		}
		return numberOfWays;
	}
	
	
	/* The main function that returns the number of ways to get sum 'x' with 'n' dice and 'm' with m faces. */
    public long findWays(int m, int n, int x){ 
          
    /* Create a table to store the results of subproblems.  
    One extra row and column are used for simplicity  
    (Number of dice is directly used as row index and sum is directly used as column index).  
    The entries in 0th row and 0th column are never used. */
    long[][] table = new long[n+1][x+1]; 
          
    /* Table entries for only one dice */
    for(int j = 1; j <= m && j <= x; j++) 
                table[1][j] = 1; 
              
    /* Fill rest of the entries in table using recursive relation  
    i: number of dice, j: sum */
    for(int i = 2; i <= n;i ++){ 
                for(int j = 1; j <= x; j++){ 
                    for(int k = 1; k < j && k <= m; k++) {
                    	 table[i][j] += table[i-1][j-k]; 
                    }
                } 
        } 
          
        return table[n][x]; 
    } 

	public static void main(String[] args) {
		DiceThrow diceThrow = new DiceThrow();
		System.out.println(diceThrow.getNumberOfWaysRecursive(4, 3, 5));
	}
}
