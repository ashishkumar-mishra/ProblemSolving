package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraph {
	Map<Character,List<Character>> graph = new HashMap<>();
	
	public void addEdge(Character source, Character destination) {
		if(source == null || destination == null) {
			System.out.println("Souce or destination can't be empty");
			return;
		}
		
		if(graph.get(source) == null) {
			createVertex(source);
		}
		if(graph.get(destination) == null) {
			createVertex(destination);
		}
		graph.get(source).add(destination);
		graph.get(destination).add(source);
	}
	
	private void createVertex(Character vertex) {
		if(vertex != null) {
			graph.put(vertex, new ArrayList<>());
		}
	}
	
	public int findDegree(Character source, Character destination) {
		// degree can be find using BFS algorithm
		boolean[] visited = new boolean[256];
		int[] distance = new int[256];
		// creating the queue for BFS
		Queue<Character> queue = new LinkedList<>();
		visited[source] = true;
		queue.add(source);
		
		while(queue.size() != 0) {
			Character ch = queue.poll();
			Iterator<Character> iterator = graph.get(ch).listIterator();
			while(iterator.hasNext()) {
				Character next = iterator.next();
				if(!visited[next]) {
					distance[next] = distance[ch] + 1;
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		
		return distance[destination];
	}
	
	public static void main(String[] args) {
		UndirectedGraph undirectedGraph = new UndirectedGraph();
		undirectedGraph.addEdge('A', 'B');
		undirectedGraph.addEdge('B', 'C');
		undirectedGraph.addEdge('C', 'D');
		undirectedGraph.addEdge('A', 'E');
		undirectedGraph.addEdge('A', 'F');
		System.out.println(undirectedGraph.graph);
		System.out.println(undirectedGraph.findDegree('A', 'C'));
	}
}
