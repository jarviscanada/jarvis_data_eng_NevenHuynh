package ca.jrvs.problems;

import static org.junit.Assert.assertArrayEquals;

import ca.jrvs.problems.ReverseLinkedList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class ReverseLinkedList_Test {

  private ReverseLinkedList reverseLinkedList;

  @Before
  public void setUp(){
    reverseLinkedList = new ReverseLinkedList();
  }

  @Test
  public void reverseList(){
    ListNode head = reverseLinkedList.new ListNode(1);
    head.next = reverseLinkedList.new ListNode(2);
    head.next.next = reverseLinkedList.new ListNode(3);
    head.next.next.next = reverseLinkedList.new ListNode(4);

    ListNode result = reverseLinkedList.reverseList(head);

    int[] actual = new int[5];
    int[] expected = {5,4,3,2,1};
    int i = 0;
    while(result != null){
      actual[i++] = result.val;
      result = result.next;
    }

    assertArrayEquals(actual, expected);
  }
  @Test
  public void recursion(){
    ListNode head = reverseLinkedList.new ListNode(1);
    head.next = reverseLinkedList.new ListNode(2);
    head.next.next = reverseLinkedList.new ListNode(3);
    head.next.next.next = reverseLinkedList.new ListNode(4);

    ListNode result = reverseLinkedList.recursion(head);

    ListNode current = result;
    int[] expected = {4,3,2,1};
    int[] actual = new int[4];
    int i = 0;
    while(current != null){
      actual[i] = current.val;
      System.out.println(actual[i]);
      current = current.next;
      i++;
    }

    assertArrayEquals(expected, actual);
  }
}
