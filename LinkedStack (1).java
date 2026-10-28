/**
 * LinkedStack: an integer stack built from singly linked nodes (LIFO).
 * The head of the chain is the top of the stack, so push and pop
 * only ever touch the head: O(1), and there is no fixed capacity.
 */
public class LinkedStack {

    // Private inner class: callers never see or touch nodes directly.
    private static class Node {
        int data;
        Node next;
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top;   // null when the stack is empty
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() { return top == null; }

    public int size() { return size; }

    /** Add a value on top. O(1). */
    public void push(int value) {
        top = new Node(value, top);   // new node points at old top
        size++;
    }

    /** Remove and return the top value. O(1). */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = top.data;
        top = top.next;               // old top becomes garbage
        size--;
        return value;
    }

    /** Return the top value without removing it. O(1). */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    /** Remove everything. O(1) (garbage collector frees the nodes). */
    public void clear() {
        top = null;
        size = 0;
    }

    /** Print from top to bottom. O(n). */
    public void display() {
        System.out.print("TOP -> ");
        for (Node cur = top; cur != null; cur = cur.next) {
            System.out.print(cur.data + " -> ");
        }
        System.out.println("null   (size = " + size + ")");
    }
}
