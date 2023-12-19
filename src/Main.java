import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
public class Main {
    public static void main(String[] args) {
        try {

            ServerSocket server = new ServerSocket(6379);
            System.out.println("Wrap server is listening on port 6379");



            Socket socket = server.accept();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println("$7\r\nhellloo\r\n");




            socket.close();
        } catch(IOException ioe) {
            System.out.println("Couldn't start Wrap server, port is already in usage");
        }




    }
}