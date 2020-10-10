package com.algo.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeGame {
	int x;
	int y;
	int width;
	int height;
	int score;
	Queue<int[]> queue;
	Deque<String> deque;

	SnakeGame(int width, int height, int[][] food) {
		x = 0;
		y = 0;
		score = 0;
		this.width = width;
		this.height = height;
		queue = new LinkedList<>();
		deque = new LinkedList<>();
		for (int i = 0; i < food.length; i++) {
			queue.add(food[i]);
		}
		deque.add(x + "," + y);
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over. Game over
	 *         when snake crosses the screen boundary or bites its body.
	 */
	public int move(String direction) {
		switch (direction) {
		case "U":
			x = x - 1;
			break;
		case "D":
			x = x + 1;
			break;
		case "R":
			y = y + 1;
			break;
		case "L":
			y = y - 1;
			break;
		default:
			score = -1;
		}
		int[] currentValue = queue.peek();
		if (!queue.isEmpty() && x == currentValue[0] && y == currentValue[1]) {
			score = score + 1;
			queue.poll();
		} else {
			deque.removeLast();
		}
		
		// case when snake eats itself
		for (String str : deque) {
			if (str.equals(x + "," + y)) {
				return -1;
			}
		}
		deque.addFirst(x + "," + y);
		if (x < 0 || y < 0 || x > height - 1 || y > width - 1) {
			score = -1;
		}
		return score;
	}

	public static void main(String[] args) {
		int[][] food = { { 2, 0 }, { 0, 0 }, {0,2},{ 0, 1 }, { 2, 2 },{0,1} };
		SnakeGame snakeGame = new SnakeGame(3, 3, food);
		System.out.println(snakeGame.move("D"));
		System.out.println(snakeGame.move("D"));
		System.out.println(snakeGame.move("R"));
		System.out.println(snakeGame.move("U"));
		System.out.println(snakeGame.move("U"));
		System.out.println(snakeGame.move("L"));
		System.out.println(snakeGame.move("D"));
		System.out.println(snakeGame.move("R"));
		System.out.println(snakeGame.move("R"));
		System.out.println(snakeGame.move("U"));
		System.out.println(snakeGame.move("L"));
		System.out.println(snakeGame.move("L"));
		System.out.println(snakeGame.move("D"));
		System.out.println(snakeGame.move("R"));
	    System.out.println(snakeGame.move("U"));
	}
}
