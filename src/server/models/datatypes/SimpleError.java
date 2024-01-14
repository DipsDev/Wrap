package server.models.datatypes;

import server.utils.Errors;

public class SimpleError extends DataType {

    private String text;

     public SimpleError(String text) {
        super('-');
        this.text = text;
    }

    public SimpleError(Errors error) {
         super('-');
         this.text = error.getParsed();
    }


    @Override
    public String encode() {
        return '-' + text + terminator;
    }
}
