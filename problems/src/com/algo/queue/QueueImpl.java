package com.algo.queue;

public class QueueImpl {

	// queue implementation using array

	int front = -1;
	int rear = -1;
	int size;
	int[] queue;

	QueueImpl(int size) {
		this.size = size;
		queue = new int[size];
	}

	public void enQueue(int element) {
		if (rear == size - 1) {
			System.out.println("Queue is full");
			return;
		}
		queue[++rear] = element;
	}

	public int deQueue() {
		if (front == rear) {
			System.out.println("Queue is empty");
			return -1;
		}
		int element = queue[front+1];

		for (int i = 0; i < rear; i++) {
			queue[i] = queue[i + 1];
		}
		rear--;
		return element;
	}
	
	public static void main(String[] args) {
		QueueImpl queueImpl = new QueueImpl(3);
		queueImpl.enQueue(4);
		queueImpl.enQueue(5);
		queueImpl.enQueue(6);
		queueImpl.enQueue(7);
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
		System.out.println(queueImpl.deQueue());
	}
}
