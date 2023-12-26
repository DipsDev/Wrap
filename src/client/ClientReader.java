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
        return "";
    }

    public int parseLength() throws IOException {
        int val = 0;
        int current;
        while ((current = this.inputStream.read()) != 13) { // allow n digits length
            val += current;
        }
        this.inputStream.skip(1); // skips the terminator
        return Integer.parseInt(String.valueOf((char) val));
    }
}
