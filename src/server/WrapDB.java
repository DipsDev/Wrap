package server;

import server.models.storetypes.StoreType;

import java.util.HashMap;

public class WrapDB {
    private static WrapDB instance;

    private HashMap<String, StoreType<?>> hashMap;

    private final Object lock;

    private WrapDB() {
        this.hashMap = new HashMap<>();
        this.lock = new Object();
    }

    public <T extends StoreType<?>> void create(String name, T value) {
        this.hashMap.put(name, value);
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


    public static WrapDB getInstance() {
        if (instance == null) {
            instance = new WrapDB();
        }
        return instance;
    }
}
