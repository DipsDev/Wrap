package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.utils.Responses;

import java.util.HashMap;

public class CommandHandler {
    private static CommandHandler instance;

    private final HashMap<String, Command> commands;

    private CommandHandler() {
        this.commands = new HashMap<>();
        this.commands.put("get", new GetCommand());
        this.commands.put("set", new SetCommand());


        this.commands.put("hset", new HsetCommand());
        this.commands.put("hget", new HgetCommand());
        this.commands.put("hgetall", new HgetallCommand());
        this.commands.put("hkeys", new HkeysCommand());
    }

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public String handle(String[] args) {
        if (!this.commands.containsKey(args[0])) {
            return Responses.COMMAND_NOT_FOUND.getParsed();
        }
        Command cmd = this.commands.get(args[0]);
        if (cmd.getArgsCount() + 1 > args.length) {
            return Responses.NOT_ENOUGH_ARGS.getParsed();
        }
        DataType returnType = cmd.execute(args);
        return returnType.encode();


    }
}
