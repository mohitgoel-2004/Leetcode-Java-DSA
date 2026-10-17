/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
      ListNode sH = new ListNode(0);
      ListNode gH = new ListNode(0);
      ListNode s = sH;
      ListNode g = gH;

      while(head != null){
        if(head.val < x){
            s.next = head;
            s = s.next;
        }else{
            g.next = head;
            g = g.next;
        }
        head = head.next;
      } 
      g.next = null;
      s.next = gH.next;

      return sH.next; 
    }
}