

package com.algo.leet;

import java.util.ArrayList;
import java.util.List;

public class NQueenProblem {

    public static void main(String[] args) {
        NQueenProblem queenProblems = new NQueenProblem();
        List<List<String>> result = queenProblems.nQuestionProblems(4);
        System.out.println(result);
    }

    class Position {
        int row, column;

        Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public Position[] nQueenPositions(int n) {
        Position[] positions = new Position[n];
        boolean isSolution = solveNQueenProblems(n, 0, positions);
        if (isSolution) {
            return positions;
        }
        return null;
    }

    private boolean solveNQueenProblems(int n, int row, Position[] positions) {
        // we have placed all the queens
        if (n == row) {
            return true;
        }
        for (int col = 0; col < n; col++) {
            boolean isSolution = true;
            // checking whether row and columns are not under attack
            for (int i = 0; i < row; i++) {
                if (positions[i].column == col || (positions[i].row - positions[i].column) == (row - col)
                            || (positions[i].row + positions[i].column) == (row + col)) {
                    isSolution = false;
                    break;
                }
            }
            if (isSolution) {
                positions[row] = new Position(row,col);
                if(solveNQueenProblems(n,row + 1, positions)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public List<List<String>> nQuestionProblems(int n){
        List<List<String>> result = new ArrayList<>();
        Position[] positions = new Position[n];
        solve(0,n,result,positions);
        return result;
    }
    
    private void solve(int row,int n, List<List<String>> result, Position[] positions) {
        if(row == n) {
            // getting the string from positions
           List<String> list = new ArrayList<>();
           for(Position pos : positions) {
               StringBuilder str = new StringBuilder();
               for(int i=0; i < n ; i++) {
                   if(pos.column == i) {
                       str.append("Q");
                   }else {
                       str.append("."); 
                   }
               }
               list.add(str.toString());
           }
           result.add(list);
           return;
        }
        // traversing the columns
        for(int col = 0; col < n; col++) {
            boolean isSafe = true;
            for(int i = 0; i < row; i++) {
                // checking whether current position is under attack
                if(col == positions[i].column || (row - col) == (positions[i].row - positions[i].column) || (row + col) == (positions[i].row + positions[i].column)) {
                    isSafe = false;
                    break;
                }
            }
            if(isSafe) {
                positions[row] = new Position(row,col);
                solve(row + 1, n, result, positions);
            }
        }
    }
}
