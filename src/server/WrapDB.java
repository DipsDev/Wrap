package server;

import server.models.storetypes.StoreType;

import java.util.HashMap;

public class WrapDB {
    private static WrapDB instance;

    private final HashMap<String, StoreType<?>> hashMap;

    private final Object lock = new Object();

    private WrapDB() {
        this.hashMap = new HashMap<>();
    }

    public void create(String name, StoreType<?> value) {
        synchronized (lock) {
            this.hashMap.put(name.toLowerCase(), value);
        }

    }

    public boolean exists(String name) {
        synchronized (lock) {
            return this.hashMap.containsKey(name);
        }
    }

    public StoreType<?> get(String name) {
        synchronized (lock) {
            return this.hashMap.get(name);
        }
    }

    public StoreType<?> remove(String key) {
        synchronized (lock) {
            return this.hashMap.remove(key);
        }
    }


    public static WrapDB getInstance() {
        if (instance == null) {
            instance = new WrapDB();
        }
        return instance;
    }
}
