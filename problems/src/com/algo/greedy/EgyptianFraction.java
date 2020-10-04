package com.algo.greedy;

import java.util.ArrayList;
import java.util.List;

public class EgyptianFraction {

	public List<String> getEgyptianFractions(int numerator, int denominator, final List<String> result) {
		if (numerator == 0 || denominator == 0) {
			return result;
		}

		if (denominator % numerator == 0) {
			result.add("1/" + denominator / numerator);
			return result;
		}

		if (numerator % denominator == 0) {
			result.add("" + numerator / denominator);
			return result;
		}

		if (numerator > denominator) {
			result.add("" + numerator / denominator);
			getEgyptianFractions(numerator % denominator, denominator, result);
			return result;
		}
		// we reached at position where denominator > numerator and denominator %
		// numerator is non zero
		int n = denominator / numerator + 1;
		result.add("1/" + n);
		getEgyptianFractions(numerator * n - denominator, denominator * n, result);
		return result;
	}
	
	public static void main(String[] args) {
		EgyptianFraction egyptianFraction = new EgyptianFraction();
		List<String> result = egyptianFraction.getEgyptianFractions(2,3, new ArrayList<>());
		System.out.println(result);
	}
}
