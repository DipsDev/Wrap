package server.models.storetypes.list;

public class ListNode<T> {
    private T value;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public boolean hasPrev() {
        return this.prev != null;
    }

    public void setValue(T value) {
        this.value = value;

    }

    public ListNode<T> getNext() {
        return next;
    }

    public ListNode<T> getPrev() {
        return prev;
    }
}
