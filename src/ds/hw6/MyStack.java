package ds.hw6;

public class MyStack<T> {

    T[] mySTack;
    int top;
    int capacity;

    @SuppressWarnings("unchecked")
    public MyStack(int capacity) {
        this.capacity = capacity;
        mySTack = (T[]) new Object[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size(){
        return top+1;
    }

    public T top(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return mySTack[top];
    }

    public void push(T item){
        if(top == capacity-1){
            throw new RuntimeException("Stack Overflow");
        }
        mySTack[++top] = item;
    }

    public void pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        mySTack[top--] = null;
    }

}
