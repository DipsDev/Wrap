package main.server;

import main.server.commands.CommandHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WrapServer {
    public static void main(String[] args) {
        try {

            ServerSocket server = new ServerSocket(6379);
            System.out.println("Wrap main.server is listening on port 6379");

            CommandHandler.getInstance();

            while (true) {
                Socket socket = server.accept();
                new ServerThread(socket).start();
            }

        } catch(IOException ioe) {
            System.out.println("Couldn't start Wrap main.server, port is already in usage");
        }




    }
}