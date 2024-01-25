package main.server.models.storetypes.list;

import main.server.models.datatypes.DataType;
import main.server.models.storetypes.StoreType;

public class ListStore implements StoreType<StoreType<?>> {

    private final List list;
    private final Object lock;

    public ListStore() {
        this.list = new List();
        this.lock = new Object();
    }
    @Override
    public DataType prepare() {
        return null;
    }

    public void pushEnd(StoreType<?> value) {
        synchronized (lock) {
            this.list.appendLast(value);
        }
    }

    public void pushStart(StoreType<?> value) {
        synchronized (lock) {
            this.list.appendFirst(value);
        }
    }

    public java.util.List<StoreType<?>> range(int start, int finish) {
        return this.list.range(start, finish);
    }
}
