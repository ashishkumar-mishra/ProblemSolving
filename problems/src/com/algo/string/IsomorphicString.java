package com.algo.string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {
	// using map
	public boolean isIsomorphic(String str1, String str2) {
		Map<Character, Character> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			if (map.containsKey(str1.charAt(i))) {
				if (map.get(str1.charAt(i)) != str2.charAt(i)) {
					return false;
				}
			} else {
				if (map.containsValue(str2.charAt(i))) {
					return false;
				}
				map.put(str1.charAt(i), str2.charAt(i));
			}
		}
		return true;
	}

	// using boolean array
	public boolean isIsomorphicEfficient(String str1, String str2) {
		Map<Character, Character> map = new HashMap<>();
		boolean[] visited = new boolean[256];
		for (int i = 0; i < str1.length(); i++) {
			if (!map.containsKey(str1.charAt(i))) {
				if (visited[str2.charAt(i)]) {
					return false;
				}
				map.put(str1.charAt(i), str2.charAt(i));
				visited[str2.charAt(i)] = true;
			} else if (map.get(str1.charAt(i)) != str2.charAt(i)) {
				return false;
			}
		}
		return str1.length() == str2.length();
	}

	public static void main(String[] args) {
		IsomorphicString isomorphic = new IsomorphicString();
		System.out.println(isomorphic.isIsomorphic("foo", "bar"));
		System.out.println(isomorphic.isIsomorphicEfficient("paper", "title"));
	}
}
