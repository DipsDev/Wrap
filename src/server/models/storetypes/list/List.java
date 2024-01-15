package server.models.storetypes.list;

import server.models.datatypes.DataType;
import server.models.storetypes.StoreType;

import java.util.ArrayList;

public class List implements StoreType<StoreType<?>> {
    private ListNode<StoreType<?>> head;
    private ListNode<StoreType<?>> tail;
    private int size;


    public List() {
    }

    public void appendLast(StoreType<?> value) {
        size++;
        ListNode<StoreType<?>> nd = new ListNode<>(value);
        tail.setNext(nd);
        nd.setPrev(tail);
        tail = tail.getNext();

    }

    public void appendFirst(StoreType<?> value) {
        size++;
        ListNode<StoreType<?>> newHead = new ListNode<>(value);
        newHead.setNext(head);
        head.setPrev(newHead);
        head = newHead;
    }

    public ListNode<StoreType<?>> traverseBackwards(int index) {
        ListNode<StoreType<?>> node = this.tail;
        int counter = 0;
        while (node != null && size - counter > index) {
            node = node.getPrev();
            counter++;
        }
        return node;

    }
    public ListNode<StoreType<?>> traverseForwards(int index) {
        ListNode<StoreType<?>> node = this.head;
        int counter = 0;
        while (node != null && counter < index) {
            node = node.getNext();
            counter++;
        }
        return node;

    }


    public void updateNode(int index, StoreType<?> newValue) {
        if (index >= size) {
            return;
        }
        if (index > size / 2) {
            ListNode<StoreType<?>> node = traverseBackwards(index);
            node.setValue(newValue);
            return;
        }
        traverseForwards(index).setValue(newValue);
    }

    public java.util.List<StoreType<?>> range(int start, int finish) {
        ArrayList<StoreType<?>> list = new ArrayList<>();
        ListNode<StoreType<?>> node = traverseForwards(start);
        int counter = finish - start;
        while (node != null && counter > 0) {
            list.add(node.getValue());
            counter--;
            node = node.getNext();
        }
        return list;

    }






    @Override
    public DataType prepare() {
        return null;
    }


}
