package com.algo.leet;

public class CountAndSay {

	public static void main(String args[]) {
		CountAndSay countAndSay = new CountAndSay();
		System.out.println(countAndSay.countAndSay(4));
		System.out.println(countAndSay.countAndSayRecursive(4));
	}

	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		if (n == 2) {
			return "11";
		}
		String result = "11";
		for (int i = 3; i <= n; i++) {
			result += "$";
			String tmp = "";
			int count = 1;
			for (int j = 1; j < result.length(); j++) {
				if (result.charAt(j - 1) != result.charAt(j)) {
					tmp += count;
					tmp += result.charAt(j - 1);
					count = 1;
				} else {
					count++;
				}
			}
			result = tmp;
		}
		return result;
	}

	public String countAndSayRecursive(int n) {
		String previousString = "1";
		return nextString(n, previousString);
	}

	private String nextString(int n, String previousString) {
		if (n <= 1) {
			return previousString;
		}
		//logic to calculate the nextString
		previousString = previousString + "$";
		String nextString = "";
		int count = 1;
		
		for(int i = 1; i < previousString.length(); i++) {
			if(previousString.charAt(i - 1) != previousString.charAt(i)) {
				nextString += count;
				nextString += previousString.charAt(i - 1);
				count = 1;
			} else {
				count++;
			}
		}
		return nextString(n - 1, nextString);
	}
}
