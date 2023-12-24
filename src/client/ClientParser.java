package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class ClientParser {
    public final static String terminator = "\r\n";



    public static String parseSimpleString(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != 13) {
            builder.append((char) ch);
        }
        inputStream.skip(1);
        return builder.toString();
    }

    public static String parseNull(InputStream inputStream) throws IOException {
        inputStream.skipNBytes(2);
        return "null";
    }


    public static String parseClientInputCommands(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        StringBuilder current = new StringBuilder();
        String commands = reader.readLine();

        int currentWordCount = 0;
        int arrCount = 0;


        for (int i = 0; i<commands.length(); i++) {
            if (commands.charAt(i) == ' ') {
                arrCount++;
                builder.append("$")
                        .append(currentWordCount)
                        .append(terminator)
                        .append(current.toString())
                        .append(terminator);
                current.setLength(0);
                currentWordCount = 0;
            } else if (i == commands.length() - 1) {
                    current.append(commands.charAt(i));
                    currentWordCount++;
                    arrCount++;
                    builder.append("$")
                            .append(currentWordCount)
                            .append(terminator)
                            .append(current.toString())
                            .append(terminator);
                    current.setLength(0);
                    currentWordCount = 0;
            }
            else {
                current.append(commands.charAt(i));
                currentWordCount++;
            }
        }
        return "*" + arrCount + terminator + builder;
    }
}
