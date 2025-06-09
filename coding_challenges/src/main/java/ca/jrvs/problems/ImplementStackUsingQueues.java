package ca.jrvs.problems;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
  Queue<Integer> queue1;
  int size = 0;

  public ImplementStackUsingQueues() {
    queue1 = new LinkedList<Integer>();
  }

  public void push(int x) {
    queue1.add(x);
    size++;
  }

  public int pop() {
    for(int i = 0; i < size - 1; i++){
      queue1.add(queue1.remove());
    }
    size--;
    return queue1.remove();
  }

  public int top() {
    for(int i = 0; i < size - 1; i++){
      queue1.add(queue1.remove());
    }
    int result = queue1.peek();
    queue1.add(queue1.remove());
    return result;
  }

  public boolean empty() {
    return queue1.isEmpty();
  }
}
