package com.algo.leet;

import java.util.Hashtable;

public class LRUCache {

	class Node {
		int key;
		int value;
		Node previous;
		Node next;
	}
	
	//Defining variables
	private Node head;
	private Node tail;
	private Hashtable<Integer,Node> cache = new Hashtable<>();
	int count;
	int capacity;
	
	//Defining public constructor
	LRUCache(int capacity){
		this.capacity = capacity;
		this.count = 0;
		this.head = new Node();
		this.tail = new Node();
		head.next = tail;
		tail.previous = head;
	}
	
	
	//adding a Node to linked list
	private void addNode(Node node) {
		//there are four steps for adding node
		node.previous = head;
		node.next = head.next;
		head.next.previous = node;
		head.next = node;
	}
	
	// removing an existing node from linked list
	private void removeNode(Node node) {
		node.previous.next = node.next;
		node.next.previous = node.previous;
		
	}
	
	private void addToHead(Node node) {
		this.removeNode(node);
		this.addNode(node);
	}
	
	private Node getTail() {
		Node node = tail.previous;
		this.removeNode(node);
		return node;
	}
	public int get(int key) {
		Node node = this.cache.get(key);
		if(node == null) {
			return -1;
		}
		addToHead(node);
		return node.value;
	}
	
	public void put(int key, int value) {
		// get the node from cache
		Node node = cache.get(key);
		
		//create a new node when key is not present in cache
		if(node == null) {
			node = new Node();
			node.key = key;
			node.value = value;
		}
		if(count > capacity) {
			// remove the last node
			Node tail = this.getTail();
			cache.remove(tail.key);
			cache.put(key, node);
		} else {
			cache.put(key, node);
		}
		addNode(node);
	}
}
