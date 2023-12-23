import models.ServerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
public class Main {
    public static void main(String[] args) {
        try {

            ServerSocket server = new ServerSocket(6379);
            System.out.println("Wrap server is listening on port 6379");

            while (true) {
                Socket socket = server.accept();


                new ServerThread(socket).start();

            }







        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Couldn't start Wrap server, port is already in usage");
        }




    }
}