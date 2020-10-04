package com.algo.trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
	Map<Character, TrieNode> children = new HashMap<>();
	boolean isWord = false;
}

public class WordDictionary {

	TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	// method to add word in trie

	public void addWord(String word) {
		TrieNode node = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (node.children.get(ch) == null) {
				node.children.put(ch, new TrieNode());
			}
			node = node.children.get(ch);
		}
		node.isWord = true;
	}

	public boolean search(String word) {
		return searchHelper(word, root);
	}

	private boolean searchHelper(String word, TrieNode node) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (node.children.get(ch) == null) {
				if (ch == '.') {
					for (char key : node.children.keySet()) {
						TrieNode childNode = node.children.get(key);
						if (searchHelper(word.substring(i + 1), childNode)) {
							return true;
						}
					}
				}
				return false;
			} else {
				node = node.children.get(ch);
			}
		}
		return node.isWord;
	}
	
	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("a");
		wordDictionary.addWord("a");
		System.out.println(wordDictionary.search("aa"));
	}

}
