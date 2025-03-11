package ca.jrvs.problems;

import static org.junit.Assert.assertArrayEquals;
import ca.jrvs.problems.MiddleOfTheLinkedList.ListNode;
import org.junit.Before;
import org.junit.Test;


public class MiddleOfTheLinkedList_Test {

  private MiddleOfTheLinkedList middleOfTheLinkedList;

  @Before
  public void setUp(){
    middleOfTheLinkedList = new MiddleOfTheLinkedList();
  }

  @Test
  public void middleNode1(){
    ListNode tail = new ListNode(5);
    ListNode fourth = new ListNode(4, tail);
    ListNode third = new ListNode(3, fourth);
    ListNode second = new ListNode(2, third);
    ListNode head = new ListNode(1, second);

    ListNode result = middleOfTheLinkedList.middleNode(head);

    int[] actual = new int[3];
    int[] expected = {3,4,5};
    int i = 0;

    while(result != null){
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }
  @Test
  public void middleNode2(){
    ListNode tail = new ListNode(6);
    ListNode fifth = new ListNode(5, tail);
    ListNode fourth = new ListNode(4, fifth);
    ListNode third = new ListNode(3, fourth);
    ListNode second = new ListNode(2, third);
    ListNode head = new ListNode(1, second);

    ListNode result = middleOfTheLinkedList.middleNode(head);

    int[] actual = new int[3];
    int[] expected = {4,5,6};
    int i = 0;

    while(result != null){
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }
  @Test
  public void middleNode3(){

    ListNode second = new ListNode(2);
    ListNode head = new ListNode(1, second);

    ListNode result = middleOfTheLinkedList.middleNode(head);

    int[] actual = new int[1];
    int[] expected = {2};
    int i = 0;

    while(result != null){
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }
  @Test
  public void middleNode4(){

    ListNode head = new ListNode(1);

    ListNode result = middleOfTheLinkedList.middleNode(head);

    int[] actual = new int[1];
    int[] expected = {1};
    int i = 0;

    while(result != null){
      actual[i] = result.val;
      result = result.next;
      i++;
    }
    assertArrayEquals(expected, actual);
  }
}
