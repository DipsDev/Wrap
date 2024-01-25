package main.server.models.datatypes;

public class Null extends DataType {
    public Null() {
        super('_');
    }

    @Override
    public String encode() {
        return "_" + terminator;
    }
}
