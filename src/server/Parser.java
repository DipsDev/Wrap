package server;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

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
           val += current;
       }
       this.inputStream.skip(1); // skips the terminator
       return Integer.parseInt(String.valueOf((char) val));
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
      this.inputStream.skipNBytes(2);
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
