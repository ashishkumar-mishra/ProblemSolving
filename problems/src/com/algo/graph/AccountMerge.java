package com.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AccountMerge {

	// solving problem using DFS : equivalent to find connected components
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String,Set<String>> graph = new HashMap<>();
        Map<String,String> nameemailmap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
       
       for(int i = 0; i<accounts.size(); i++){
           
           String userName = accounts.get(i).get(0);
           String firstEmail = accounts.get(i).get(1);
           
           for(int j=1;j<accounts.get(i).size();j++){
               String email = accounts.get(i).get(j);
               nameemailmap.put(email, userName);
               addEdge(firstEmail,email,graph);
               addEdge(email,firstEmail,graph);
           }
       }
		// doing DFS to find connected components
		Set<String> visited = new HashSet<>();
		Set<String> keys = graph.keySet();

		for (String key : keys) {
			List<String> list = new ArrayList<>();
			if (!visited.contains(key)) {
				search(key, visited, graph, list);
			}

			if (list.size() > 0) {
				List<String> tmp = new ArrayList<>();
				Collections.sort(list);
				tmp.add(nameemailmap.get(key));
				tmp.addAll(list);
				result.add(tmp);
			}
		}
		return result;
	}

	private void search(String source, Set<String> visited, Map<String, Set<String>> graph, List<String> res) {
		Stack<String> stack = new Stack<>();
		visited.add(source);
		stack.push(source);
		while (!stack.isEmpty()) {
			String node = stack.pop();
			res.add(node);
			Set<String> adj = graph.get(node);
			for (String str : adj) {
				if (!visited.contains(str)) {
					visited.add(str);
					stack.push(str);
				}
			}
		}
	}

	private void addEdge(String fromEmail, String toEmail, Map<String, Set<String>> graph) {
		if (graph.get(fromEmail) == null) {
			graph.put(fromEmail, new HashSet<>());
		}
		graph.get(fromEmail).add(toEmail);
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
		AccountMerge accountmerge = new AccountMerge();
		System.out.println(accountmerge.accountsMerge(accounts));
	}

}
