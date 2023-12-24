package server.models.datatypes.sortedlist;

public class SkipNode {


    private int value;
    private SkipNode[] next;

    private int level;


    public SkipNode(int value, int level) {
        this.value = value;
        this.next = new SkipNode[level + 1];
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getValue() {
        return value;
    }

    public void setNext(SkipNode[] next) {
        this.next = next;
    }

    public SkipNode[] getNext() {
        return next;
    }
}

