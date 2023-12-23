package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;

public class GetCommand implements Command {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public DataType execute(String[] args) {
        String data = WrapDB.getInstance().getRecord(args[1]);
        if (data == null) {
            return new SimpleError("Key was not found");
        }
        return new SimpleString(data);
    }
}
