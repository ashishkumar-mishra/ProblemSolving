package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

	Map<Integer, List<Integer>> graph = null;

	TopologicalSort() {
		graph = new HashMap<>();
	}

	public void addEdge(int start, int end) {
		List<Integer> adj = null;
		if (graph.get(start) == null) {
			adj = new ArrayList<>();
		} else {
			adj = graph.get(start);
		}
		adj.add(end);
		graph.put(start, adj);
	}

	public List<Integer> topologicalSorting() {
		Set<Integer> keySet = graph.keySet();
		Map<Integer, Boolean> visted = new HashMap<>();
		Stack<Integer> resultStack = new Stack<>();

		for (Integer key : keySet) {
			if (visted.get(key) == null) {
				sortUtil(key, visted, resultStack);
			}
		}
		List<Integer> result = new ArrayList<>();
		while (!resultStack.isEmpty()) {
			result.add(resultStack.pop());
		}
		return result;
	}

	private void sortUtil(int vertex, Map<Integer, Boolean> visted, Stack<Integer> resultStack) {
		visted.put(vertex, true);
		if (graph.get(vertex) != null) {
			Iterator<Integer> itr = graph.get(vertex).iterator();
			while (itr.hasNext()) {
				int v = itr.next();
				if (visted.get(v) == null) {
					sortUtil(v, visted, resultStack);
				}
			}
		}
		resultStack.push(vertex);
	}
	
	// also working : simple for loop
	public void sortUtil1(int vertex, Map<Integer, Boolean> visted, Stack<Integer> resultStack) {
		visted.put(vertex, true);
		if (graph.get(vertex) != null) {
			for(int v : graph.get(vertex)) {
				if (visted.get(v) == null) {
					sortUtil1(v, visted, resultStack);
				}
			}
		}
		resultStack.push(vertex);
	}

	public static void main(String[] args) {
		TopologicalSort topologicalSort = new TopologicalSort();
		topologicalSort.addEdge(5, 2);
		topologicalSort.addEdge(5, 0);
		topologicalSort.addEdge(4, 0);
		topologicalSort.addEdge(4, 1);
		topologicalSort.addEdge(2, 3);
		topologicalSort.addEdge(3, 1);
		System.out.println(topologicalSort.topologicalSorting());
	}
}
