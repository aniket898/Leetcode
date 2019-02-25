/*
Java Solution 1 - Inorder Traversal
We can inorder traverse the tree and get the kth smallest element. Time is O(n).
*/

public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
 
    TreeNode p = root;
    int result = 0;
 
    while(!stack.isEmpty() || p!=null){
        if(p!=null){
            stack.push(p);
            p = p.left;
        }else{
            TreeNode t = stack.pop();
            k--;
            if(k==0)
                result = t.val;
            p = t.right;
        }
    }
 
    return result;
}