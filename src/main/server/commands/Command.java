package main.server.commands;

import main.server.models.datatypes.DataType;

public interface Command {
    String getName();

    int getArgsCount();

    DataType execute(String[] args);



}
