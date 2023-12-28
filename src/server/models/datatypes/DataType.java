package server.models.datatypes;

public abstract class DataType {

    protected static String terminator = "\r\n";
    protected char sign;

    protected DataType(char sign) {
        this.sign = sign;
    }

    public abstract String encode();
}
