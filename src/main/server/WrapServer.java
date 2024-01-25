package main.server;

import main.server.commands.CommandHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WrapServer {

    private boolean isRunning = true;

    public void start() {
        try {

            ServerSocket server = new ServerSocket(6379);
            System.out.println("Wrap server is listening on port 6379");

            CommandHandler.getInstance();

            while (isRunning) {
                Socket socket = server.accept();
                new ServerThread(socket).start();
            }
            server.close();

        } catch(IOException ioe) {
            System.out.println("Couldn't start Wrap main.server, port is already in usage");
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    public static void main(String[] args) {
        new WrapServer().start();
    }
}