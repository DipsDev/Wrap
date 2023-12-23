package server;

import server.Parser;
import server.WrapDB;
import server.commands.CommandHandler;

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
            while (true) {
                InputStream input = socket.getInputStream();
                String[] commandArguments = new Parser(input).parseBulkStringArray();
                CommandHandler commandHandler = CommandHandler.getInstance();
                String encodedString = commandHandler.handle(commandArguments);
                writer.print(encodedString);
                writer.flush();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
