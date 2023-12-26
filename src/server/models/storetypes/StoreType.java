package server.models.storetypes;

import server.models.datatypes.DataType;

public interface StoreType<T> {

    public DataType prepare();
    public void put(T value);
    public T get(String name);



}
