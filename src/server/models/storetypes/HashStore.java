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
        Iterator it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, StoreType<?>> pair = (Map.Entry) it.next();
            arr.add(new SimpleString(pair.getKey()));
            arr.add(pair.getValue().prepare());
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
