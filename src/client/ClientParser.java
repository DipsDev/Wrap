package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ClientParser {
    public final static String terminator = "\r\n";



    public static String parseSimpleString(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != 13) {
            builder.append((char) ch);
        }
        inputStream.read();
        return builder.toString();
    }

    public static String parseInteger(InputStream inputStream) throws IOException {
        int sum = 0;
        int ch;
        while ((ch = inputStream.read()) != 13) {
            sum *= 10;
            sum += ((char) ch) - '0';
        }
        inputStream.read();
        return "(Integer) " + sum;
    }

    public static String parseSimpleError(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != 13) {
            builder.append((char) ch);
        }
        inputStream.read();
        return builder.toString();
    }

    public static String parseNull(InputStream inputStream) throws IOException {
        inputStream.read();
        inputStream.read();
        return "null";
    }

    public static int parseLength(InputStream inputStream) throws IOException {
        int val = 0;
        int current;
        while ((current = inputStream.read()) != 13) { // allow n digits length
            val = (val * 10) + (((char) current) - '0');
        }
        inputStream.read(); // skips the terminator
        return val;
    }
    public static String parseArray(InputStream inputStream) throws IOException {
        int arrayLength = parseLength(inputStream);
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < arrayLength) {
            builder.append(i + 1).append(") ").append(new ClientReader(inputStream).parse()).append("\n");
            i++;

        }
        return builder.toString();

    }


    public static String parseClientInputCommands(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        StringBuilder current = new StringBuilder();
        String commands = reader.readLine();

        int currentWordCount = 0;
        int arrCount = 0;

        boolean isInsideString = false;


        for (int i = 0; i<commands.length(); i++) {
            if (commands.charAt(i) == '"') {
                isInsideString = !isInsideString;
                currentWordCount++;
                if (i == commands.length() - 1) {
                    arrCount++;
                    builder.append("$")
                            .append(currentWordCount)
                            .append(terminator).append('"')
                            .append(current)
                            .append('"')
                            .append(terminator);

                }
                continue;
            }
            if (isInsideString) {
                current.append(commands.charAt(i));
                currentWordCount++;
            }
            else {
                if (commands.charAt(i) == ' ') {
                    arrCount++;
                    builder.append("$")
                            .append(currentWordCount)
                            .append(terminator)
                            .append(current)
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
                            .append(current)
                            .append(terminator);
                    current.setLength(0);
                    currentWordCount = 0;
                } else {
                    current.append(commands.charAt(i));
                    currentWordCount++;
                }
            }
        }
        return "*" + arrCount + terminator + builder;
    }
}
