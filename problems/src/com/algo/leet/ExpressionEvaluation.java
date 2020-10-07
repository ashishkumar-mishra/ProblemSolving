package com.algo.leet;

import java.util.Stack;

public class ExpressionEvaluation {

	public static void main(String[] args) {
		ExpressionEvaluation expressionEvaluation = new ExpressionEvaluation();
//        System.out.println(expressionEvaluation.evaluate("10 + 2 * 6")); 
//        System.out.println(expressionEvaluation.evaluate("100 * 2 + 12")); 
//        System.out.println(expressionEvaluation.evaluate("100 * ( 2 + 12 )")); 
        //System.out.println(expressionEvaluation.evaluate("100 * ( 2 + 12 ) / 14"));
        System.out.println(expressionEvaluation.evaluate("10 + 2 - 4 + 5"));
	}

	public long evaluate(final String expression) {
		char[] tokens = expression.toCharArray();
		Stack<Integer> dataStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();

		for (int i = 0; i < tokens.length; i++) {
			// case 1: space found
			if (tokens[i] == ' ') {
				continue;
			}
			// case 2 : in case we found the number
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuilder num = new StringBuilder();
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
					num.append(tokens[i++]);
				}
				dataStack.push(Integer.valueOf(num.toString()));
			} else if (tokens[i] == '(') {
				operatorStack.push(tokens[i]);
			} else if (tokens[i] == ')') {
				while (operatorStack.peek() != '(') {
					dataStack.push(compute(dataStack.pop(), dataStack.pop(), operatorStack.pop()));
				}
				operatorStack.pop();
			} else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				while (!operatorStack.isEmpty() && getPrecedence(tokens[i], operatorStack.peek())) {
					dataStack.push(compute(dataStack.pop(), dataStack.pop(), operatorStack.pop()));
				}
				operatorStack.push(tokens[i]);
			}
		}
		// entire expression is parsed
		while (!operatorStack.isEmpty()) {
			dataStack.push(compute(dataStack.pop(), dataStack.pop(), operatorStack.pop()));
		}
		return dataStack.pop();
	}

	public boolean getPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')') {
			return false;
		}
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op1 == '-')) {
			return false;
		}
		return true;
	}

	public int compute(int num2, int num1, char operator) {
		switch (operator) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			if (num2 == 0) {
				throw new UnsupportedOperationException("Cannot divide by zero");
			}
			return num1 / num2;
		}
		return 0;
	}
}
