package main.server.models.storetypes;

import main.server.models.datatypes.DataType;
import main.server.models.datatypes.Integer;

import java.util.regex.Pattern;

public class IntegerStore implements PrimitiveStoreType<java.lang.Integer> {
    private int value;

    public static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

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
    public java.lang.Integer get() {
        return this.value;
    }


}
