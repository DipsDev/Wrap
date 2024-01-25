package main.server.commands.registers;

import main.server.commands.Command;
import main.server.commands.RegisteredCommand;
import main.server.models.datatypes.DataType;
import main.server.models.datatypes.SimpleString;

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
