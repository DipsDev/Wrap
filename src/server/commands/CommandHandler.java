package server.commands;

import server.models.datatypes.DataType;
import server.utils.Responses;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.ServiceLoader;

public class CommandHandler {
    private static CommandHandler instance;

    private final HashMap<String, Command> commands;

    private CommandHandler() {
        this.commands = new HashMap<>();
        try {
            this.registerCommands();
        } catch (Exception e) {
            System.out.println("Couldn't register all classes, ");
            e.printStackTrace();
        }
    }

    private void registerCommands() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL resource = loader.getResource("server/commands");
        File folder = new File(resource.getFile());
        for (final File fileEntry : folder.listFiles()) {
            String className = "server.commands." + fileEntry.getName().substring(0, fileEntry.getName().length() - 6);
            Class<?> commandClass = Class.forName(className);
            if (commandClass.isAnnotationPresent(RegisteredCommand.class) && Command.class.isAssignableFrom(commandClass)) {
                Command command = (Command) commandClass.getDeclaredConstructors()[0].newInstance();
                this.commands.put(command.getName(), command);

            }
        }
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
