package com.aniket.test;
import java.io.*;
import java.util.*;


public class TestHackRank {
}



class Solution
{
    public static int minDistance = Integer.MAX_VALUE;
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        /* You are provided the following code to read in the input.
           Please Feel free to change as needed to solve the problem. */
        Scanner sc = new Scanner(System.in);
        int M = 3;
        int N = 3;

        int[][] matrix = new int[M][N];

        int startX = 1;
        int startY = 1;

        int destinationX = 3;
        int destinationY = 3;

//        while (sc.hasNext()) {
            int blockedX = 2;
            int blockedY = 2;
            matrix[blockedX][blockedY] = -1;
//        }





        boolean[][] visited= new boolean[M][N];
        //visited[startX-1][startY-1] = 1;
        int distance = 0;
        //int minDistance = Integer.MAX_VALUE;
        shortestPath(matrix, visited, startX, startY, destinationX, destinationY, distance);
        if(minDistance == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(minDistance);

    }

    public static void shortestPath(int[][] matrix, boolean[][] visited, int row, int col, int destX, int destY, int distance){
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || row > rows || col < 0 || col > cols) {
            return;
        }

        if(row == destX && col == destY){
            minDistance =  Math.min(distance, minDistance);
            return;
        }

        // visited
        if(visited[row][col]) {
            return;
        }

        // Is blocked?
        if (matrix[row][col] == -1) {
            return;
        }

        // Mark as visited
        visited[row][col] = true;
        // go up, down, left and right
        shortestPath(matrix, visited, row - 1, col, destX, destY,distance + 1);
        shortestPath(matrix, visited,row + 1, col, destX, destY,distance + 1);
        shortestPath(matrix, visited, row, col - 1, destX, destY,distance + 1);
        shortestPath(matrix, visited, row, col + 1, destX, destY,distance + 1);

        // Mark as unvisited
        visited[row][col] = false;
    }

}