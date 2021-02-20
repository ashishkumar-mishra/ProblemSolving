package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraphBFS {

	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}
		Map<Integer, Node> visited = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		visited.put(node.val, new Node(node.val, new ArrayList<>()));
		while (queue.size() > 0) {
			Node n = queue.poll();
			for (Node neigh : n.neighbors) {
				if (!visited.containsKey(neigh.val)) {
					visited.put(neigh.val, new Node(neigh.val, new ArrayList<>()));
					queue.add(neigh);
				}
				visited.get(n.val).neighbors.add(visited.get(neigh.val));
			}
		}
		return visited.get(node.val);
	}

	class Node {
	    public int val;
	    public List<Node> neighbors;
	    public Node() {
	        this.val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    public Node(int val) {
	        this.val = val;
	        this.neighbors = new ArrayList<Node>();
	    }
	    public Node(int val, ArrayList<Node> neighbors) {
	        this.val = val;
	        this.neighbors = neighbors;
	    }
	}
}
