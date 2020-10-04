package com.algo.leet;

public class RobotReturnToOrigin {

	public static void main(String[] args) {
		RobotReturnToOrigin r = new RobotReturnToOrigin();
		System.out.println(r.judgeCircle("LLLL"));
	}

	public boolean judgeCircle(String moves) {
		boolean reachedToOrigin = false;
		int x = 0;
		int y = 0;
		for (int i = 0; i < moves.length(); i++) {
			if(moves.charAt(i) == 'U') {
				y = y + 1;
			}
			if(moves.charAt(i) == 'D') {
				y = y - 1;
			}
			if(moves.charAt(i) == 'L') {
				x = x - 1;
			}
			if(moves.charAt(i) == 'R') {
				x = x + 1;
			}
		}
		
		if(x == 0 && y == 0) {
			reachedToOrigin = true;
		}
		return reachedToOrigin;
	}

}
