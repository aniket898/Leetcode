/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return true;
        }
         
        if (board.length == 0 && word.length() == 0) {
            return true;
        }
         
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
         
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (existHelper(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
     
    private boolean existHelper(char[][] board, String word, int start, int row, int col, boolean[][] visited) {
        if (start == word.length()) {
            return true;
        }
         
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
            return false;
        }
         
        if (visited[row][col]) {
            return false;
        }
         
        if (board[row][col] != word.charAt(start)) {
            return false;
        }
         
        visited[row][col] = true;
        boolean result = existHelper(board, word, start + 1, row - 1, col, visited) ||
                         existHelper(board, word, start + 1, row + 1, col, visited) ||
                         existHelper(board, word, start + 1, row, col - 1, visited) ||
                         existHelper(board, word, start + 1, row, col + 1, visited);
        visited[row][col] = false;
         
        return result;
    }
}