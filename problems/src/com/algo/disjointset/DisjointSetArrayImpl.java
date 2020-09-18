package com.algo.disjointset;

// array implementation of disjoint set
public class DisjointSetArrayImpl {

	int[] parents;
	int[] ranks;

	DisjointSetArrayImpl(int n) {
		this.parents = new int[n];
		this.ranks = new int[n];
		for(int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	public int find(int num) {
		if (parents[num] == num) {
			return num;
		} else {
			int res = find(parents[num]);
			parents[num] = res;
			return res;
		}
	}

	public void union(int num1, int num2) {
		int p1 = find(num1);
		int p2 = find(num2);

		if (p1 == p2) {
			return;
		}

		if (ranks[p1] > ranks[p2]) {
			// moving under parent1
			parents[p2] = p1;
		} else if (ranks[p1] < ranks[p2]) {
			parents[p1] = p2;
		} else {
			parents[p1] = p2;
			ranks[p2]++;
		}
	}
	
	public static void main(String[] args) {
		DisjointSetArrayImpl disjointSetArrayImpl = new DisjointSetArrayImpl(5);
		disjointSetArrayImpl.union(0,2);
		disjointSetArrayImpl.union(4,2);
		disjointSetArrayImpl.union(3,1);
		System.out.println(disjointSetArrayImpl.find(0));
		System.out.println(disjointSetArrayImpl.find(4));
		System.out.println(disjointSetArrayImpl.find(1));
	}
}
