package main.server;

import java.io.*;

public class Parser {

    private static int terminator = 13; // ==> \r
   private final DataInputStream inputStream;

   public Parser(InputStream inputStream) {
      this.inputStream = new DataInputStream(inputStream);
   }

   public int parseLength() throws IOException {
       int sign = this.inputStream.read();
       int val = 0;
       int current;
       while ((current = this.inputStream.read()) != terminator) { // allow n digits length

           val = (val * 10) + (((char) current) - '0');
       }
       this.inputStream.read(); // skips the terminator
       return val;
   }

   public String parseBulkString() throws IOException {
       int length = parseLength();
      char[] buffer = new char[length];
      int total = 0;
      // reads the data until encounters the terminator
      while (total < buffer.length) {
        int in = this.inputStream.read();
        buffer[total] = (char) in;
        total++;
      }
      this.inputStream.read();
      this.inputStream.read();
      return new String(buffer);
   }
   public String[] parseBulkStringArray() throws IOException {
       int length = parseLength();
       String[] buffer = new String[length];
       int total = 0;
        while (total < buffer.length) {
            buffer[total] = parseBulkString();
            total++;
        }
       return buffer;
   }



}
