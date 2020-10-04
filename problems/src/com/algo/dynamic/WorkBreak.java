package com.algo.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkBreak {

	public boolean wordBreak(String str, List<String> dict) {
		Set<String> hashSet = new HashSet<>(dict);
		return wordBreakHelper(str, hashSet);
	}

	private boolean wordBreakHelper(String str, Set<String> dict) {
		// base case
		if (str.length() == 0) {
			return true;
		}

		for (int i = 0; i <= str.length(); i++) {
			if (dict.contains(str.substring(0, i)) && wordBreakHelper(str.substring(i, str.length()), dict)) {
				return true;
			}
		}

		return false;
	}

	public boolean wordBreakMemoization(String str, List<String> dict) {
		Set<String> hashSet = new HashSet<>(dict);
		Map<String, Boolean> memo = new HashMap<>();
		boolean result = wordBreakHelperMomoization(str, hashSet, memo);
		return result;
	}

	private boolean wordBreakHelperMomoization(String str, Set<String> dict, Map<String, Boolean> memo) {
		// base condition

		if (str.length() == 0) {
			return true;
		}
		if (memo.get(str) != null) {
			return memo.get(str);
		}

		for (int i = 0; i <= str.length(); i++) {
			if (dict.contains(str.substring(0, i))
					&& wordBreakHelperMomoization(str.substring(i, str.length()), dict, memo)) {
				memo.put(str, true);
				return true;
			}
		}
		return false;
	}

	public boolean wordBreakDynamic(String str, List<String> dict) {
		Set<String> set = new HashSet<String>(dict);
		boolean[] resultArray = new boolean[str.length() + 1];
		resultArray[0] = true;
		for (int i = 1; i <= str.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (resultArray[j] && set.contains(str.substring(j, i))) {
					resultArray[i] = true;
					break;
				}
			}
		}
		return resultArray[str.length()];
	}

	public List<String> wordBreakPossibleCombination(String str, List<String> dict) {
		Set<String> set = new HashSet<String>(dict);
		List<String> list = new ArrayList<>();
		wordBreakPossibleCombinationHelper(str, "", set, list);
		return list;
	}

	private void wordBreakPossibleCombinationHelper(String str, String res, Set<String> dict, List<String> result) {
		// base case
		if (str.isEmpty()) {
			result.add(res.trim());
			return;
		}
		for (int i = 0; i <= str.length(); i++) {
			if (dict.contains(str.substring(0, i))) {
				wordBreakPossibleCombinationHelper(str.substring(i, str.length()), res + str.substring(0, i) + " ",
						dict, result);
			}
		}
	}

	public List<String> wordBreakPossibleCombinationMemo(String str, List<String> dict) {
		Set<String> set = new HashSet<String>(dict);
		Map<String, List<String>> memo = new HashMap<>();
		wordBreakPossibleCombinationMemoHelper(str, set, memo);
		System.out.println(memo);
		return memo.get(str);
	}

	private List<String> wordBreakPossibleCombinationMemoHelper(String str, Set<String> dict,
			Map<String, List<String>> memo) {
		if (str.isEmpty()) {
			return new ArrayList<>();
		}

		if (memo.containsKey(str)) {
			return memo.get(str);
		} else {
			memo.put(str, new ArrayList<>());
		}

		for (int i = 0; i <= str.length(); i++) {
			if (dict.contains(str.substring(0, i))) {
				List<String> tmp = wordBreakPossibleCombinationMemoHelper(str.substring(i, str.length()), dict, memo);
				List<String> res = new ArrayList<>();
				if(tmp.isEmpty()) {
					res.add(str.substring(0, i));
				} else {
					for(String a : tmp) {
						res.add(str.substring(0, i) + " " + a);
					}
				}
				 memo.get(str).addAll(res);
			}
		}
		return memo.get(str);
	}

	public static void main(String[] args) {
		WorkBreak workBreak = new WorkBreak();
		List<String> list = new ArrayList<>();
		list.add("cat");
		list.add("cats");
		list.add("and");
		list.add("sand");
		list.add("dog");
		// System.out.println(workBreak.wordBreak("abc", list));
		System.out.println(workBreak.wordBreakMemoization("catsanddog", list));
		// System.out.println(workBreak.wordBreakDynamic("abc", list));
		System.out.println(workBreak.wordBreakPossibleCombination("catsanddog", list));
		System.out.println(workBreak.wordBreakPossibleCombinationMemo("catsanddog", list));
		// System.out.println(workBreak.wordBreakPossibleCombinationMemoization("catsand",
		// list));
	}
}
