package ca.jrvs.problems;

/**
 * https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-1a5cc128f9de803bb178fd679fe9f6e7
 */
public class NthNodeFromEndOfLinkedList {

  public static class ListNode{
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
  }

  /**
   * Remove Nth Node From End of LinkedList problem.
   * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
   * This approach uses two pointers to iterate through the list and remove the target node. A fast
   * pointer proceeds before a slow pointer for n iterations. Once the fast pointer reaches the end
   * of the list, the slow pointer will be at the target node for removal.
   * Time complexity is O(n) is this is a one-pass approach through the linkedlist
   * Space complexity is O(1) as the linkedlist is modified in place and two pointers are used.
   * @param head
   * @param n
   * @return ListNode
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode pointer = head;
    ListNode slowPointer = head;

    while(pointer.next != null){
      pointer = pointer.next;
      if(n > 0)
        n--;
      else
        slowPointer = slowPointer.next;
    }
    if(n==1){
      head = slowPointer.next;
    }else{
      slowPointer.next = slowPointer.next.next;
    }
    return head;
  }
}
