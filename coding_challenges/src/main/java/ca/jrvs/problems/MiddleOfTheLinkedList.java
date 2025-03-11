package ca.jrvs.problems;

/**
 * https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-1a6cc128f9de8048985aff0598162803
 */
public class MiddleOfTheLinkedList {

  public static class ListNode{
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
  }

  /**
   * Middle of the Linkedlist problem
   * https://leetcode.com/problems/middle-of-the-linked-list/
   * The two pointers approach to the middle of the linkedlist problem. This approach uses a fast
   * pointer to iterate two nodes for every node the slow pointer iterates. When the fast pointer
   * reaches the end of the list, the slow pointer will be in the middle of the list.
   * Time complexity is O(n) as we do a single pass through the linkedlist.
   * Space complexity is O(1) as we are using a constant number of pointers.
   * @param head
   * @return ListNode
   */
  public ListNode middleNode(ListNode head){
    ListNode fast = head;
    ListNode slow = head;

    while(fast.next != null){
      fast = fast.next;
      if(fast.next != null)
        fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }
}
