package server;

import server.commands.Command;
import server.models.datatypes.DataType;

import java.util.HashMap;

public class WrapDB {
    private static WrapDB instance;

    private HashMap<String, String> hashMap;

    private final Object lock;

    private WrapDB() {
        this.hashMap = new HashMap<>();
        this.lock = new Object();
    }

    public void setRecord(String name, String value) {
        synchronized (lock) {
            this.hashMap.put(name, value);
        }
    }

    public String getRecord(String name) {
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
