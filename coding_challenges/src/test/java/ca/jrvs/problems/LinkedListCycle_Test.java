package ca.jrvs.problems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.problems.LinkedListCycle.ListNode;
import org.junit.Before;
import org.junit.Test;

public class LinkedListCycle_Test {

  private LinkedListCycle linkedListCycle;

  @Before
  public void setUp(){
    linkedListCycle = new LinkedListCycle();
  }

  @Test
  public void hasCycle1(){
    ListNode second = new ListNode();
    ListNode fourth = new ListNode(-4, second);
    ListNode third = new ListNode(2, fourth);
    second.val = 0;
    second.next = third;
    ListNode head = new ListNode(3, second);

    assertTrue(linkedListCycle.hasCycle(head));
  }

  @Test
  public void hasCycle2(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(2, head);

    assertTrue(linkedListCycle.hasCycle(head));
  }

  @Test
  public void hasCycle3(){
    ListNode head = new ListNode(1);

    assertFalse(linkedListCycle.hasCycle(head));
  }

  @Test
  public void hasCycle4(){
    ListNode head = new ListNode();

    assertFalse(linkedListCycle.hasCycle(head));
  }

  @Test
  public void hasCycle5(){
    ListNode head = new ListNode(1);
    ListNode second = new ListNode(2);
    head.next = second;

    assertFalse(linkedListCycle.hasCycle(head));
  }

  @Test
  public void hasCycle6(){
    ListNode fourth = new ListNode(1);
    ListNode third = new ListNode(1, fourth);
    ListNode second = new ListNode(1, third);
    ListNode head = new ListNode(1, second);

    assertFalse(linkedListCycle.hasCycle(head));
  }
}
