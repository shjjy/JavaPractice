package ds.hw6;

public class MyQueue<T> {
    private T[] myQueue;
    private int front;
    private int rear;
    private int capacity;

    public MyQueue(T[] myQueue, int capacity) {
        this.myQueue = myQueue;
        this.capacity = capacity;
        this.front = this.rear = capacity - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return myQueue[(front + 1) % capacity];
    }

    public T rear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return myQueue[rear];
    }

    public void push(T item) {
        if ((rear + 1) % capacity == front) {
            throw new RuntimeException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        myQueue[rear] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = (front + 1) % capacity;
        T item = myQueue[front];
        myQueue[front] = null;
        return item;
    }
}
