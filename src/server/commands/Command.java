package server.commands;

import java.util.function.Function;

public interface Command {
    String getName();

    String execute(String[] args);

}
