package com.algo.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPickIndex {
	int[] nums;

	public RandomPickIndex(int[] nums) {
        this.nums=nums;
    }

	public int pick(int target) {
		List<Integer> list = new ArrayList<>();
		// figure out all the indices where target number appears
		// add those indices to a list
		for (int i = 0; i < nums.length; i++)
			if (nums[i] == target)
				list.add(i);

		// randomly pick an index of the list
		// return item from that index
		Random random = new Random();
		int randomIndex = random.nextInt(list.size());
		return list.get(randomIndex);
	}
}
