package com.algo.string;

import java.util.ArrayList;
import java.util.List;

public class ZigZagString {

	public String convertZigzag(String str, int numRows) {
		if (numRows == 1) {
			return str;
		}

		List<StringBuilder> list = new ArrayList<>();
		for (int i = 0; i < Math.min(str.length(), numRows); i++) {
			list.add(new StringBuilder());
		}
		boolean down = true;
		int row = 0;

		for (int i = 0; i < str.length(); i++) {
			list.get(row).append(str.charAt(i));
			if (row == 0) {
				down = true;
			}
			if (row == numRows - 1) {
				down = false;
			}
			row = down ? row + 1 : row - 1;
		}
		StringBuilder result = new StringBuilder();
		for (StringBuilder s : list) {
			result.append(s.toString());
		}
		return result.toString();
	}

	public static void main(String[] args) {
		ZigZagString zigZagString = new ZigZagString();
		String str = "PAYPALISHIRING";
		System.out.println(zigZagString.convertZigzag(str, 3));
	}
}
