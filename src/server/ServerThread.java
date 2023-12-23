package server;

import server.Parser;
import server.WrapDB;
import server.commands.CommandHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


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
                String[] commandArguments = new Parser(input).parseBulkStringArray();
                CommandHandler commandHandler = CommandHandler.getInstance();
                commandHandler.handle(commandArguments);


            } catch (IOException ioe) {
                throw new RuntimeException(ioe.getMessage());

            }
        }
    }
}
