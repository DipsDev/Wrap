package server.models.storetypes.list;

import server.models.datatypes.DataType;
import server.models.storetypes.StoreType;

public class ListStore<T> implements StoreType<StoreType<T>> {

    private final List<StoreType<T>> list;
    private final Object lock;

    public ListStore() {
        this.list = new List<>();
        this.lock = new Object();
    }
    @Override
    public DataType prepare() {
        return null;
    }

    public void pushEnd(StoreType<T> value) {
        synchronized (lock) {
            this.list.appendLast(value);
        }
    }

    public void pushStart(StoreType<T> value) {
        synchronized (lock) {
            this.list.appendFirst(value);
        }
    }
}
