package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class CourseSchedule {

	Map<Integer, List<Integer>> map = null;

	CourseSchedule() {
		map = new HashMap<>();
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (prerequisites.length == 0) {
			int[] r = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				r[i] = i;
			}
			return r;
		}
		buildGraph(prerequisites);
		boolean isCyclic = isCyclic();
		if (isCyclic) {
			return new int[0];
		}
		return topologicalSort(numCourses);
	}

	private int[] topologicalSort(int numCourses) {
		Stack<Integer> stack = new Stack<>();
		Set<Integer> visited = new HashSet<>();
		for (int vertex = 0; vertex < numCourses; vertex++) {
			if (!visited.contains(vertex)) {
				util(vertex, stack, visited);
			}
		}
		int[] result = new int[stack.size()];
		int start = 0;
		while (!stack.isEmpty()) {
			result[start++] = stack.pop();
		}
		return result;
	}

	private void util(int vertex, Stack<Integer> stack, Set<Integer> visited) {
		visited.add(vertex);
		if (map.get(vertex) != null) {
			for (int child : map.get(vertex)) {
				if (!visited.contains(child)) {
					util(child, stack, visited);
				}
			}
		}
		stack.push(vertex);
	}

	private void buildGraph(int[][] prerequisites) {
		for (int[] a : prerequisites) {
			map.computeIfAbsent(a[1], x -> new ArrayList<>()).add(a[0]);
		}
	}

	private boolean isCyclic() {
		Set<Integer> visited = new HashSet<>();
		Set<Integer> seen = new HashSet<>();
		for (int vertex : map.keySet()) {
			if (!visited.contains(vertex)) {
				if (cyclicUtil(vertex, visited, seen)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean cyclicUtil(int vertex, Set<Integer> visited, Set<Integer> seen) {
		if (seen.contains(vertex)) {
			return true;
		}
		if(visited.contains(vertex)) {
			return false;
		}
		seen.add(vertex);
		visited.add(vertex);
		if (map.get(vertex) != null) {
			for (int child : map.get(vertex)) {
				if (cyclicUtil(child, visited, seen)) {
					return true;
				}
			}
		}
		seen.remove(vertex);
		return false;
	}

	public static void main(String[] args) {
		// int[][] courses = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		int[][] courses = { { 1, 0 } };
		CourseSchedule c = new CourseSchedule();
		int[] result = c.findOrder(3, courses);
		for (int a : result) {
			System.out.print(a + " ");
		}
	}

}
