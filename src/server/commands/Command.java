package server.commands;

import server.models.datatypes.DataType;

import java.util.function.Function;

public interface Command {
    String getName();

    int getArgsCount();

    DataType execute(String[] args);

}
