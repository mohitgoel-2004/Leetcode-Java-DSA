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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        
        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        int idx = 1;

        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) || 
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (firstCritical == -1) {
                    firstCritical = idx;
                } else {
                    minDistance = Math.min(minDistance, idx - prevCritical);
                }
                prevCritical = idx;
            }
            
            prev = curr;
            curr = curr.next;
            idx++;
        }

        if (firstCritical == -1 || prevCritical == firstCritical) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritical - firstCritical;
        return new int[]{minDistance, maxDistance};
    }
}