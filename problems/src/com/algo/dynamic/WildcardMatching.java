package com.algo.dynamic;

public class WildcardMatching {

	public boolean isMatching(String pattern, String stringToMatch) {
		
		//System.out.println("isMatching("+pattern + "," +stringToMatch + ")");

		if (pattern.isEmpty() && stringToMatch.isEmpty()) {
			return true;
		}

		// case 1 : when stringToMatch is empty and pattern to match is having more than
		// one char

		if (pattern.length() > 1 && pattern.charAt(0) == '*' && stringToMatch.isEmpty()) {
			return false;
		}
		// case 2
		if ((pattern.length() > 0 && pattern.charAt(0) == '?') || (pattern.length() != 0 && stringToMatch.length() != 0 && pattern.charAt(0) == stringToMatch.charAt(0))) {
			return isMatching(pattern.substring(1), stringToMatch.substring(1));
		}
		// case 3
		if (pattern.length() > 0 && pattern.charAt(0) == '*') {
			return (isMatching(pattern.substring(1), stringToMatch) || isMatching(pattern, stringToMatch.substring(1)));
		}
		return false;
	}

	public static void main(String[] args) {
		WildcardMatching wildcardMatching = new WildcardMatching();
		System.out.println(wildcardMatching.isMatching("g*ks", "geeks"));
		System.out.println(wildcardMatching.isMatching("ge?ks*", "geeksforgeeks"));
		System.out.println(wildcardMatching.isMatching("abc*bcd", "abcdhghgbcd"));
		System.out.println(wildcardMatching.isMatching("g*k", "gee"));
		System.out.println(wildcardMatching.isMatching("*", ""));
	}

}
