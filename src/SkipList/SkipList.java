package SkipList;

import java.util.Random;

public class SkipList {

    private final SkipNode head;
    private final int maxLevel;

    private int size;

    private final Random random = new Random();

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        this.head = new SkipNode(Integer.MIN_VALUE, maxLevel);
        this.size = 0;
    }

    private int generateRandomHeight() {
        int level = 0;
        while (random.nextDouble() < 0.5 && level < this.maxLevel) {
            level++;
        }
        return level;
    }


    public void insert(int value) {
        SkipNode nd = this.head;
        SkipNode newNode = new SkipNode(value, generateRandomHeight());
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
        SkipNode next = nd.getNext()[0];
        for(int i = 0; i<newNode.getLevel() + 1; i++) {
            newNode.getNext()[i] = next;
        }
        for (int i = 0; i<nd.getLevel() + 1; i++) {
            nd.getNext()[i] = newNode;
        }
        size++;
    }

    public SkipNode search(int value) {
        SkipNode nd = this.head;
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
