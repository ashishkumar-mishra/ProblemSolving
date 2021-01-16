package com.algo.backtracking;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/combination-sum/
public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		combinationSumHelper(new ArrayList<>(), candidates, target, result, candidates.length - 1);
		return result;
	}

	private void combinationSumHelper(List<Integer> list, int[] candidates, int target, List<List<Integer>> result,
			int index) {
		if (target == 0) {
			result.add(list);
			return;
		}
		if (target < 0 || index < 0) {
			return;
		}
		// including coin at index
		List<Integer> l = new ArrayList<>(list);
		l.add(candidates[index]);
		combinationSumHelper(l, candidates, target - candidates[index], result, index);
		// not including coin at index
		combinationSumHelper(new ArrayList<>(list), candidates, target, result, index - 1);
	}
	
	public static void main(String[] args) {
		CombinationSum comb = new CombinationSum();
		int[] candidates = {2,3,6,7};
		List<List<Integer>> result = comb.combinationSum(candidates, 7);
		System.out.println(result);
	}
}
