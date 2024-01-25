package server.commands.registers;

import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleString;

import java.util.Arrays;

@RegisteredCommand
public class EchoCommand implements Command {
    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public DataType execute(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i<args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        return new SimpleString(builder.toString());
    }
}
