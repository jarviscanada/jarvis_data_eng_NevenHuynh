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
    ListNode tail = new ListNode(5);
    ListNode fourth = new ListNode(4, tail);
    ListNode third = new ListNode(3, fourth);
    ListNode second = new ListNode(2, third);
    ListNode head = new ListNode(1, second);

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

}
