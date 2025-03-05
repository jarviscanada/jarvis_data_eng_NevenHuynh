package ca.jrvs.problems;

import static org.junit.Assert.assertArrayEquals;

import ca.jrvs.problems.NthNodeFromEndOfLinkedList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class NthNodeFromEndofLinkedList_Test {

  private NthNodeFromEndOfLinkedList nthNodeFromEndOfLinkedList;

  @Before
  public void setUp(){
    nthNodeFromEndOfLinkedList = new NthNodeFromEndOfLinkedList();
  }

  @Test
  public void removeNthFromEnd1(){
    ListNode tail = new ListNode(5);
    ListNode fourth = new ListNode(4, tail);
    ListNode third = new ListNode(3, fourth);
    ListNode second = new ListNode(2, third);
    ListNode head = new ListNode(1, second);

    ListNode result = nthNodeFromEndOfLinkedList.removeNthFromEnd(head,2);

    int[] actual = new int[4];
    int[] expected = {1,2,3,5};
    int i=0;

    while(result != null) {
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }

  @Test
  public void removeNthFromEnd2(){

    ListNode head = new ListNode(1);

    ListNode result = nthNodeFromEndOfLinkedList.removeNthFromEnd(head,1);

    int[] actual = new int[0];
    int[] expected = {};
    int i=0;
    while(result != null) {
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }

  @Test
  public void removeNthFromEnd3(){
    ListNode second = new ListNode(2);
    ListNode head = new ListNode(1, second);

    ListNode result = nthNodeFromEndOfLinkedList.removeNthFromEnd(head,1);

    int[] actual = new int[1];
    int[] expected = {1};
    int i=0;
    while(result != null) {
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }

  @Test
  public void removeNthFromEnd4(){
    ListNode second = new ListNode(2);
    ListNode head = new ListNode(1, second);

    ListNode result = nthNodeFromEndOfLinkedList.removeNthFromEnd(head,2);

    int[] actual = new int[1];
    int[] expected = {2};
    int i=0;
    while(result != null) {
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }
}
