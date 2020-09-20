package com.algo.disjointset;

import java.util.HashMap;
import java.util.Map;

// Tree implementation of disjoint set
public class DisjointSetTreeImpl {

	Map<Integer, Node> map = new HashMap<>();

	public void createNode(int data) {
		Node node = new Node();
		node.data = data;
		node.rank = 0;
		node.parent = node;
		map.put(data, node);
	}

	// union by rank
	public void union(int data1, int data2) {
		Node parent1 = map.get(data1);
		Node parent2 = map.get(data2);;
		
		//if both the node belongs to same set
		if(parent1.data == parent2.data) {
			return;
		}
		
		if(parent1.rank > parent2.rank) {
			parent2.parent = parent1;
		} else if(parent1.rank < parent2.rank) {
			parent1.parent = parent2;
		} else {
			parent1.parent = parent2;
			parent2.rank++;
		}
	}
	
	// find by path compression
	public int find(int data) {
		return findHelper(map.get(data)).data;
	}
	
	private Node findHelper(Node node) {
		if(node.parent == node) {
			return node;
		}
		Node tmpNode = findHelper(node.parent);
		node.parent = tmpNode;
		return tmpNode;
	}

	class Node {
		int data;
		int rank;
		Node parent;
	}
	
	public static void main(String[] args) {
		DisjointSetTreeImpl disjointSetTreeImpl = new DisjointSetTreeImpl();
		disjointSetTreeImpl.createNode(1);
		disjointSetTreeImpl.createNode(2);
		disjointSetTreeImpl.createNode(3);
		disjointSetTreeImpl.createNode(4);
		disjointSetTreeImpl.createNode(5);
		disjointSetTreeImpl.createNode(6);
		disjointSetTreeImpl.createNode(7);
		
		disjointSetTreeImpl.union(1, 2);
		disjointSetTreeImpl.union(2, 3);
		disjointSetTreeImpl.union(4, 5);
		disjointSetTreeImpl.union(6, 7);
		disjointSetTreeImpl.union(5, 6);
		disjointSetTreeImpl.union(3, 7);

        System.out.println(disjointSetTreeImpl.find(1));
        System.out.println(disjointSetTreeImpl.find(2));
        System.out.println(disjointSetTreeImpl.find(3));
        System.out.println(disjointSetTreeImpl.find(4));
        System.out.println(disjointSetTreeImpl.find(5));
        System.out.println(disjointSetTreeImpl.find(6));
        System.out.println(disjointSetTreeImpl.find(7));
	}
}
