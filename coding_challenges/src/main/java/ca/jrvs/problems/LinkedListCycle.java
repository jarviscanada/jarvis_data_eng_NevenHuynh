package ca.jrvs.problems;

/**
 * https://www.notion.so/jarvisdev/LinkedList-Cycle-1a6cc128f9de80b2b10cebd214bcd7e0
 */
public class LinkedListCycle {
  public static class ListNode {
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int x) {val = x;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
  }

  /**
   * The Linkedlist cycle problem.
   * https://leetcode.com/problems/linked-list-cycle/
   * This approach uses two pointers. The fast pointer iterates two nodes for every node the slow
   * pointer iterates. If these pointers ever meet, then the linkedlist contains a cycle.
   * Time complexity is O(n) as we do a single pass of the linkedlist.
   * Space complexity is O(1) for the two pointers.
   * @param head
   * @return boolean
   */
  public boolean hasCycle(ListNode head) {
    ListNode slowPointer = head;
    ListNode fastPointer = head;

    while(fastPointer != null && fastPointer.next != null){
      fastPointer = fastPointer.next.next;
      slowPointer = slowPointer.next;

      if(fastPointer == slowPointer)
        return true;
    }
    return false;
  }
}
