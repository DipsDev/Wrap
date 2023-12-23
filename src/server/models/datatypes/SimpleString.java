package server.models.datatypes;

public class SimpleString extends DataType {
    private String text;


    public SimpleString(String text) {
        super('+');
        this.text = text;
    }


    @Override
    public String encode() {
        return '+' + text + terminator;
    }
}
