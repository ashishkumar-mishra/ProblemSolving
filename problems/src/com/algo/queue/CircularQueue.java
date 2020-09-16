package com.algo.queue;

public class CircularQueue {
	int front = -1;
	int rear = -1;
	int[] queue;
	int size;

	CircularQueue(int size) {
		this.size = size;
		queue = new int[size];
	}

	public void inEqueue(int val) {
		if ((front == -1 && rear == size - 1) || rear == (front - 1) % size) {
			System.out.println("Queue is full");
			return;
		}
		// resetting the rear value
		if (front > 0 && rear == size - 1) {
			rear = -1;
		}
		queue[++rear] = val;
	}

	public int deQueue() {
		if (front == rear) {
			System.out.println("Queue is empty");
			return -1;
		}
		if (front == size - 1) {
			front = -1;
		}
		int element = queue[++front];
		return element;
	}

	public static void main(String[] args) {
		CircularQueue queueImpl = new CircularQueue(3);
		queueImpl.inEqueue(4);
		queueImpl.inEqueue(5);
		queueImpl.inEqueue(6);
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
		queueImpl.inEqueue(7);
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
	}
}
