package server.models.storetypes.list;

import server.models.datatypes.DataType;
import server.models.storetypes.StoreType;

import java.util.ArrayList;

public class List<T> implements StoreType<StoreType<?>> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;


    public List() {
    }

    public void appendLast(T value) {
        size++;
        tail.setNext(new ListNode<>(value));
    }

    public void appendFirst(T value) {
        ListNode<T> newHead = new ListNode<>(value);
        newHead.setNext(head);
        head = newHead;
    }

    public ListNode<T> traverseBackwards(int index) {
        ListNode<T> node = this.tail;
        int counter = 0;
        while (node != null && size - counter > index) {
            node = node.getPrev();
            counter++;
        }
        return node;

    }
    public ListNode<T> traverseForwards(int index) {
        ListNode<T> node = this.tail;
        int counter = 0;
        while (node != null && counter < index) {
            node = node.getNext();
            counter++;
        }
        return node;

    }


    public void updateNode(int index, T newValue) {
        if (index >= size) {
            return;
        }
        if (index > size / 2) {
            ListNode<T> node = traverseBackwards(index);
            node.setValue(newValue);
            return;
        }
        traverseForwards(index).setValue(newValue);
    }

    public java.util.List<T> range(int start, int finish) {
        ArrayList<T> list = new ArrayList<>();
        ListNode<T> node = traverseForwards(start);
        int counter = finish - start;
        while (counter > 0) {
            list.add(node.getValue());
            counter--;
        }
        return list;

    }





    @Override
    public DataType prepare() {
        return null;
    }


}
