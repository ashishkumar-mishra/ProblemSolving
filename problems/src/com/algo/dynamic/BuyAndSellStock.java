package com.algo.dynamic;

import java.util.ArrayList;
import java.util.List;

public class BuyAndSellStock {

	/**
	 * max profit in case buying and selling allowed only once.
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int profit = prices[j] - prices[i];
				if (maxProfit < profit) {
					maxProfit = profit;
				}
			}
		}
		return maxProfit;
	}

	public int maxProfitEfficient(int[] prices) {
		int minElement = Integer.MAX_VALUE;
		int maxProfit = 0;

		for (int i = 0; i < prices.length; i++) {
			if (minElement > prices[i]) {
				minElement = prices[i];
			} else if (maxProfit < (prices[i] - minElement)) {
				maxProfit = prices[i] - minElement;
			}
		}
		return maxProfit;
	}

	public int maxProfitWithMultipleBuyingSelling(int[] prices) {
		return maxProfitWithMultipleBuyingSellingHelper(prices, 0, prices.length - 1);
	}

	private int maxProfitWithMultipleBuyingSellingHelper(int[] prices, int start, int end) {
		System.out.println("(" + start + ", " + end + ")");
		if (end <= start) {
			return 0;
		}
		int profit = 0;
		for (int i = start; i < end; i++) {
			// calculating the profit as buying on Ith day and selling after that
			for (int j = i + 1; j <= end; j++) {
				int currentProfit = prices[j] - prices[i] + maxProfitWithMultipleBuyingSellingHelper(prices, j + 1, end)
						+ maxProfitWithMultipleBuyingSellingHelper(prices, start, i - 1);

				profit = Math.max(currentProfit, profit);
			}
		}

		return profit;
	}

	public int maxProfitWithMultipleBuyingSellingEfficient(int[] prices) {
		int result = 0;

		int i = 0;
		int n = prices.length;
		List<Range> list = new ArrayList<>();

		while (i < n - 1) {
			// Finding the local minima
			while (i < n - 1 && prices[i] >= prices[i + 1]) {
				i++;
			}
			// if we reached to end just break
			if (i == n - 1) {
				break;
			}

			Range range = new Range();
			range.setBuyingIndex(i++);

			// Finding local maxima
			while (i < n && prices[i] >= prices[i - 1]) {
				i++;
			}
			range.setSellingIndex(i - 1);
			list.add(range);
		}
		// calculating the result
		for (Range r : list) {
			result += (prices[r.getSellingIndex()] - prices[r.getBuyingIndex()]);
		}
		return result;
	}

	class Range {
		private int buyingIndex;
		private int sellingIndex;

		public int getBuyingIndex() {
			return buyingIndex;
		}

		public void setBuyingIndex(int buyingIndex) {
			this.buyingIndex = buyingIndex;
		}

		public int getSellingIndex() {
			return sellingIndex;
		}

		public void setSellingIndex(int sellingIndex) {
			this.sellingIndex = sellingIndex;
		}
	}

	/**
	 * Say you have an array for which the i-th element is the price of a given
	 * stock on day i. Design an algorithm to find the maximum profit. You may
	 * complete at most k transactions.
	 * 
	 * @param k
	 * @param prices
	 * @return
	 */
	public int maxProfitinKTransactions(int k, int[] prices) {
		if(prices.length == 0) {
			return 0;
		}
		// 2D array to store result
		int[][] profitArray = new int[k + 1][prices.length];

		// for 0th transaction there wouldn't be any profit

		for (int i = 0; i < profitArray[0].length; i++) {
			profitArray[0][i] = 0;
		}

		// for 0th day there wouldn't be any profit

		for (int i = 0; i < profitArray.length; i++) {
			profitArray[i][0] = 0;
		}

		for (int i = 1; i < profitArray.length; i++) {
			 
			int previousDifference = Integer.MIN_VALUE;
			for (int j = 1; j < profitArray[0].length; j++) {
				//if we don't do any transaction at jth day
//				int currentMax = profitArray[i][j - 1];
//				// if we want to sell at jth day
//				int localMax = 0;
//				for(int m = 0; m < j ; m++) {
//					localMax = Math.max(localMax, prices[j] - prices[m] + profitArray[i - 1][m]);
//				}
				previousDifference = Math.max(previousDifference, -prices[j - 1] + profitArray[i - 1][j - 1]);
				profitArray[i][j] = Math.max(profitArray[i][j - 1], prices[j] + previousDifference);
			}
		}
		return profitArray[k][prices.length - 1];
	}

	public static void main(String[] args) {
		BuyAndSellStock buyAndSellStock = new BuyAndSellStock();
		int[] input = { 12, 14, 17, 10, 14, 13, 12, 15 }; // 310, 40, 535, 695
		// System.out.println(buyAndSellStock.maxProfit(input));
		// System.out.println(buyAndSellStock.maxProfitEfficient(input));
		//System.out.println(buyAndSellStock.maxProfitWithMultipleBuyingSelling(input));
		System.out.println(buyAndSellStock.maxProfitinKTransactions(3,input));
		// System.out.println(buyAndSellStock.maxProfitWithMultipleBuyingSellingEfficient(input));
	}
}
