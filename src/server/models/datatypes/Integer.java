package server.models.datatypes;

public class Integer extends DataType {

    private int value;
    public Integer(int value) {
        super(':');
        this.value = value;
    }

    @Override
    public String encode() {
        return this.sign + value + terminator;
    }
}
