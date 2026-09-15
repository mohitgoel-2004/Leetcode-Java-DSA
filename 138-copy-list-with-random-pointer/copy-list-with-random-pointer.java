/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> m = new HashMap<>();
        Node newHead = new Node(head.val);
        m.put(head, newHead);
       Node oldTemp = head.next;
        Node newTemp = newHead;
        
        while(oldTemp != null){
            Node copyNode = new Node(oldTemp.val);
            m.put(oldTemp, copyNode);
            newTemp.next = copyNode;
            oldTemp = oldTemp.next;
            newTemp = newTemp.next;
        }
        oldTemp = head;
        newTemp = newHead;
        while(oldTemp != null){
            newTemp.random = m.get(oldTemp.random);
            oldTemp = oldTemp.next;
            newTemp = newTemp.next;
        }
        return newHead;
    }
}