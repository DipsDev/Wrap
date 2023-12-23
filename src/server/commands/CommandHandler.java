package server.commands;

import server.WrapDB;

import java.util.HashMap;

public class CommandHandler {
    private static CommandHandler instance;

    private HashMap<String, Command> commands;

    private CommandHandler() {
        this.commands = new HashMap<>();
    }

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public String handle(String[] args) {
        if (!this.commands.containsKey(args[0])) {
            return null; //TODO: Return an error message indicating the command was not found
        }
        return this.commands.get(args[0]).execute(args);

    }
}
