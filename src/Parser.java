import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class Parser {

    private static int terminator = 13; // ==> \r
   private InputStream inputStream;

   public Parser(InputStream inputStream) {
      this.inputStream = inputStream;
   }

   public int parseLength() throws IOException {
       this.inputStream.skip(1); // currently skip, later creates a token
       int val = (char) (this.inputStream.read() - '0');
       this.inputStream.skip(2); // skips the terminator
       return val;
   }
   public String parseBulkString() throws IOException {
      char[] buffer = new char[parseLength()]; // supports only 1-9 length
      int total = 0;
      // reads the data until encounters the terminator
      while (total < buffer.length) {
        int in = this.inputStream.read();
        buffer[total] = (char) in;
        total++;
      }
      return new String(buffer);
   }

}
