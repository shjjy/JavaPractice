package algorithm.ls12;

import java.util.Random;

public class SkipList<T extends Comparable<T>> {

    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();

        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(19);
        skipList.insert(17);

        skipList.printList();

        System.out.println("Search 6: " + skipList.search(6));
        System.out.println("Search 10: " + skipList.search(10));
        System.out.println("Delete 7: " + skipList.delete(7));

        System.out.println("After delete:");
        skipList.printList();
    }

    private static final int MAX_LEVEL = 8;
    private final SkipNode<T> head;
    private final Random random = new Random();



    public SkipList() {
        head = new SkipNode<>(null, MAX_LEVEL); // 一定要有左節點
    }

    private int randomLevel() {
        int level = 0;
        while (random.nextInt(2) == 1 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void insert(T value) {
        SkipNode<T>[] update = new SkipNode[MAX_LEVEL + 1];
        SkipNode<T> current = head;

        // 從高層往低層尋找插入位置
        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        // 確認是否需要插入
        int level = randomLevel();
        SkipNode<T> newNode = new SkipNode<>(value, level);

        // 更新指標
        for (int i = 0; i <= level; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public boolean search(T value) {
        SkipNode<T> current = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
        }

        current = current.forward[0];
        return current != null && current.value.equals(value);
    }

    public boolean delete(T value) {
        SkipNode<T>[] update = new SkipNode[MAX_LEVEL + 1];
        SkipNode<T> current = head;

        // 找到每層需要更新的節點
        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(value) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        // 檢查目標節點是否存在
        current = current.forward[0];
        if (current == null || !current.value.equals(value)) {
            return false; // 沒找到
        }

        // 更新指標以刪除節點
        for (int i = 0; i <= MAX_LEVEL; i++) {
            if (update[i].forward[i] != current) {
                break;
            }
            update[i].forward[i] = current.forward[i];
        }

        return true;
    }

    public void printList() {
        for (int i = MAX_LEVEL; i >= 0; i--) {
            SkipNode<T> current = head.forward[i];
            System.out.print("Level " + i + ": ");
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }


}

class SkipNode<T> {
    T value;
    SkipNode<T>[] forward;

    @SuppressWarnings("unchecked")
    public SkipNode(T value, int level) {
        this.value = value;
        this.forward = new SkipNode[level + 1];
    }
}
