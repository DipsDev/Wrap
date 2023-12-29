package server.models.storetypes;

import server.models.datatypes.Array;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashStore implements StoreType<StoreType<?>> {

    private HashMap<String, StoreType<?>> map;

    private final Object lock;

    public HashStore() {
        this.map = new HashMap<>();
        this.lock = new Object();
    }


    @Override
    public DataType prepare() {
        Array arr = new Array();
        synchronized (lock) {
            for (Map.Entry<String, StoreType<?>> pair : this.map.entrySet()) {
                arr.add(new SimpleString(pair.getKey()));
                arr.add(pair.getValue().prepare());
            }
        }

        return arr;

    }

    public DataType prepareKeys() {
        Array arr = new Array();
        synchronized (lock) {
            for (String s : this.map.keySet()) {
                arr.add(new SimpleString(s));

            }
        }
        return arr;
    }

    @Override
    public void put(StoreType<?> value) {
    }

    public void put(String name, StoreType<?> value) {
        synchronized (lock) {
            this.map.put(name, value);
        }

    }


    @Override
    public StoreType<?> get(String name) {
        synchronized (lock) {
            return this.map.get(name);
        }
    }
}
