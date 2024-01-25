package main.server.models.storetypes.sortedlist;

public class SkipNode<T> {


    private int value;
    private T data;
    private SkipNode<T>[] next;

    private int level;


    public SkipNode(int value, T data, int level) {
        this.value = value;
        this.data = data;
        this.next = new SkipNode[level + 1];
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getValue() {
        return value;
    }

    public T getData() {
        return this.data;
    }


    public SkipNode[] getNext() {
        return next;
    }
}

