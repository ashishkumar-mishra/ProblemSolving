package com.algo.array;

import java.util.ArrayList;
import java.util.List;

public class KPartitionOfArray {
	List<List<List<Integer>>> result =  new ArrayList<>();
	public void paritiontoKSubsets(int[] arr, int k){
		List<List<Integer>> list =  new ArrayList<>();
		for(int i= 0; i < k; i++){
			list.add(new ArrayList<>());
		}
		helper(arr,k,0,0,list);
	}
	
	private void helper(int[] arr, int k, int start, int noOfPartitions,List<List<Integer>> list){
		if(start >= arr.length){
			if(noOfPartitions == k){
				List<List<Integer>> tmp = new ArrayList<>();
				for(List<Integer> l : list) {
					tmp.add(new ArrayList<>(l));
				}
				result.add(new ArrayList<>(tmp));
			}
			return;
		}
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).size() > 0){
				list.get(i).add(arr[start]);
				helper(arr,k,start + 1, noOfPartitions,list);
				list.get(i).remove(list.get(i).size() - 1);
			} else {
				list.get(i).add(arr[start]);
				helper(arr,k,start + 1, noOfPartitions + 1,list);
				list.get(i).remove(list.get(i).size() - 1);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		KPartitionOfArray kpartition = new KPartitionOfArray();
		int[] arr = {1,2,3,4};
		kpartition.paritiontoKSubsets(arr, 3);
		System.out.println(kpartition.result);
	}
}
