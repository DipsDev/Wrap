package server.models.storetypes.sortedlist;

import java.util.List;
import java.util.Random;

public class SkipList<T> {

    private final SkipNode<T> head;
    private final int maxLevel;

    private int size;

    private final Random random = new Random();

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        this.head = new SkipNode<T>(Integer.MIN_VALUE, null, maxLevel);
        this.size = 0;
    }

    private int generateRandomHeight() {
        int level = 0;
        while (random.nextDouble() < 0.5 && level < this.maxLevel) {
            level++;
        }
        return level;
    }

    public int getSize() {
        return size;
    }

    public void insert(int value, T data) {
        SkipNode<T> nd = this.head;
        SkipNode<T> newNode = new SkipNode<T>(value, data, generateRandomHeight());
        if (size == 0) {
            for (int i = 0; i<newNode.getLevel() + 1; i++) {
                nd.getNext()[i] = newNode;
            }
            size++;
            return;
        }
        for (int i = this.maxLevel - 1; i>=0; i--) {
            while (nd.getNext()[i] != null && nd.getNext()[i].getValue() < value) {
                nd = nd.getNext()[i];
            }
        }
        SkipNode<T> next = nd.getNext()[0];
        for(int i = 0; i<newNode.getLevel() + 1; i++) {
            newNode.getNext()[i] = next;
        }
        for (int i = 0; i<nd.getLevel() + 1; i++) {
            nd.getNext()[i] = newNode;
        }
        size++;
    }

    public SkipNode<T> search(int value) {
        SkipNode<T> nd = this.head;
        for (int i = this.maxLevel - 1; i >= 0; i--) {
            while (nd.getNext()[i] != null && nd.getNext()[i].getValue() <= value) {
                 nd = nd.getNext()[i];
                System.out.println(nd.getValue());
                 if (nd.getValue() == value) {
                     return nd;
                 }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        SkipNode nd = this.head;
        while (nd != null) {
            builder.append(nd.getValue() + "::" + nd.getLevel() + " -> ");
            nd = nd.getNext()[0];
        }
        return builder.toString();
    }
}
