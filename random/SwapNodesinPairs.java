/*Given a linked list, swap every two adjacent nodes and return its head.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the list's nodes, only nodes itself may be changed.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   
        return head;
 
        ListNode h = new ListNode(0);
        h.next = head;
        
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode pre = h;
        
        while(p1!=null && p2!=null){
            pre.next = p2;

            ListNode t = p2.next;
            p2.next = p1;
            pre = p1;
            p1.next = t;

            p1 = p1.next;

            if(t!=null)
                p2 = t.next;
        }
 
        return h.next;
        
        
    }
}