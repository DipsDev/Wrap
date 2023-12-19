import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) throws IOException {
       // a user can only send array of bulk strings *<number-of-elements>\r\n$<length>\r\n<data>\r\n
        Socket client = new Socket("127.0.0.1", 6379);
        System.out.println(new Parser(client.getInputStream()).parseBulkString());
    }
}
