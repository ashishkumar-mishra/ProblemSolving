package com.algo.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKWords {
	
	// using sorting
	public List<String> topKWords(String[] words, int k){
		Map<String, Integer> map = new HashMap<>();
		
		for(String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		List<String> wordList = new ArrayList<>(map.keySet());
		Collections.sort(wordList,(w1,w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1));
		//System.out.println(wordList);
		return wordList.subList(0, k);
	}
	
	public static void main(String[] args) {
		TopKWords topKWords = new TopKWords();
		String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
		System.out.println(topKWords.topKWords(words, 2));
	}
}
