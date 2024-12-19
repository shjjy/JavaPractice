package ds.hw8;

public class Chain<T> {
    private ChainNode<T> first, last;

    public static void main(String[] args) {
        Chain<Integer> chain = new Chain<>();
        chain.create2();
        chain.print();
        chain.addAfter(5, 100);
        chain.print();
        chain.addLast(0);
        chain.print();
        chain.addFirst(1);
        chain.print();
        chain.addLast(2);
        chain.print();
        chain.addAfter(20, 100);
        chain.print();
        chain.delete(100, 20);
        chain.print();
        chain.delete(20, 20);
        chain.print();
        chain.insert50(20);
        chain.print();
        chain.insert50(30);
        chain.print();

        Chain<Integer> chain2 = new Chain<>();
        chain2.create2();
        chain2.print();

        chain.concat(chain2);
        chain.print();
        chain.reverse();
        chain.print();
    }

    private void reverse() {
        ChainNode<T> current = first;
        ChainNode<T> pre = null;
        ChainNode<T> next = null;
        last = first;
        while (current != null) {
            next = current.next;//暫存下個節點
            current.next = pre;//反轉
            pre = current;//準備處理下個節點
            current = next;
        }
        first = pre;//更新頭
    }

    private void concat(Chain<T> otherChain){
        if(first == null){
            first = otherChain.first;
            last = otherChain.last;
        }else {
            last.next = otherChain.first;
            last = otherChain.last;
        }
        otherChain.first = null;
        otherChain.last = null;
    }

    @SuppressWarnings("SameParameterValue")
    private void delete(T target, T preNodeData) {
        ChainNode<T> current = first;
        while (current != null && current.next != null) {
            if (current.data.equals(preNodeData) && current.next.data.equals(target)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        System.out.println("target " + target + " not found");
    }

    @SuppressWarnings({"unchecked", "ReassignedVariable"})
    private void insert50(T target) {
        ChainNode<T> current = first;
        while (current != null && !current.data.equals(target)) {
            current = current.next;
        }
        if (current != null) {
            ChainNode<T> newNode = new ChainNode<>((T) Integer.valueOf(50));
            newNode.next = current.next;
            current.next = newNode;
        } else {
            first = new ChainNode<>((T) Integer.valueOf(50));
            last = first;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void addAfter(T target, T data) {
        ChainNode<T> current = first;
        while (current != null && !current.data.equals(target)) {
            current = current.next;
        }
        if (current != null) {
            ChainNode<T> newNode = new ChainNode<>(data);
            newNode.next = current.next;
            current.next = newNode;
        } else {
            System.out.println("target " + target + " not found");
        }
    }

    @SuppressWarnings("unchecked")
    private void create2() {
        ChainNode<T> second = new ChainNode<>((T) Integer.valueOf(20), null);
        first = new ChainNode<>((T) Integer.valueOf(10), second);
        last = second;
    }

    private void addLast(T data) {
        if (first != null) {
            ChainNode<T> newNode = new ChainNode<>(data);
            last.next = newNode;
            last = newNode;
        } else {
            addFirst(data);
        }
    }

    private void addFirst(T data) {
        if (first == null) {
            first = new ChainNode<>(data);
            last = first;
        } else {
            ChainNode<T> newNode = new ChainNode<T>(data);
            newNode.next = first;
            first = newNode;
        }
    }

    private void print() {
        ChainNode<T> current = first;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.print("null");
        System.out.println();
    }
}

class ChainNode<T> {
    T data;
    ChainNode<T> next;

    public ChainNode(T data) {
        this.data = data;
    }

    public ChainNode(T data, ChainNode<T> next) {
        this.data = data;
        this.next = next;
    }
}