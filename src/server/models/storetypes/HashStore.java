package server.models.storetypes;

import server.models.datatypes.Array;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashStore implements StoreType<StoreType<?>> {

    private HashMap<String, StoreType<?>> map;

    public HashStore() {
        this.map = new HashMap<>();
    }


    @Override
    public DataType prepare() {
        Array arr = new Array();
        for (Map.Entry<String, StoreType<?>> pair : this.map.entrySet()) {
            arr.add(new SimpleString(pair.getKey()));
            arr.add(pair.getValue().prepare());
        }

        return arr;

    }

    public DataType prepareKeys() {
        Array arr = new Array();
        Iterator<String> it = this.map.keySet().iterator();
        while (it.hasNext()) {
            arr.add(new SimpleString(it.next()));
            it.remove();
        }
        return arr;
    }

    @Override
    public void put(StoreType<?> value) {
    }

    public void put(String name, StoreType<?> value) {
        this.map.put(name, value);
    }


    @Override
    public StoreType<?> get(String name) {
        return this.map.get(name);
    }
}
