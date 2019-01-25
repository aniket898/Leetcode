/*Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
Understand the problem:
This is an extension of the problem I. This mainly difference is you can buy and sell as many times as you want, but you need to sell the current stock before you buy again, i.e, each time you can only hold at most one stock.

So the process is like: buy -> sell -> buy -> sell, etc... 
A DP Solution:
Since this problem asks for the maximal profit, we may also consider the DP solution. 

Define a DP array, dp[n], where dp[i] means the maximal profit at day i
Initial state: dp[0] = 0 means we buy and sell at the first day
Transit function. dp[i] = dp[i - 1] + prices[i] - prices[i - 1], where prices[i] > prices[i - 1], dp[i] = dp[i - 1], where prices[i] <= prices[i - 1]
Final state: Check dp[n]



*/

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
         
        int[] dp = new int[prices.length];
         
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                dp[i] = dp[i - 1] + prices[i] - prices[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }
         
        return dp[prices.length - 1];
    }
}