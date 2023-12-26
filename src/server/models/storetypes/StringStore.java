package server.models.storetypes;

import server.models.datatypes.DataType;
import server.models.datatypes.SimpleString;

public class StringStore implements StoreType<String> {

    private String value;

    public StringStore(String value) {
        this.value = value;
    }


    @Override
    public DataType prepare() {
        return new SimpleString(this.value);
    }

    @Override
    public void put(String value) {
        this.value = value;

    }

    @Override
    public String get(String name) {
        return this.value;
    }
}
