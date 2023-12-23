package server.models.datatypes;

public class SimpleError extends DataType {

    private String text;

     public SimpleError(String text) {
        super('-');
        this.text = text;
    }


    @Override
    public String encode() {
        return '-' + text + terminator;
    }
}
