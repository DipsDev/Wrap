package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;

public class SetCommand implements Command {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public DataType execute(String[] args) {
        WrapDB.getInstance().setRecord(args[1], args[2]);
        return new SimpleString("OK");
    }
}
