

package com.algo.leet;


import java.util.ArrayList;
import java.util.List;


public class GenerateParanthesis {

    public static void main(String[] args) {
        GenerateParanthesis generate = new GenerateParanthesis();
        List<String> list = generate.generateParanthesis(3);
        list.forEach(str -> {
            System.out.println(str);
        });
    }

    public List<String> generateParanthesis(int n) {
        List<String> list = new ArrayList<>();
        generateAll(n, n, "", list);
        return list;
    }

    private void generateAll(int leftNeededCount, int rightNeededCount, String current, List<String> result) {
        if(leftNeededCount == 0 && rightNeededCount == 0) {
            result.add(current);
            return;
        }
        
        if(leftNeededCount > 0) {
            generateAll(leftNeededCount - 1, rightNeededCount, current + "(",result);
        }
        
        if(leftNeededCount < rightNeededCount) {
            generateAll(leftNeededCount,rightNeededCount - 1, current + ")",result);
        }
    }
}
