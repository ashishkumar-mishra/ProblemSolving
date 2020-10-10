package com.algo.disjointset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMergeUnionFind {
	// this method is for merging account using union find
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, Integer> emailtoid = new HashMap<>();
		Map<String, String> emailtoname = new HashMap<>();
		List<List<String>> result = new ArrayList<>();
		UnionFind unionFind = new UnionFind();
		int startID = 0;
		for (int i = 0; i < accounts.size(); i++) {
			String name = accounts.get(i).get(0);
			String firstEmail = accounts.get(i).get(1);
			for (int j = 1; j < accounts.get(i).size(); j++) {
				String email = accounts.get(i).get(j);
				emailtoname.put(email, name);
				if (!emailtoid.containsKey(email)) {
					emailtoid.put(email, startID++);
				}
				unionFind.union(emailtoid.get(firstEmail), emailtoid.get(email));
			}
		}
		Map<Integer, List<String>> map = new HashMap<>();
		for (String key : emailtoname.keySet()) {
			int idx = unionFind.find(emailtoid.get(key));
			map.computeIfAbsent(idx, x -> new ArrayList<>()).add(key);
		}
		// Now computing result
		for (List<String> list : map.values()) {
			Collections.sort(list);
			list.add(0, emailtoname.get(list.get(0)));
			result.add(list);
		}
		return result;
	}

	class UnionFind {
		int[] parent;

		UnionFind() {
			parent = new int[10000];
			for (int i = 0; i < 10000; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}
	
	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<>();
		String[] a1 = {"John", "johnsmith@mail.com", "john00@mail.com"};
		String[] a2 = {"John", "johnnybravo@mail.com"};
		String[] a3 = {"John", "johnsmith@mail.com", "john_newyork@mail.com"};
		String[] a4 = {"Mary", "mary@mail.com"};
		accounts.add(Arrays.asList(a1));
		accounts.add(Arrays.asList(a2));
		accounts.add(Arrays.asList(a3));
		accounts.add(Arrays.asList(a4));
		AccountMergeUnionFind accountmerge = new AccountMergeUnionFind();
		System.out.println(accountmerge.accountsMerge(accounts));
	}
}
