package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AlienLanguageProblems {

	Map<Character, List<Character>> graph = new HashMap<>();

	public void addEdge(char start, char end) {
		List<Character> adjList = null;
		if (graph.get(start) == null) {
			adjList = new ArrayList<>();
		} else {
			adjList = graph.get(start);
		}
		adjList.add(end);
		graph.put(start, adjList);
	}

	private List<Character> topolocialSort() {
		Map<Character, Boolean> visited = new HashMap<>();
		Stack<Character> stack = new Stack<>();
		for (Character ch : graph.keySet()) {
			if (visited.get(ch) == null) {
				sortUtil(ch, visited, stack);
			}
		}
		List<Character> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private void sortUtil(char vertex, Map<Character, Boolean> visited, Stack<Character> stack) {
		visited.put(vertex, true);
		if (graph.get(vertex) != null) {
			for (char ch : graph.get(vertex)) {
				if (visited.get(ch) == null) {
					sortUtil(ch, visited, stack);
				}
			}
		}
		stack.push(vertex);
	}

	public List<Character> findOrder(String[] words) {
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			int k = 0;
			while (k < word1.length() && k < word2.length()) {
				if (word1.charAt(k) != word2.charAt(k)) {
					addEdge(word1.charAt(k), word2.charAt(k));
					break;
				}
				k++;
			}
		}
		List<Character> result = topolocialSort();
		return result;
	}

	public boolean isAlienSorted(String[] words, String order) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < order.length(); i++) {
			map.put(order.charAt(i), i + 1);
		}
		boolean isSorted = true;
		for (int j = 0; j < words.length - 1; j++) {
			String word1 = words[j];
			String word2 = words[j + 1];
			int k = 0;
			while (k < word1.length() && k < word2.length()) {
				if (word1.charAt(k) == word2.charAt(k)) {
					k++;
				} else if ((word1.charAt(k) != word2.charAt(k))
						&& map.get(word1.charAt(k)) < map.get(word2.charAt(k))) {
					isSorted = true;
					break;
				} else {
					isSorted = false;
					return isSorted;
				}
				isSorted = (word1.length() < word2.length());
			}
		}
		return isSorted;
	}

	public static void main(String[] args) {
		AlienLanguageProblems al = new AlienLanguageProblems();
		String[] words = { "caa", "aaa", "aab" };
		System.out.println(al.findOrder(words));
		String[] words1 = { "app", "apple" };
		System.out.println(al.isAlienSorted(words1, "abcdefghijklmnopqrstuvwxyz"));
	}
}
