package main.client;

import java.io.*;
import java.net.Socket;

public class Client {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
       // a user can only send array of bulk strings *<number-of-elements>\r\n$<length>\r\n<data>\r\n
        Socket client = new Socket("127.0.0.1", 6379);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

        while (true)  {
            System.out.print("127.0.0.1:6379> ");
            String parsedCommand = ClientParser.parseClientInputCommands(reader);
            if (parsedCommand.charAt(1) == '0') {
                continue;
            }
            writer.print(parsedCommand);
            writer.flush();
            try {
                ClientReader reader = new ClientReader(client.getInputStream());
                System.out.println(reader.parse());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }



        }









    }
}
