package ca.jrvs.problems;

/**
 * Reverse linkedlist problem.
 * https://www.notion.so/jarvisdev/Reverse-Linked-List-1adcc128f9de80c990badca2dcc13b89
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

  public static class ListNode{
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
  }

  /**
   * This approach in reversing a linkedlist utilizes three pointers. Here, the linkedlist is
   * traversed and each node's references are being updated so that the reference to the next node
   * becomes the current node's previous node.
   * Time complexity is O(n) as this utilizes a one-pass loop.
   * Space complexity is O(1) for the pointers.
   * @param head
   * @return ListNode
   */
  public ListNode reverseList(ListNode head){
    ListNode current = head;
    ListNode next;
    ListNode prev = null;

    while(current != null){
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    return prev;
  }

}
