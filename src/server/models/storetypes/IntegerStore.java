package server.models.storetypes;

import server.models.datatypes.DataType;
import server.models.datatypes.Integer;

public class IntegerStore implements StoreType<java.lang.Integer> {
    private int value;

    public IntegerStore(int value) {
        this.value = value;
    }


    @Override
    public DataType prepare() {
        return new Integer(this.value);
    }

    @Override
    public void put(java.lang.Integer value) {
        this.value = value;
    }

    @Override
    public java.lang.Integer get(String name) {
        return this.value;
    }


}
