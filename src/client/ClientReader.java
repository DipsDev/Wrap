package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientReader {

    private final InputStream inputStream;

    public ClientReader(InputStream stream) {
        this.inputStream = stream;
    }

    public String parse() throws IOException {
        int sign = this.inputStream.read();
        // start going over each type
        if (sign == DataTypes.SimpleString.getSign()) {
            return ClientParser.parseSimpleString(this.inputStream);
        }
        if (sign == DataTypes.Null.getSign()) {
            return ClientParser.parseNull(this.inputStream);
        }
        if (sign == DataTypes.SimpleError.getSign()) {
            return ClientParser.parseSimpleError(this.inputStream);
        }
        if (sign == DataTypes.Array.getSign()) {
            return ClientParser.parseArray(this.inputStream);
        }
        if (sign == DataTypes.Integer.getSign()) {
            return ClientParser.parseInteger(this.inputStream);
        }
        System.out.println("NOT IMPLEMENTED " + (char) sign);
        return "";
    }


}
