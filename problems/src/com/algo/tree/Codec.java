package com.algo.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {
	
 	 public String serialize(TreeNode root) {
   	 	StringBuilder result = new StringBuilder();
   	 	serializeHelper(root,result);
   	 	return result.toString();
     }
     
     public void serializeHelper(TreeNode node, StringBuilder result){
     	if(node == null){
     		result.append("null,");
     	} else {
     		result.append(node.key).append(",");
     		serializeHelper(node.left,result);
     		serializeHelper(node.right,result);
     	}
     }
     
     public TreeNode deserialize(String str){
     	Queue<String> queue = new LinkedList<>(Arrays.asList(str.split(",")));
     	return deserializeHelper(queue);
     }
     
     private TreeNode deserializeHelper(Queue<String> queue) {
    	 if(queue.peek().equals("null")) {
    		 queue.remove();
    		 return null;
    	 }
    	 TreeNode node = new TreeNode(Integer.valueOf(queue.remove()));
    	 node.left = deserializeHelper(queue);
    	 node.right = deserializeHelper(queue);
    	 
    	 return node;
     }
     
     
 	public static void main(String[] args) {
 		Codec codec = new Codec();
 		TreeNode root = new TreeNode(10);
 		root.left = new TreeNode(7);
 		root.right = new TreeNode(11);
 		root.left.left = new TreeNode(6);
 		root.left.right = new TreeNode(9);
 		root.right.left = new TreeNode(8);
 		root.right.right = new TreeNode(12);
 		System.out.println(codec.serialize(root));
	}

}
