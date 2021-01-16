package com.algo.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SentenceSimilarity {

	public static void main(String[] args) {

		String[] words1 = { "great", "acting", "skills" };
		String[] words2 = { "fine", "drama", "talent" };
		List<List<String>> pairs = new ArrayList<>();
		List<String> l1 = new ArrayList<>();
		l1.add("great");
		l1.add("fine");

		List<String> l2 = new ArrayList<>();
		l2.add("acting");
		l2.add("drama");

		List<String> l3 = new ArrayList<>();
		l3.add("skills");
		l3.add("talent");
		pairs.add(l1);
		pairs.add(l2);
		pairs.add(l3);
		SentenceSimilarity sim = new SentenceSimilarity();
		System.out.println(sim.areSentenceSimilarTwo(words1, words2, pairs));

	}

	public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
		boolean sentenceSimilar = true;
		Set<String> set = new HashSet<>();
		if (words1.length != words2.length) {
			return false;
		}
		for (List<String> list : pairs) {
			set.add(list.get(0) + " " + list.get(1));
			set.add(list.get(1) + " " + list.get(0));
		}

		for (int i = 0; i < words1.length; i++) {
			if (!set.contains(words1[i] + " " + words2[i]) && !words1[i].equals(words2[i])) {
				return false;
			}
		}
		return sentenceSimilar;
	}

	/**
	 * Given two sentences words1, words2 (each represented as an array of strings),
	 * and a list of similar word pairs pairs, determine if two sentences are
	 * similar. For example, words1 = ["great", "acting", "skills"] and words2 =
	 * ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs
	 * = [["great", "good"], ["fine", "good"], ["acting","drama"],
	 * ["skills","talent"]].
	 * 
	 * Note that the similarity relation is transitive. For example, if "great" and
	 * "good" are similar, and "fine" and "good" are similar, then "great" and
	 * "fine" are similar.
	 * 
	 * Similarity is also symmetric. For example, "great" and "fine" being similar
	 * is the same as "fine" and "great" being similar.
	 * 
	 * Also, a word is always similar with itself. For example, the sentences words1
	 * = ["great"], words2 = ["great"], pairs = [] are similar, even though there
	 * are no specified similar word pairs.
	 * 
	 * Finally, sentences can only be similar if they have the same number of words.
	 * So a sentence like words1 = ["great"] can never be similar to words2 =
	 * ["doubleplus","good"].
	 * 
	 */
	
	public boolean areSentenceSimilarTwo(String[] words1,String[] words2, List<List<String>> pairs) {
		if(words1.length != words2.length) {
			return false;
		}
		Map<String,List<String>> graph = new HashMap<>();
		for(List<String> list : pairs) {
			addEdge(list.get(0),list.get(1),graph);
			addEdge(list.get(1),list.get(0),graph);
			//below lines can be used for same things
			//graph.computeIfAbsent(list.get(0), x -> new ArrayList<>()).add(list.get(1));
			//graph.computeIfAbsent(list.get(1), x -> new ArrayList<>()).add(list.get(0));
		}
		for(int i = 0; i < words1.length; i++) {
			String word1 = words1[i];
			String word2 = words2[i];
			Stack<String> stack = new Stack<>();
			Set<String> seen = new HashSet<>();
			boolean wordMatched = false;
			stack.add(word1);
			seen.add(word1);
			while(!stack.isEmpty()) {
				String top = stack.pop();
				if(top.equals(word2)) {
					wordMatched = true;
					break;
				}
				if(graph.containsKey(top)) {
					for(String str : graph.get(top)) {
						if(!seen.contains(str)) {
							stack.push(str);
							seen.add(str);
						}
					}
				}	
			}
			if(!wordMatched) {
				return false;
			}
		}
		return true;	
	}

	private void addEdge(String source, String destination, Map<String, List<String>> graph) {
		if (graph.get(source) == null) {
			createVertex(source, graph);
		}

		if (graph.get(destination) == null) {
			createVertex(destination, graph);
		}
		graph.get(source).add(destination);
		graph.get(destination).add(source);
	}

	private void createVertex(String vertex, Map<String, List<String>> graph) {
		if (graph.get(vertex) == null) {
			graph.put(vertex, new ArrayList<>());
		}
	}
}
