package main.server.models.storetypes.sortedlist;

import main.server.models.datatypes.DataType;
import main.server.models.storetypes.StoreType;

public class SortedListStore implements StoreType<StoreType<?>> {

    private final Object lock;
    private final SkipList<StoreType<?>> list;

    public SortedListStore() {
        this.list = new SkipList<>(7);
        this.lock = new Object();
    }

    @Override
    public DataType prepare() {
        return null;
    }


    public void put(int score, StoreType<?> value) {
        synchronized (lock) {
            this.list.insert(score, value);
        }
    }

    public int size() {
        synchronized (lock) {
            return this.list.getSize();
        }
    }

    public StoreType<?> search(int score) {
        synchronized (lock) {
            SkipNode<StoreType<?>> node = this.list.search(score);
            if (node == null) {
                return null;
            }
            return node.getData();
        }
    }




}
