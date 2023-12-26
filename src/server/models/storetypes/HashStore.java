package server.models.storetypes;

import server.models.datatypes.DataType;

import java.util.HashMap;

public class HashStore implements StoreType<StoreType<?>> {

    private HashMap<String, StoreType<?>> map;

    public HashStore() {
        this.map = new HashMap<>();
    }


    @Override
    public DataType prepare() {
        return null;
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
