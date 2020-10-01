package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPathAlgorithm {

	public static void main(String args[]) {
		ShortestPathAlgorithm algo = new ShortestPathAlgorithm();
		List<WeightedEdge> edges = new ArrayList<>();
		List<Integer> vetices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			vetices.add(i);
		}
		WeightedEdge e1 = new WeightedEdge(0, 1, 5);
		WeightedEdge e2 = new WeightedEdge(0, 2, 5);
		WeightedEdge e3 = new WeightedEdge(1, 3, 5);
		WeightedEdge e4 = new WeightedEdge(2, 4, 15);
		WeightedEdge e5 = new WeightedEdge(3, 4, 5);
		WeightedEdge e6 = new WeightedEdge(2, 3,2);
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		Graph graph = new Graph(edges, vetices);
        int graph1[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
        System.out.println(algo.getShortestPathForAdjMatrixGraph(graph1, 0));
		System.out.println(algo.getShortestPaths(graph, 0));
	}

	public Map<Integer, Integer> getShortestPaths(final Graph graph, int source) {
		boolean[] visited = new boolean[graph.vertices.size()];
		Map<Integer, Integer> distances = new HashMap<>();
		Map<Integer, List<WeightedEdge>> graphMap = new HashMap<>();
		// creating map from graph
		for (WeightedEdge edge : graph.edges) {
			if (graphMap.get(edge.getSource()) != null) {
				List<WeightedEdge> edgeList = graphMap.get(edge.getSource());
				edgeList.add(edge);
			} else {
				List<WeightedEdge> list = new ArrayList<>();
				list.add(edge);
				graphMap.put(edge.getSource(), list);
			}
		}
		for (int node = 0; node < graph.vertices.size(); node++) {
			distances.put(node, Integer.MAX_VALUE);
		}
		// print graph map
		printGrapMap(graphMap);
		distances.put(source, 0);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		while (queue.size() > 0) {
			int src = queue.poll();
			visited[src] = true;
			List<WeightedEdge> edges = graphMap.get(src);
			if (edges != null) {
				for (WeightedEdge edge : edges) {
					if (!visited[edge.getDestination()]) {
						distances.put(edge.getDestination(), Math.min(distances.get(edge.getDestination()),
								distances.get(src) + edge.getDistance()));
						queue.add(edge.getDestination());
					}
				}
			}
		}

		return distances;
	}
	
	public Map<Integer,Integer> getShortestPathForAdjMatrixGraph(int[][] graph, int source) {
		Map<Integer, Integer> distances = new HashMap<>();
		boolean[] visited = new boolean[graph.length];
		for (int node = 0; node < graph.length; node++) {
			distances.put(node, Integer.MAX_VALUE);
		}
		distances.put(source, 0);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		
		while(queue.size() > 0) {
			int src = queue.poll();
			visited[src] = true;
			for(int i = 0; i < graph.length; i++) {
				if(graph[src][i] > 0) {
					if(!visited[i]) {
						distances.put(i,Math.min(distances.get(i), distances.get(src) + graph[src][i]));
						queue.add(i);
					}
				}
			}
		}
		return distances;
	}

	private void printGrapMap(Map<Integer, List<WeightedEdge>> map) {
		map.forEach((k, v) -> {
			v.forEach(e -> {
				System.out.print(k + " " + e.getSource() + " " + e.getDestination());
				System.out.println();
			});
		});
	}
}

class Graph {
	List<WeightedEdge> edges;
	List<Integer> vertices;

	Graph(List<WeightedEdge> edges, List<Integer> vertices) {
		this.edges = edges;
		this.vertices = vertices;
	}
}

class WeightedEdge {
	private int source;
	private int destination;
	private int distance;

	WeightedEdge(int source, int destination, int distance) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
