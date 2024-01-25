package main.server.models.storetypes;

import main.server.models.datatypes.DataType;
import main.server.models.datatypes.SimpleString;

public class StringStore implements PrimitiveStoreType<String> {

    private String value;

    public StringStore(String value) {
        this.value = value.substring(1, value.length() - 1);
    }


    @Override
    public DataType prepare() {
        return new SimpleString(this.value);
    }

    @Override
    public void put(String value) {
        this.value = value.substring(1, value.length() - 1);

    }

    @Override
    public String get() {
        return this.value;
    }
}
