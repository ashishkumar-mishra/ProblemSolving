

package com.algo.leet;


import java.util.ArrayList;
import java.util.List;


public class AllPermutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permutations(0,nums.length - 1,nums,result);
        return result;
    }

    private void permutations(int startIndex, int endIndex, int[] nums, List<List<Integer>> result) {
        if (startIndex == endIndex) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
        }
        
        for(int i = startIndex; i < nums.length; i++) {
            swap(nums,startIndex,i);
            permutations(startIndex + 1, endIndex, nums, result);
            swap(nums,startIndex,i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
        AllPermutations allPermutations = new AllPermutations();
        int[] nums = {1,2,3};
        List<List<Integer>> result = allPermutations.permute(nums);
        System.out.println(result);
    }
}
