package models;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;


public class ServerThread  extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        while (true) {
            try {
                InputStream input = socket.getInputStream();
                Parser parser = new Parser(input);
                System.out.println(Arrays.toString(parser.parseBulkStringArray()));

            } catch (IOException ioe) {
                throw new RuntimeException(ioe.getMessage());

            }
        }
    }
}
