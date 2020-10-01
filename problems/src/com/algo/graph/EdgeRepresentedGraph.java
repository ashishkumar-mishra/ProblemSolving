package com.algo.graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeRepresentedGraph {
	List<Edge> edges;
	List<Integer> vertes;

	EdgeRepresentedGraph(List<Edge> edges, List<Integer> vertes) {
		this.edges = edges;
		this.vertes = vertes;
	}

	public boolean isCyclic(final EdgeRepresentedGraph graph) {
		int[] parent = new int[graph.vertes.size()];
		for (int i = 0; i < graph.vertes.size(); i++) {
			parent[i] = -1;
		}

		for (Edge e : graph.edges) {
			int xParent = find(parent, e.getSource());
			int yParent = find(parent, e.getDestination());
			if (xParent == yParent) {
				return true;
			}
			union(parent, e.getSource(), e.getDestination());
		}
		return false;
	}

	public boolean isCyclicPathCompression(final EdgeRepresentedGraph graph) {
		Subset[] subsets = new Subset[graph.vertes.size()];
		for (int i = 0; i < graph.vertes.size(); i++) {
			subsets[i] = new Subset(0,i);
		}

		for (Edge e : graph.edges) {
			int xParent = findByPathCompression(subsets, e.getSource());
			int yParent = findByPathCompression(subsets, e.getDestination());
			if (xParent == yParent) {
				return true;
			}
			unionByRank(subsets, e.getSource(), e.getDestination());
		}
		return false;
	}

	public int find(int[] parent, int node) {
		if (parent[node] == -1) {
			return node;
		}
		return find(parent, parent[node]);
	}

	public void union(int[] parent, int source, int dest) {
		int x = find(parent, source);
		int y = find(parent, dest);
		parent[y] = x;
	}

	public void unionByRank(Subset[] subsets, int source, int dest) {
		int x = findByPathCompression(subsets, source);
		int y = findByPathCompression(subsets, dest);
		if (subsets[x].rank < subsets[y].rank) {
			subsets[x].parent = y;
			subsets[x].rank++;
		} else if (subsets[x].rank > subsets[y].rank) {
			subsets[y].parent = x;
			subsets[y].rank++;
		} else {
			subsets[x].parent = y;
			subsets[x].rank++;
		}
	}

	public int findByPathCompression(Subset[] subsets, int node) {
		if (subsets[node].parent != node) {
			subsets[node].parent = findByPathCompression(subsets, subsets[node].parent);
		}
		return subsets[node].parent;
	}

	public static void main(String[] args) {
		List<Integer> vertes = new ArrayList<>();
		vertes.add(0);
		vertes.add(1);
		vertes.add(2);
		vertes.add(3);
		List<Edge> edges = new ArrayList<>();
		Edge e1 = new Edge(0, 1);
		Edge e2 = new Edge(1, 2);
		Edge e3 = new Edge(2, 3);
		Edge e4 = new Edge(3, 1);
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		EdgeRepresentedGraph graph = new EdgeRepresentedGraph(edges, vertes);
		System.out.println(graph.isCyclic(graph));
		System.out.println("path compression = " + graph.isCyclicPathCompression(graph));
	}
}

class Edge {
	private int source;
	private int destination;

	Edge(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}
}

class Subset {
	int rank;
	int parent;

	Subset(int rank, int parent) {
		this.rank = rank;
		this.parent = parent;
	}
}