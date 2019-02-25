/*

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.


             Approach 2: Using two arrays
Algorithm

In this approach, we make use of two 1-d arrays left2rightleft2right and right2leftright2left. The left2rightleft2right array is used to store the number of candies required by the current student taking care of the distribution relative to the left neighbours only. i.e. Assuming the distribution rule is: The student with a higher ratings than its left neighbour should always get more candies than its left neighbour. Similarly, the right2leftright2left array is used to store the number of candies candies required by the current student taking care of the distribution relative to the right neighbours only. i.e. Assuming the distribution rule to be: The student with a higher ratings than its right neighbour should always get more candies than its right neighbour. To do so, firstly we assign 1 candy to each student in both left2rightleft2right and right2leftright2left array. Then, we traverse the array from left-to-right and whenever the current element's ratings is larger than the left neighbour we update the current element's candies in the left2rightleft2right array as left2right[i] = left2right[i-1] + 1left2right[i]=left2right[i−1]+1, since the current element's candies are always less than or equal candies than its left neighbour before updation. After the forward traversal, we traverse the array from left-to-right and update right2left[i]right2left[i] as right2left[i] = right2left[i + 1] + 1right2left[i]=right2left[i+1]+1, whenever the current ( i^{th}i 
th
  ) element has a higher ratings than the right ( (i+1)^{th}(i+1) 
th
  ) element.

Now, for the i^{th}i 
th
  student in the array, we need to give \text{max}(left2right[i], right2left[i])max(left2right[i],right2left[i]) to it, in order to satisfy both the left and the right neighbour relationship. Thus, at the end, we obtain the minimum number of candies required as:

minimum_candies=∑𝑖=0𝑛−1max(𝑙𝑒𝑓𝑡2𝑟𝑖𝑔ℎ𝑡[𝑖],𝑟𝑖𝑔ℎ𝑡2𝑙𝑒𝑓𝑡[𝑖]),where 𝑛=length of the ratings array.

*/

class Solution {
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}