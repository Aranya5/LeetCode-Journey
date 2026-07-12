import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    // q1 is our primary queue holding elements in LIFO order
    private Queue<Integer> q1;
    // q2 is our temporary helper queue
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        // 1. Put the new element into the helper queue
        q2.add(x);
        
        // 2. Move all elements from q1 to q2 so they sit behind x
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        
        // 3. Swap the references of q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    public int pop() {
        // The top element is always at the front of q1
        return q1.poll();
    }
    
    public int top() {
        // Look at the front element of q1 without removing it
        return q1.peek();
    }
    
    public boolean empty() {
        // The stack is empty if our primary queue is empty
        return q1.isEmpty();
    }
}