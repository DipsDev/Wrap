package server.models.datatypes;

public abstract class DataType {

    protected static String terminator = "\r\n";
    private char sign;

    protected DataType(char sign) {
        this.sign = sign;
    }

    public abstract String encode();
}
