package test.server;

import main.client.ClientParser;
import main.client.ClientReader;
import org.junit.Rule;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TestServer {


    static Socket client;

    static {
        try {
            client = new Socket("127.0.0.1", 6379);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TestServer() throws IOException {
    }

    public static String exec(String prompt) throws IOException {
        String parsedCommand = ClientParser.parseClientInputCommands(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(prompt.getBytes(StandardCharsets.UTF_8)))));
        writer.print(parsedCommand);
        writer.flush();
        ClientReader reader = new ClientReader(client.getInputStream());
        return reader.parse();
    }
}
