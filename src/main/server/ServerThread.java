package main.server;

import main.server.commands.CommandHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread  extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            CommandHandler commandHandler = CommandHandler.getInstance();
            while (true) {
                InputStream input = socket.getInputStream();
                String[] commandArguments = new Parser(input).parseBulkStringArray();
                String encodedString = commandHandler.handle(commandArguments);
                writer.print(encodedString);
                writer.flush();
            }
        }
        catch(IOException e) {
            System.out.println("Socket Disconnected");
        }
    }
}
