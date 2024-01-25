package test.server.commands;

import main.client.ClientParser;
import main.client.ClientReader;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Assert;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class TestSetCommand {

    Socket client = new Socket("127.0.0.1", 6379);
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

    public TestSetCommand() throws IOException {
    }

    @BeforeAll
    public static void init(){
        System.out.println("BeforeAll init() method called");
    }

    @Test
    public void shouldBeAbleToSetAndGet() throws IOException {
        String prompt = "set version \"1.24-alpha\"";
        String parsedCommand = ClientParser.parseClientInputCommands(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(prompt.getBytes(StandardCharsets.UTF_8)))));
        writer.print(parsedCommand);
        writer.flush();
        ClientReader reader = new ClientReader(client.getInputStream());
        Assert.assertEquals(reader.parse(), "OK");




    }

}
