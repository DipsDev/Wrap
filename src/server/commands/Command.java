package server.commands;

import server.models.datatypes.DataType;
import server.models.storetypes.IntegerStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;

import java.util.function.Function;

public interface Command {
    String getName();

    int getArgsCount();

    DataType execute(String[] args);



}
