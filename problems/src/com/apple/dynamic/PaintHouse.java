package com.apple.dynamic;

public class PaintHouse {
	public int minCost(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		return Math.min(minCostHelper(costs, 0, 0), Math.min(minCostHelper(costs, 0, 1), minCostHelper(costs, 0, 2)));
	}

	private int minCostHelper(int[][] costs, int houseNo, int color) {
		int totalCost = costs[houseNo][color];
		if (houseNo == costs.length - 1) {
			return totalCost;
		}
		if (color == 0) {
			totalCost += Math.min(minCostHelper(costs, houseNo + 1, 1), minCostHelper(costs, houseNo + 1, 2));
		} else if (color == 1) {
			totalCost += Math.min(minCostHelper(costs, houseNo + 1, 0), minCostHelper(costs, houseNo + 1, 2));
		} else {
			totalCost += Math.min(minCostHelper(costs, houseNo + 1, 0), minCostHelper(costs, houseNo + 1, 1));
		}
		return totalCost;
	}

	public int minCostHelper(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		for (int i = 1; i < costs.length; i++) {
			// if ith house is painted with red
			costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
			// if ith house is painted with green
			costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
			// if ith house is painted with blue
			costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
		}

		return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
	}
}
